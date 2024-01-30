package map.net.link;

import pcore.io.Connection;
import pcore.io.DynamicMultiClientManager;
import pcore.io.Session;

/**
 * Created by zyao on 2020/3/21 20:31
 */
public class LinkClient extends DynamicMultiClientManager.DynamicClient {
    public LinkClient(DynamicMultiClientManager manager, String name, Conf conf) {
        super(manager, name, conf);
    }

    @Override
    protected Session newSession(Connection conn) {
        return new LinkSession(conn);
    }
}
