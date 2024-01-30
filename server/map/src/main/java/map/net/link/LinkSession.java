package map.net.link;

import pcore.io.Connection;
import pcore.io.Session;

/**
 * Created by zyao on 2020/3/26 14:21
 */
public class LinkSession extends Session {
    public LinkSession(Connection connection) {
        super(connection);
    }
}
