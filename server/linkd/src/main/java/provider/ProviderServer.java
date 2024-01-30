package provider;

import com.mafia.serverex.metrics.MetricsHelper;
import link.LinkConf;
import link.LinkServer;
import link.LinkSession;
import main.BootConfig;
import msg.clink.SKickUser;
import msg.clink.SOnlineAnnounce;
import msg.net.EErrorCode;
import msg.net.GClientAnnouceServerInfo;
import msg.net.GServerAnnouceServerInfo;
import msg.plink.*;
import pcore.db.Trace;
import pcore.io.ProtocolDispatcher;
import pcore.io.Server;
import pcore.io.Session;
import pcore.marshal.Octets;

/**
 * Created by zyao on 2020/2/10 11:16
 */
@SuppressWarnings("SynchronizationOnLocalVariableOrMethodParameter")
public class ProviderServer extends Server<ProviderSession> {

    private static ProviderServer inst;
    private static ProtocolDispatcher dispatcher = new ProtocolDispatcher();
    private static long forwardCount = 0;

    public static ProviderServer getInst() {
        return inst;
    }

    public static ProtocolDispatcher getDispatcher() {
        return dispatcher;
    }

    public static void start(ProviderConf conf) {
        if (inst == null) {
            inst = new ProviderServer(conf);
            inst.open();
        }
    }

    public ProviderServer(Conf conf) {
        super(conf);
        dispatcher.register(GForward.class, this::process);
        dispatcher.register(GClientAnnouceServerInfo.class, this::process);
        dispatcher.register(GUserOnline.class, this::process);
        dispatcher.register(GMulticast.class, this::process);
        dispatcher.register(GKickUser.class, this::process);
        dispatcher.register(GBind.class, this::process);
        dispatcher.register(GUnBind.class, this::process);

    }

    @Override
    protected void onOpen() {

    }

    @Override
    protected void onClose() {

    }

    @Override
    protected ProviderSession newSession(pcore.io.Connection connection) {
        return new ProviderSession(connection);
    }

    @Override
    protected void onAddSession(ProviderSession providerSession) {
        providerSession.send(new GServerAnnouceServerInfo(BootConfig.getIns().getServerId(),
                BootConfig.getIns().getProviderConf().expireTime / 2));

        LinkConf conf = BootConfig.getIns().getLinkConf();
        providerSession.send(new LAnnounceOuterNetAddress(conf.getPublicIp(),
                conf.getPublicIpv6(), conf.port, conf.getPublicIsBackup()));
    }

    @Override
    protected void onDelSession(ProviderSession providerSession) {

    }

    private void process(GForward p) {
        LinkSession linkSession = LinkServer.getInst().getSession(p.linkSid);
        if (linkSession == null || linkSession.getState() != LinkSession.STATE.FORWARD) {
            if(linkSession == null){
                Trace.warn("Receive GForward with error linkSession {} not exist",p.linkSid);
            }else{
                Trace.warn("Receive GForward with error linkSession {} state {}",p.linkSid,linkSession.getState());
            }
            // should not break the session
            return;
        }
        linkSession.send(Octets.wrap(p.data));
        MetricsHelper.getCounter("link_g_forward_count").set(++forwardCount);
    }

    private void process(GClientAnnouceServerInfo p) {
        register(p.serverId, (Session) p.getContext());
    }

    private void process(GUserOnline p) {
        LinkSession linkSession = LinkServer.getInst().getSession(p.linkSid);
        if (linkSession != null) {
            synchronized (linkSession) {
                if (p.err == EErrorCode.OK) {
                    linkSession.setState(LinkSession.STATE.FORWARD);
                    linkSession.send(new SOnlineAnnounce(linkSession.getUserId()));
                } else {
                    linkSession.setState(LinkSession.STATE.NONE);
                    linkSession.send(new SKickUser(p.err));
                }
            }
        }
    }

    private void process(GMulticast p) {
        for (long sid : p.sids) {
            LinkSession linkSession = LinkServer.getInst().getSession(sid);
            if (linkSession == null) {
                continue;
            }
            linkSession.send(p.data);
        }
    }

    private void process(GKickUser p) {
        LinkSession linkSession = LinkServer.getInst().getSession(p.linkSid);
        if (linkSession != null) {
            synchronized (linkSession) {
                linkSession.send(new SKickUser(p.err));
                linkSession.setState(LinkSession.STATE.NONE);
            }
        }
    }

    private void process(GBind p) {
        if (p.lindSids.size() > 0) {
            for (long sid : p.lindSids) {
                LinkSession linkSession = LinkServer.getInst().getSession(sid);
                if (linkSession != null) {
                    Trace.info("[ProviderManager] bind. sid:{}", sid);
                    //synchronized (linkSession) {
                    linkSession.addProviderSession(p.pvid, (ProviderSession) p.getContext());
                    //}
                }
            }
        } else {
            ProviderSessionMap.putDefaultProviderSession(p.pvid, (ProviderSession) p.getContext());
        }

    }

    private void process(GUnBind p) {
        if (p.lindSids.size() > 0) {
            for (long sid : p.lindSids) {
                LinkSession linkSession = LinkServer.getInst().getSession(sid);
                if (linkSession != null) {
                    Trace.info("[ProviderManager] unbind. sid:{}", sid);
                    //synchronized (linkSession) {
                    linkSession.removeProviderSession(p.pvid);
                    //}
                }
            }
        } else {
            ProviderSessionMap.removeDefaultProviderSession(p.pvid);
        }
    }
}

