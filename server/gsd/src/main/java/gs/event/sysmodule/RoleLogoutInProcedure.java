package gs.event.sysmodule;


import pcore.event.Event;

public class RoleLogoutInProcedure extends Event {
    public final long roleId;
    private final boolean isReconnect;

    public RoleLogoutInProcedure(long roleId, boolean isReconnect) {
        this.roleId = roleId;
        this.isReconnect = isReconnect;
    }
}
