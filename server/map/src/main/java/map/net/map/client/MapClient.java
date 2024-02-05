package map.net.map.client;

import pcore.io.Connection;
import pcore.io.DynamicMultiClientManager;
import pcore.io.Session;



public class MapClient extends DynamicMultiClientManager.DynamicClient {
    public MapClient(DynamicMultiClientManager manager, String name, Conf conf) {
        super(manager, name, conf);
    }

    @Override
    protected Session newSession(Connection conn) {
        return new Session(conn);
    }
}
