package map.mapmodule.msg;

import map.net.link.LinkSession;

/**
 * Created by zyao on 2020/3/27 11:35
 */
public final class LinkMsgContext {
    private final long linkSid;
    private final LinkSession linkSession;

    public LinkMsgContext(long linkSid, LinkSession linkSession) {
        this.linkSid = linkSid;
        this.linkSession = linkSession;
    }

    public long getLinkSid() {
        return linkSid;
    }

    public LinkSession getLinkSession() {
        return linkSession;
    }
}
