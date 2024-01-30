package gs.event.sysmodule;

import gs.net.link.LinkUser;
import pcore.event.Event;

public class RoleLoginInProcedure extends Event {
    public final long roleId;
    public final LinkUser linkUser;
    public final boolean isReconnect;

    public RoleLoginInProcedure(long roleId, LinkUser linkUser, boolean isReconnect) {
        this.roleId = roleId;
        this.linkUser = linkUser;
        this.isReconnect = isReconnect;
    }
}
