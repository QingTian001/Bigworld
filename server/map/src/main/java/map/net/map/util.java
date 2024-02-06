package map.net.map;

import map.cfg.BootConfig;
import map.mapmodule.map.MapId;
import map.net.gs.GsManager;
import map.net.map.client.MapClientManager;
import map.net.map.server.MapServerManager;
import pcore.io.Protocol;
import pcore.io.ProtocolDispatcher;

public class util {

    public static final ProtocolDispatcher dispatcher = new ProtocolDispatcher();

    public static void sendMMapProtocol(MapId mapId, Protocol p) {
        if (!GsManager.mapId2LineIds.containsKey(mapId)) {
            throw new RuntimeException("mapId not exists:" + mapId);
        }

        int lineId = GsManager.mapId2LineIds.get(mapId);
        if (lineId == BootConfig.getIns().getLineId()) {
            throw new RuntimeException();
        }
        else if (lineId < BootConfig.getIns().getLineId()) {
            MapServerManager.getInstance().send(MapServerManager.getInstance().lineId2SessionMap.get(lineId).getSessionId(), p);
        } else {
            MapClientManager.getInst().send(MapClientManager.getInst().lineId2ServerId.get(lineId), p);
        }
    }
}
