package link;

import auth.AuthManager;
import com.mafia.serverex.metrics.MetricsHelper;
import com.mafia.serverex.util.FrequencyControl;
import msg.clink.CForward;
import msg.clink.CLoginAccountCheck;
import msg.clink.CLoginTokenCheck;
import msg.clink.SLoginTokenCheck;
import msg.linkau.LLoginAccountCheck;
import msg.linkau.LTokenCheck;
import msg.net.EErrorCode;
import msg.plink.LForward;
import msg.plink.LLinkBroken;
import msg.plink.LUserKeepAlive;
import pcore.collection.LongConcurrentHashMap;
import pcore.db.Trace;
import pcore.io.Dispatcher;
import pcore.io.DynamicMultiClientManager;
import pcore.io.ProtocolDispatcher;
import pcore.io.Server;
import provider.ProviderServer;
import provider.ProviderSession;

import java.util.concurrent.TimeUnit;

/**
 * Created by zyao on 2020/2/7 12:49
 */

@SuppressWarnings("SynchronizationOnLocalVariableOrMethodParameter")
public final class LinkServer extends Server<LinkSession> {

    private static ProtocolDispatcher dispatcher = new ProtocolDispatcher();

    private static LinkServer inst = null;

    private static long forwardCount = 0;

    public static LinkServer getInst() {
        return inst;
    }

    public static void start(LinkConf conf) {
        if (inst == null) {
            inst = new LinkServer(conf);
            inst.open();
        }
    }

    public static Dispatcher getDispatcher() {
        return dispatcher;
    }
    private LinkServer(Conf conf) {
        super(conf);
        dispatcher.register(CLoginAccountCheck.class, this::process);
        dispatcher.register(CLoginTokenCheck.class, this::process);
        dispatcher.register(CForward.class, this::process);
    }

    @Override
    protected void onOpen() {
        LinkConf linkConf = (LinkConf) this.getConf();
        linkConf.workGroup.scheduleWithFixedDelay(this::forwardKeepAlive, linkConf.getForwardSessionKeepAliveTime(), linkConf.getForwardSessionKeepAliveTime(), TimeUnit.SECONDS);
    }

    @Override
    protected void onClose() {

    }

    @Override
    protected void onAddSession(LinkSession linkSession) {

    }

    @Override
    protected void onDelSession(LinkSession linkSession) {
        synchronized (linkSession) {
            if (linkSession.getState() == LinkSession.STATE.FORWARD || linkSession.getState() == LinkSession.STATE.LOGIN) {
                LLinkBroken p = new LLinkBroken(linkSession.getUserId(), linkSession.getSessionId());

                for (ProviderSession ps : linkSession.getProviderMap().values()) {
                    ps.send(p);
                }
                linkSession.setState(LinkSession.STATE.NONE);
            }
        }
    }

    @Override
    protected LinkSession newSession(pcore.io.Connection connection) {
        return new LinkSession(connection);
    }

    private void forwardKeepAlive() {
        Trace.debug("[LinkServer] LUserKeepAlive");
        LongConcurrentHashMap<LinkSession> sessions = LinkServer.this.getSessions();
        for (var it = sessions.valuesIterator(); it.hasNext(); ) {
            LinkSession session = it.next();
            if (session.getState() == LinkSession.STATE.FORWARD) {
                synchronized (session) {
                    session.forwardToGs(new LUserKeepAlive(session.getSessionId()));
                }
            }
        }
    }

    private void process(CLoginAccountCheck p) {
        LinkSession linkSession = (LinkSession) p.getContext();
        synchronized (linkSession) {
            if (linkSession.getState() != LinkSession.STATE.NONE) {
                Trace.error("session:{} receive CLoginAccountCheck with state:{}", linkSession, linkSession.getState());
                linkSession.close();
                return;
            }
            DynamicMultiClientManager.DynamicClient auth = AuthManager.getInst().getRandomAuth();
            linkSession.setAuthClient(auth);
            auth.send(new LLoginAccountCheck(linkSession.getSessionId(), p.channel, p.ip, p.devicedId, p.params));
            linkSession.setState(LinkSession.STATE.RECV_ACCOUNT_CHECK);
        }
    }

    private void process(CLoginTokenCheck p) {
        LinkSession linkSession = (LinkSession) p.getContext();
        synchronized (linkSession) {
            if (linkSession.getState() != LinkSession.STATE.NONE && linkSession.getState() != LinkSession.STATE.SEND_ACCOUNT_CHECK_RESULT) {
                Trace.error("session:{} receive CLoginTokenCheck with state:{}", linkSession, linkSession.getState());
                linkSession.close();
                return;
            }
            ProviderSession gsSession = ProviderServer.getInst().getServer(p.gsServerId);
            if (gsSession == null) {
                linkSession.send(new SLoginTokenCheck(EErrorCode.SERVER_NOT_EXIST, linkSession.getUserId()));
                return;
            }
            linkSession.setGsServerId(p.gsServerId);
            linkSession.setReconnect(p.isReconnect);
            linkSession.setExtraInfo(p.extra);
            if (linkSession.getState() == LinkSession.STATE.NONE) {
                DynamicMultiClientManager.DynamicClient auth = AuthManager.getInst().getRandomAuth();
                linkSession.setAuthClient(auth);
                linkSession.setUserId(p.uid);
            } else if (linkSession.getState() == LinkSession.STATE.SEND_ACCOUNT_CHECK_RESULT) {
                if (linkSession.getUserId() != p.uid) {
                    Trace.error("session:{} receive CLoginTokenCheck with error useId:{}", linkSession, p.uid);
                    linkSession.close();
                    return;
                }
            }
            DynamicMultiClientManager.DynamicClient authClient = linkSession.getAuthClient();
            authClient.send(new LTokenCheck(linkSession.getSessionId(), p.uid, p.token));
            linkSession.setState(LinkSession.STATE.RECV_TOKEN_CHECK);
        }
    }

    private void process(CForward p) {
        LinkSession linkSession = (LinkSession) p.getContext();
        //synchronized (linkSession) {
        if (linkSession.getState() != LinkSession.STATE.FORWARD) {
            Trace.error("session:{} receive CForward with state:{}", linkSession, linkSession.getState());
            linkSession.close();
            return;
        }
        if (!linkSession.getLimiter().check(p.type)) {
            Trace.error("session:{} receive CForward too fast", linkSession);
            linkSession.close();
        }

        LForward msg = new LForward(linkSession.getSessionId(), p.type, p.data);
        ProviderSession providerSession = linkSession.getProviderSession(p.pvid);
        if (providerSession == null) {
            Trace.error("session:{} receive CForward but providerSession is null", linkSession);
            //linkSession.close();
            return;
        }
        providerSession.send(msg);
        MetricsHelper.getCounter("link_c_forward_count").set(++forwardCount);
        //}
    }
}
