package gs.event.net;

import gs.net.link.LinkUser;
import msg.plink.LUserOnline;
import pcore.event.Event;

/**
 * Created by zyao on 2020/2/12 11:31
 */
public class SessionEstablish extends Event {
    public final LinkUser linkUser;
    public final LUserOnline p;

    public SessionEstablish(LUserOnline p, LinkUser linkUser) {
        this.p = p;
        this.linkUser = linkUser;
    }
}
