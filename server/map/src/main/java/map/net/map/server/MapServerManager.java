package map.net.map.server;

import com.google.gson.JsonObject;
import map.cfg.BootConfig;
import map.net.map.util;
import map.util.CfgReload;
import msg.gmap.GCfgReload;
import msg.mmap.MCLineIdNotify;
import msg.net.GClientAnnouceServerInfo;
import msg.net.GServerAnnouceServerInfo;
import pcore.io.Connection;
import pcore.io.ProtocolDispatcher;
import pcore.io.Server;
import pcore.io.Session;

import java.util.concurrent.ConcurrentHashMap;

public class MapServerManager extends Server<Session> {

    private static MapServerManager ins;

    public ConcurrentHashMap<Integer, Session> lineId2SessionMap = new ConcurrentHashMap<>();


    public static MapServerManager getInstance() {
        return ins;
    }


    public static ProtocolDispatcher getDispatcher() {
        return util.dispatcher;
    }

    public static void start(Conf conf) {
        if (ins == null) {
            ins = new MapServerManager(conf);
            ins.open();
        }
    }

    private MapServerManager(Conf conf) {
        super(conf);
        getDispatcher().register(GClientAnnouceServerInfo.class, this::process);
        getDispatcher().register(MCLineIdNotify.class, this::process);

    }
    @Override
    protected void onOpen() {

    }

    @Override
    protected void onClose() {

    }

    @Override
    protected void onAddSession(Session gsSession) {
        GServerAnnouceServerInfo p = new GServerAnnouceServerInfo(BootConfig.getIns().getServerId(), getConf().expireTime / 2);
        gsSession.send(p);
    }

    private void process(GClientAnnouceServerInfo p) {
        register(p.serverId, (Session)p.getContext());
    }

    private void process(MCLineIdNotify p) {
        lineId2SessionMap.put(p.lineId, (Session)p.getContext());
    }


    public static class Conf extends Server.Conf {

        public String connectAddr;
        public void parse(JsonObject jo) {
            super.parse(jo);
            connectAddr = jo.get("connectAddr").getAsString();
        }
    }
}
