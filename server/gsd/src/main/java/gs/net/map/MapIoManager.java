package gs.net.map;

import com.google.gson.JsonObject;
import gs.cfg.BootConfig;
import msg.gmap.*;
import msg.net.GClientAnnouceServerInfo;
import msg.net.GServerAnnouceServerInfo;
import pcore.collection.Int2ObjectHashMap;
import pcore.collection.IntList;
import pcore.io.*;

import java.util.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * Created by zyao on 2020/3/21 17:49
 */
public class MapIoManager extends DynamicMultiClientManager {
    private static MapIoManager inst;
    private static ProtocolDispatcher dispatcher = new ProtocolDispatcher();
    private final Conf conf;
    private final Int2ObjectHashMap<MapIo> lineId2MapIos = new Int2ObjectHashMap<>();

    private final Map<MapIo, Integer> mapIo2LineIdMap = new HashMap<>();

    public static class MapId {
        public final int x;
        public final int y;

        public MapId(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public MapId(MapInfo mapInfo) {
            this.x = mapInfo.x;
            this.y = mapInfo.y;
        }
    }

    private final Map<Integer, MapServerInfo> lineId2MapServerInfo = new ConcurrentHashMap<>();

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
        dispatcher.register(MGMapInfos.class, this::process);
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
        mapIo2LineIdMap.put((MapIo)client, mapConf.lineId);
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

    private void process(MGMapInfos p) {
        MapIo io = (MapIo)((MapIoSession)p.getContext()).getConnection().getManager();

        List<MapId> mapIds = new ArrayList<>();
        lineId2MapServerInfo.put(mapIo2LineIdMap.get(io), p.mapServerInfo);

        GMMapInfosNotify notify = new GMMapInfosNotify();
        notify.mapServerInfos.putAll(lineId2MapServerInfo);

        for (DynamicClient manager : this.getServers().values()) {
            manager.send(notify);
        }
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
