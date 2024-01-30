package auth;

import link.LinkConf;
import link.LinkServer;
import link.LinkSession;
import main.BootConfig;
import msg.clink.SLoginAccountCheck;
import msg.clink.SLoginTokenCheck;

import msg.linkau.*;
import msg.net.EErrorCode;
import msg.net.GClientAnnouceServerInfo;
import msg.net.GServerAnnouceServerInfo;
import msg.plink.LUserOnline;
import pcore.db.Trace;
import pcore.io.Dispatcher;
import pcore.io.DynamicMultiClientManager;
import pcore.io.ProtocolDispatcher;
import pcore.io.Session;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 * Created by zyao on 2020/2/9 11:53
 */
@SuppressWarnings("SynchronizationOnLocalVariableOrMethodParameter")
public final class AuthManager extends DynamicMultiClientManager {
    private static AuthManager inst;
    private static ProtocolDispatcher dispatcher = new ProtocolDispatcher();

    public static AuthManager getInst() {
        return inst;
    }

    public static Dispatcher getDispatcher() {
        return dispatcher;
    }

    public static void start(AuthConf conf) {
        if (inst == null) {
            inst = new AuthManager();
            inst.open();
            inst.updateServers(conf.getServers());
        }
    }

    private AuthManager() {
        dispatcher.register(ALoginAccountCheck.class, this::process);
        dispatcher.register(ATokenCheck.class, this::process);
        dispatcher.register(GServerAnnouceServerInfo.class, this::process);
    }

    @Override
    protected void onAddClient(DynamicMultiClientManager.DynamicClient client) {
        super.onAddClient(client);
        GClientAnnouceServerInfo p = new GClientAnnouceServerInfo(BootConfig.getIns().getServerId());
        client.send(p);


        if (BootConfig.getIns().getAuthConf().getReportLinkPublicAddr()) {
            LinkConf conf = BootConfig.getIns().getLinkConf();
            client.send(new LAnnounceOuterNetAddress(conf.getPublicIp(),
                    conf.getPublicIpv6(), conf.port, conf.getPublicIsBackup()));
        }
    }

    public DynamicClient getRandomAuth() {
        List<DynamicClient> authList = AuthManager.getInst().getServerAsList();
        return authList.get(ThreadLocalRandom.current().nextInt(authList.size()));
    }

    private int transAccountCheckState(int accountCheckState) {
        switch (accountCheckState) {
            case CheckState.OK: {
                return EErrorCode.OK;
            }
            case CheckState.FAIL: {
                return EErrorCode.AUTH_FAIL;
            }
            default: {
                throw new RuntimeException("error account check state:" + accountCheckState);
            }
        }
    }


    private void process(ALoginAccountCheck p) {
        LinkSession linkSession = LinkServer.getInst().getSession(p.sid);
        if (linkSession != null) {
            synchronized (linkSession) {
                if (linkSession.getState() != LinkSession.STATE.RECV_ACCOUNT_CHECK) {
                    Trace.error("AuthManager receive ALoginAccountCheck with error linkSession state");
                    return;
                }
                linkSession.setUserId(p.uid);
                linkSession.send(new SLoginAccountCheck(transAccountCheckState(p.state), p.token, p.uid, p.overdueTime, p.extra));
                linkSession.setState(LinkSession.STATE.SEND_ACCOUNT_CHECK_RESULT);
            }
        }
    }

    private void process(ATokenCheck p) {
        LinkSession linkSession = LinkServer.getInst().getSession(p.sid);
        if (linkSession != null) {
            synchronized (linkSession) {
                if (linkSession.getState() != LinkSession.STATE.RECV_TOKEN_CHECK) {
                    Trace.error("AuthManager receive ATokenCheck with error linkSession state");
                    return;
                }
                int errCode = transAccountCheckState(p.state);
                if (errCode != EErrorCode.OK) {
                    linkSession.send(new SLoginTokenCheck(errCode, linkSession.getUserId()));
                    linkSession.setState(LinkSession.STATE.NONE);
                    return;
                }
                var addr = linkSession.getConnection().getRemoteSocketAddress();
                linkSession.forwardToGs(new LUserOnline(linkSession.getUserId(), linkSession.getSessionId(),
                        linkSession.isReconnect(), p.accountId, addr.getAddress().getHostAddress(), linkSession.getExtraInfo()));
                linkSession.setState(LinkSession.STATE.LOGIN);
            }
        }
    }

    private void process(GServerAnnouceServerInfo p) {
        register(p.serverId, (DynamicClient)((Session) p.getContext()).getConnection().getManager(), p.keepAliveInterval);
    }
}
