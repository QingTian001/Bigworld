package gs.net.link;

import pcore.io.Connection;
import pcore.io.Session;


public class LinkClient extends pcore.io.DynamicMultiClientManager.DynamicClient {

    public LinkClient(LinkManager manager, String name, Conf conf) {
        super(manager, name, conf);
    }

    @Override
    protected Session newSession(Connection conn) {
        return new LinkSession(conn);
    }
}
