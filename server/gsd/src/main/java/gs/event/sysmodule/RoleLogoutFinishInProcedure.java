package gs.event.sysmodule;

import pcore.event.Event;

/**
 * Created by zyao on 2020/4/2 16:14
 */
public class RoleLogoutFinishInProcedure extends Event {
    public final long roleId;
    private final boolean isReconnect;

    public RoleLogoutFinishInProcedure(long roleId, boolean isReconnect) {
        this.roleId = roleId;
        this.isReconnect = isReconnect;
    }
}
