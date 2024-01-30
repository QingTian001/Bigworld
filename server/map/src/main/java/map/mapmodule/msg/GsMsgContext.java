package map.mapmodule.msg;

import map.net.gs.GsSession;

/**
 * Created by zyao on 2020/3/27 9:30
 */
public final class GsMsgContext {
    private final long mapId;
    private final GsSession gsSession;

    public GsMsgContext(long mapId, GsSession gsSession) {
        this.mapId = mapId;
        this.gsSession = gsSession;
    }

    public GsSession getGsSession() {
        return gsSession;
    }

    public long getMapId() {
        return mapId;
    }
}
