package gs.net.link;

import pcore.io.Connection;
import pcore.io.Session;

/**
 * Created by zyao on 2020/3/26 17:02
 */
public class LinkSession extends Session {
    public LinkSession(Connection connection) {
        super(connection);
    }
}
