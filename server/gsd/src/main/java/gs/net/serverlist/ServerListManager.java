package gs.net.serverlist;

import cfg.CfgMgr;
import com.google.gson.JsonObject;
import gs.cfg.BootConfig;
import gs.net.link.LinkManager;
import gs.net.link.LinkManager.LinkAddr;
import gs.sysmodule.server.ServerModule;
import gs.util.IPUtil;
import gs.util.timer.Timer;
import io.netty.util.internal.StringUtil;
import lombok.Builder;
import msg.Refs;
import msg.net.GClientAnnouceServerInfo;
import msg.net.GServerAnnouceServerInfo;
import msg.plink.LUserOnline;
import msg.serverlist.*;
import pcore.collection.Int2ObjectHashMap;
import pcore.collection.LongConcurrentHashMap;
import pcore.db.Trace;
import pcore.io.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by zhanghuaizheng on 2020/3/26 16:24
 */
public class ServerListManager extends DynamicMultiClientManager {

    private Timer timer;
    private static ServerListManager inst;
    private static ProtocolDispatcher dispatcher = new ProtocolDispatcher();
    private BootConfig bootConfig = BootConfig.getIns();
    private static Int2ObjectHashMap<GAnnouceGsServerInfo> serverInfos = new Int2ObjectHashMap<>();
    private static final Map<String, LinkAddr> linkAddrs = new ConcurrentHashMap<>();
    private static final Int2ObjectHashMap<IProtocolFactory> stubs = new Int2ObjectHashMap<>();

    public static ServerListManager getInst() {
        return inst;
    }

    public static ProtocolDispatcher getDispatcher() {
        return dispatcher;
    }
    public static void start(ServerListConf conf) {
        if (inst == null) {
            inst = new ServerListManager();
            inst.open();
            inst.updateServers(conf.getServers());
        }
    }

    private ServerListManager() {
        dispatcher.register(GServerAnnouceServerInfo.class, this::process);
        dispatcher.register(S2GCustomServiceForward.class, this::process);
        stubs.putAll(Refs.serverlist);
    }

    private void process(GServerAnnouceServerInfo msg) {
        Trace.info("Connect ServerList serverId: {} success!", msg.serverId);
        DynamicClient client = (DynamicClient)((Session) msg.getContext()).getConnection().getManager();
        register(msg.serverId, client, msg.keepAliveInterval);
        //order to serverlist restart sych linkinfo
        sychLinkInfo(msg.serverId);

        ServerModule.Ins.sendAnnouceGsServerStart2Server(client);
    }

    private void process(S2GCustomServiceForward msg) {

        Protocol p = ProtocolUtils.decodeFromBytes(msg.data, stubs);
        Session session = (Session) msg.getContext();

        Trace.info("[ServerListManager] receive S2GCustomServiceForward request. id:{}", msg.id);
        CustomServiceContext context = new CustomServiceContext(session, msg.id);
        p.setContext(context);
        dispatcher.dispatch(p);
    }

    @Override
    protected void onAddClient(DynamicMultiClientManager.DynamicClient client) {
        super.onAddClient(client);
        var p = new GClientAnnouceServerInfo(bootConfig.getServerId());
        client.send(p);

        var proto = new GAnnouceGsServerlocalId(bootConfig.getServerId(), bootConfig.getLocalId());
        client.send(proto);
        var serverListConf = bootConfig.getServerListConf();
        synchronized (this) {
            if (getTimer() == null) {
                Timer timer = Timer.schedulePeriod(serverListConf.synchServerRoleNumIntervalInMillis,
                        serverListConf.synchServerRoleNumIntervalInMillis,
                        new Timer.TimerHandler() {
                            @Override
                            public void onTimer() {
                                Trace.debug("synchServerRoleNumTask Cycle send====");
                                synchServerInfo2ServerList();
                            }
                        });
                setTimer(timer);
            }
        }
    }

    private void synchServerInfo2ServerList() {
        Trace.debug(" do synchServerInfo2ServerList task ");
        GAnnouceGsServerInfo msg = new GAnnouceGsServerInfo();
        msg.serverId = bootConfig.getServerId();
        msg.onlineRoleNum = gs.sysmodule.login.Module.INS.getOnlineRoles().size();
        //msg.registerRoleNum = Caches.getInstance().getRoleCacheMap().size();

        for (var it = getServerAsList().iterator(); it.hasNext(); ) {
            DynamicClient client = it.next();
            GAnnouceGsServerInfo info = serverInfos.get(client.getServerId());
            if (info == null || info.onlineRoleNum != msg.onlineRoleNum
                    || info.registerRoleNum != msg.registerRoleNum) {
                serverInfos.put(client.getServerId(), msg);
                client.send(msg);
            }
        }
    }

    @Override
    protected void onDelClient(DynamicClient client) {
        super.onDelClient(client);
        serverInfos.remove(client.getServerId());

        if (getServerAsList().isEmpty()) {
            setTaskFutureToNull();
        }
    }

    private void sychLinkInfo(int serverId) {
        linkAddrs.values().forEach(a -> sychLinkInfoMsg(serverId, a));
    }

    private void sychLinkInfoMsg(int serverId, LinkAddr linkAddr) {
        GAddGsServerLinkInfo linkInfoMsg = buildAddServerLinkInfoMsg(linkAddr);
        send(serverId, linkInfoMsg);
    }

    private GAddGsServerLinkInfo buildAddServerLinkInfoMsg(LinkAddr linkAddr) {
        var msg = new GAddGsServerLinkInfo();
        msg.serverId = bootConfig.getServerId();
        msg.localId = bootConfig.getLocalId();
        msg.innerIp = linkAddr.innerIp;
        msg.innerPort = linkAddr.innerPort;
        msg.outerIp = linkAddr.outerIp;
        msg.outerIpv6 = linkAddr.outerIpv6;
        msg.outerPort = linkAddr.outerPort;
        msg.isBackup = linkAddr.isBackup;
        return msg;
    }

    public void addServerLinkInfo(LinkAddr linkAddr) {
        Trace.info(" do addServerLink: {}", linkAddr.toString());
        GAddGsServerLinkInfo linkInfoMsg = buildAddServerLinkInfoMsg(linkAddr);
        broadcast(linkInfoMsg);
    }

    public void rmvServerLinkInfo(String innerIp, int innerPort) {
        Trace.info(" do rmvServerLink innerIp={}, innerPort={}", innerIp, innerPort);
        var msg = new GRmvGsServerLinkInfo();
        msg.serverId = bootConfig.getServerId();
        msg.innerIp = innerIp;
        msg.innerPort = innerPort;
        broadcast(msg);
    }


    public static void removeLinkInfo(String innerIp, int innerPort) {
        linkAddrs.remove(LinkAddr.makeKey(innerIp, innerPort));
        //ServerListManager.getInst().rmvServerLinkInfo(innerIp, innerPort);
    }

    public static void addLinkInfo(LinkAddr linkAddr) {
        linkAddrs.put(linkAddr.buildKey(), linkAddr);
        //ServerListManager.getInst().addServerLinkInfo(linkAddr);
    }

    private synchronized boolean setTaskFutureToNull() {
        Timer timer = getTimer();
        if (null != timer) {
            Trace.debug("=========Severlist Timer rmv======");
            timer.cancel();
            setTimer(null);
        }
        return true;
    }

    private synchronized Timer getTimer() {
        return timer;
    }

    private synchronized void setTimer(Timer timer) {
        this.timer = timer;
    }

    public void broadcast(Protocol p) {
        for (var it = getServerAsList().iterator(); it.hasNext(); ) {
            it.next().send(p);
        }
    }

    public void doRandomSend(Protocol p) {
        ArrayList<DynamicClient> servers = getServerAsList();
        DynamicClient client = servers.get(ThreadLocalRandom.current().nextInt(servers.size()));
        client.send(p);
    }

    public void reloadConf() {
        GReload reload = new GReload();
        broadcast(reload);
    }

    public void openStatistic() {
        GOpenStatistic openMsg = new GOpenStatistic();
        broadcast(openMsg);
    }

    public void closeStatistic() {
        GCloseStatistic closeMsg = new GCloseStatistic();
        broadcast(closeMsg);
    }

    public static class ServerListConf extends  DynamicMultiClientManager.Conf {
        public int synchServerRoleNumIntervalInMillis = 5000;

        @Override
        public void parse(JsonObject jo) {
            super.parse(jo);
            synchServerRoleNumIntervalInMillis = jo.get("synchServerRoleNumIntervalInMillis").getAsInt();
        }
    }
}
