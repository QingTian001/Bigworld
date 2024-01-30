package map.mapmodule.msg;

import com.mafia.serverex.metrics.MetricsHelper;
import map.cfg.BootConfig;
import map.mapmodule.role.Role;
import map.util.ExceptionUtil;
import pcore.io.Protocol;

/**
 * Created by zyao on 2020/3/27 10:36
 */
public abstract class LinkMsgHandler<T extends Protocol> extends MsgHandler<T> {

    private Role role;
    private static long receiveCount = 0;

    @SuppressWarnings("unchecked")
    void setContext(Role role, Protocol p) {
        this.role = role;
        this.p = (T)p;
    }

    @Override
    public void run() {
        try {
            MetricsHelper.getCounter("map_link_receive_count").set(++receiveCount);
            doProcess(role, p);
        }
        catch (Throwable t) {
            if (BootConfig.getIns().isDebug()) {
                role.sendProtocol(ExceptionUtil.toProtocol(t));
            }
            throw t;
        }

    }

    protected abstract void doProcess(Role role, T p);
}
