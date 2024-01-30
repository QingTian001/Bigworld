package gs.net.serverlist;

import pcore.io.Session;

/**
 * Created by zyao on 2020/9/19 15:39
 */
public class CustomServiceContext {
    public final Session session;
    public final long id;

    public CustomServiceContext(Session session, long id) {
        this.session = session;
        this.id = id;
    }
}
