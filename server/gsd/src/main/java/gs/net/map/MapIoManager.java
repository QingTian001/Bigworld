package gs.net.map;

import com.google.gson.JsonObject;
import gs.cfg.BootConfig;
import msg.gmap.MGMessage;
import msg.net.GClientAnnouceServerInfo;
import msg.net.GServerAnnouceServerInfo;
import pcore.collection.Int2ObjectHashMap;
import pcore.collection.IntList;
import pcore.io.*;

/**
 * Created by zyao on 2020/3/21 17:49
 */
public class MapIoManager extends DynamicMultiClientManager {
    private static MapIoManager inst;
    private static ProtocolDispatcher dispatcher = new ProtocolDispatcher();
    private final Conf conf;
    private final Int2ObjectHashMap<MapIo> lineId2MapIos = new Int2ObjectHashMap<>();

    public static MapIoManager getInst() {
        return inst;
    }

    public static void start(Conf conf) {
        if (inst == null) {
            inst = new MapIoManager(conf);
            inst.updateServers(conf.getServers());
        }
    }

    public MapIoManager(Conf conf) {
        this.conf = conf;
        dispatcher.register(GServerAnnouceServerInfo.class, this::process);
        dispatcher.register(MGMessage.class, this::process);
    }

    protected DynamicClient newClient(String name, Client.Conf config) {
        return new MapIo(this, name, config);
    }

    @Override
    protected void onAddClient(DynamicClient client) {
        super.onAddClient(client);
        GClientAnnouceServerInfo p = new GClientAnnouceServerInfo(BootConfig.getIns().getServerId());
        client.send(p);
        MapConf mapConf = (MapConf)client.getConf();
        lineId2MapIos.put(mapConf.lineId, (MapIo)client);
    }

    @Override
    protected void onDelClient(DynamicClient client) {
        super.onDelClient(client);
        MapConf mapConf = (MapConf)client.getConf();
        lineId2MapIos.remove(mapConf.lineId);
    }

    public static ProtocolDispatcher getDispatcher() {
        return dispatcher;
    }

    public MapIo getMapIo(int lineId) {
        return lineId2MapIos.get(lineId);
    }

    private void process(GServerAnnouceServerInfo p) {
        register(p.serverId, (MapIo)((MapIoSession)p.getContext()).getConnection().getManager(), p.keepAliveInterval);
    }

    private void process(MGMessage p) {
        long mapId = p.mapId;
        Protocol msg = ProtocolUtils.decodeFromBytes(p.data, conf.getStubs());
        //new MapProtocolReceive(mapId, msg).trigger();
    }

    public static class Conf extends DynamicMultiClientManager.Conf {

        private final Int2ObjectHashMap<MapConf> lineId2MapConfs = new Int2ObjectHashMap<>();
        private final IntList lineIdList = new IntList();

        public final IntList getLineIdList() {
            return lineIdList;
        }

        @Override
        public void addConf(DynamicClient.Conf c) {
            super.addConf(c);
            MapConf mapConf = (MapConf)c;
            lineId2MapConfs.put(mapConf.lineId, mapConf);
            lineIdList.add(mapConf.lineId);
        }

        @Override
        public Client.Conf createConf() {
            return new MapConf();
        }
    }

    public static final class MapConf extends Client.Conf {
        private int lineId;
        @Override
        public void parse(JsonObject jo) {
            super.parse(jo);
            lineId = jo.get("lineId").getAsInt();
        }

        public int getLineId() {
            return lineId;
        }
    }
}
