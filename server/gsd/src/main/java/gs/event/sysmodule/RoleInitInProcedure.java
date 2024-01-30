package gs.event.sysmodule;

import pcore.event.Event;

public class RoleInitInProcedure extends Event {
    public final long roleId;
    public final long userId;
    public final String accountId;

    public RoleInitInProcedure(long roleId, long userId, String accountId) {
        this.roleId = roleId;
        this.userId = userId;
        this.accountId = accountId;
    }
}
