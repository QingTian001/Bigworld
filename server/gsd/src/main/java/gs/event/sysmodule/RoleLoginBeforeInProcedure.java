package gs.event.sysmodule;

import pcore.event.Event;

/**
 * Created by zhanghuaizheng on 2021/3/31 10:30
 */
public class RoleLoginBeforeInProcedure extends Event {
    public final long roleId;
    public final boolean isReconnect;

    public RoleLoginBeforeInProcedure(long roleId, boolean isReconnect) {
        this.roleId = roleId;
        this.isReconnect = isReconnect;
    }
}
