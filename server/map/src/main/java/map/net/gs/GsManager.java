package map.net.gs;

import map.cfg.BootConfig;
import map.net.link.LinkManager;
import map.net.map.client.MapClient;
import map.net.map.client.MapClientManager;
import map.util.CfgReload;
import msg.Refs;
import msg.gmap.GCfgReload;
import msg.gmap.GMMapInfosNotify;
import msg.gmap.MapServerInfo;
import msg.net.GClientAnnouceServerInfo;
import msg.net.GServerAnnouceServerInfo;
import pcore.collection.Int2ObjectHashMap;
import pcore.db.Trace;
import pcore.io.Client;
import pcore.io.Connection;
import pcore.io.ProtocolDispatcher;
import pcore.io.Server;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zyao on 2020/3/22 19:53
 */
public class GsManager extends Server<GsSession> {

    private static GsManager ins;
    private static final ProtocolDispatcher dispatcher = new ProtocolDispatcher();

    public static GsManager getInstance() {
        return ins;
    }

    public static ProtocolDispatcher getDispatcher() {
        return dispatcher;
    }

    public static volatile ConcurrentHashMap<Integer, MapServerInfo> mapServerInfos = new ConcurrentHashMap<>();

    public static void start(Conf conf) {
        if (ins == null) {
            ins = new GsManager(conf);
            ins.open();
        }
    }

    private GsManager(Conf conf) {
        super(conf);
        dispatcher.register(GClientAnnouceServerInfo.class, this::process);
        dispatcher.register(GCfgReload.class, this::process);
        dispatcher.register(GMMapInfosNotify.class, this::process);

    }

    protected GsSession newSession(Connection conn) {
        return new GsSession(conn);
    }

    @Override
    protected void onOpen() {

    }

    @Override
    protected void onClose() {

    }

    @Override
    protected void onAddSession(GsSession gsSession) {
        GServerAnnouceServerInfo p = new GServerAnnouceServerInfo(BootConfig.getIns().getServerId(), getConf().expireTime / 2);
        gsSession.send(p);
    }

    private void process(GClientAnnouceServerInfo p) {
        register(p.serverId, (GsSession)p.getContext());
    }

    private void process(GCfgReload p) {
        CfgReload.getInstance().reload(p.version);
    }

    private void process(GMMapInfosNotify p) {
        for (var entry : p.mapServerInfos.entrySet()) {
            if (entry.getKey() == BootConfig.getIns().getLineId()) {
                continue;
            }
            MapServerInfo mapServerInfo = entry.getValue();
            if (mapServerInfos.putIfAbsent(entry.getKey(), mapServerInfo) == null) {


            }
        }

    }


    public static class Conf extends Server.Conf {

    }
}
