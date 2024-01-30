package map.mapmodule.msg;

import map.net.gs.GsSession;
import pcore.io.Protocol;

/**
 * Created by zyao on 2020/3/25 17:51
 */
public abstract class GsMsgHandler<T extends Protocol> extends MsgHandler<T> {

    private long mapId;
    private GsSession gsSession;

    @SuppressWarnings("unchecked")
    void setContext(long mapId, GsSession gsSession, Protocol p) {
        this.p = (T)p;
        this.mapId = mapId;
        this.gsSession = gsSession;
    }

    @Override
    public void run() {
        doProcess(mapId, gsSession, p);
    }

    protected abstract void doProcess(long mapId, GsSession gsSession, T p);
}
