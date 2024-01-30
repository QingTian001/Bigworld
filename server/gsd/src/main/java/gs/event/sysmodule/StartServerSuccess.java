package gs.event.sysmodule;

import pcore.event.Event;

public class StartServerSuccess extends Event {
    public final long startTime;

    public StartServerSuccess(long startTime) {
        this.startTime = startTime;
    }
}
