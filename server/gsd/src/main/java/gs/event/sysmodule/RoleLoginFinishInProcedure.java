package gs.event.sysmodule;

import pcore.event.Event;

/**
 * Created by zyao on 2020/4/2 16:15
 */
public class RoleLoginFinishInProcedure extends Event {
    public final long roleId;
    private final boolean isReconnect;

    public RoleLoginFinishInProcedure(long roleId, boolean isReconnect) {
        this.roleId = roleId;
        this.isReconnect = isReconnect;
    }
}
