package gs.event.net;

import pcore.event.Event;

public class SessionBreak extends Event {
    public final long roleId;
    public final long userId;
    public final int reason;

    public SessionBreak(long userId, long roleId, int reason) {
        this.userId = userId;
        this.roleId = roleId;
        this.reason = reason;
    }
}
