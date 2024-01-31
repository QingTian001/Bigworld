package gs.sysmodule.server;

import cfg.CfgMgr;
import gs.cfg.BootConfig;
import gs.event.sysmodule.StartServerSuccess;
import gs.net.serverlist.ServerListManager;
import gs.util.GProcedure;
import gs.util.ProcedureUtil;
import msg.serverlist.GAnnouceGsServerStart;
import pcore.db.Trace;
import pcore.event.EventHandler;
import pcore.io.DynamicMultiClientManager;
import pcore.misc.IModule;
import xtable.Server;

/**
 * Created by zhanghuaizheng on 2020/9/21 11:05
 */
public enum ServerModule implements IModule {
    Ins;

    public final static int SERVER_INFO_KEY = 0;

    private volatile long startTimeMills = 0;
    private volatile long serverFirstStartTimeMills = 0;

    @Override
    public void start() {

    }

    public void tryExecuteServerOpen(long startTimeMills) {
        if (!new GProcedure() {
            @Override
            protected boolean doProcess() {
                if (xtable.Server.get(SERVER_INFO_KEY) == null) {
                    Trace.info("save serverStartMills:" + startTimeMills);
                    xtable.Server.put(SERVER_INFO_KEY, new xbean.ServerInfo(startTimeMills));

                }
                xbean.ServerInfo serverInfo = xtable.Server.get(SERVER_INFO_KEY);
                final long firstStartMills = serverInfo.getStartTimeMills();
                ProcedureUtil.executeWhileSucc(() -> {
                    serverFirstStartTimeMills = firstStartMills;
                });
                return true;
            }
        }.call()) {
            throw new RuntimeException("try save server start time failed");
        }
    }

    @EventHandler
    void onEvent(StartServerSuccess event) {
        this.startTimeMills = event.startTime;
        sendAnnouceGsServerStart2ServerList();
    }

    public final long getServerFirstStartTimeMills() {
        if (serverFirstStartTimeMills == 0) {
            throw new RuntimeException("server not started fully. Cant get serverFirstStartTimeMills");
        }
        return serverFirstStartTimeMills;
    }

    /**
     * 如果gs启动成功前建立连接，则不用发送数据，因为gs启动成功后会统一广播
     * 如果gs启动成功后建立连接，则建立连接时会通知serverList
     * 极端情况，会存在serverlist收到两次协议的情况，但是不影响。
     */
    public void sendAnnouceGsServerStart2ServerList() {
        if (startTimeMills > 0 && serverFirstStartTimeMills > 0) {
            //ServerListManager.getInst().broadcast(makeGAnnouceGsServerStart());
        }
    }

    public void sendAnnouceGsServerStart2Server(DynamicMultiClientManager.DynamicClient client) {
        if (startTimeMills > 0 && serverFirstStartTimeMills > 0) {
            client.send(makeGAnnouceGsServerStart());
        }
    }

    private GAnnouceGsServerStart makeGAnnouceGsServerStart() {
        GAnnouceGsServerStart snd = new GAnnouceGsServerStart();
        snd.serverId = BootConfig.getIns().getServerId();
        snd.startTime = startTimeMills;
        snd.firstStartTime = serverFirstStartTimeMills;
        return snd;
    }
}
