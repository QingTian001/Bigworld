package map.mapmodule.msg;

import map.cfg.BootConfig;
import map.mapmodule.Module;
import map.mapmodule.map.GMap;
import map.net.gs.GsSession;
import map.util.ExceptionUtil;
import msg.gmap.*;
import pcore.db.Trace;
import pcore.io.Protocol;
import pcore.io.ProtocolUtils;

/**
 * Created by zyao on 2020/3/25 17:51
 */
public abstract class GsMapMsgHandler<T extends Protocol> extends GsMsgHandler<T> {

    @Override
    public void doProcess(long mapId, GsSession gsSession, T p) {

        GMap map = Module.Ins.getMap(mapId);
        if (map == null) {
            if(p instanceof GCheckBattle) {
                var respond = new MCheckBattle(false, false, -1L);
                gsSession.send(new MGMessage(mapId, ProtocolUtils.encode2Bytes(respond)));
            }else if(p instanceof GQuitStage){
                var respond = new MQuitStage(-1,0);
                gsSession.send(new MGMessage(mapId, ProtocolUtils.encode2Bytes(respond)));
            }else {
                Trace.warn("map not exists. mapId:{}", mapId);
            }
            return;
        }
        try {
            doProcess(map, gsSession, p);
        }
        catch (Throwable t) {
            if (BootConfig.getIns().isDebug()) {
                map.broadcast(ExceptionUtil.toProtocol(t));
            }
            throw t;
        }
    }

    protected abstract void doProcess(GMap map, GsSession gsSession, T p);
}
