package map.event;

import map.net.gs.GsSession;
import pcore.event.Event;

public class GsConnectedEvent extends Event {

    public final GsSession gsSession;
    public GsConnectedEvent(GsSession gsSession) {
        this.gsSession = gsSession;
    }
}
