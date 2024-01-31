package gs.net.map;

import pcore.io.Connection;
import pcore.io.DynamicMultiClientManager;
import pcore.io.Session;

/**
 * Created by zyao on 2020/3/21 17:41
 */

public class MapIo extends DynamicMultiClientManager.DynamicClient {
    public MapIo(DynamicMultiClientManager manager, String name, Conf conf) {
        super(manager, name, conf);
    }

    @Override
    protected Session newSession(Connection conn) {
        return new MapIoSession(conn);
    }
}
