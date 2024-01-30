package provider;

import pcore.io.Connection;
import pcore.io.Session;

/**
 * Created by zyao on 2020/2/10 11:17
 */
public class ProviderSession extends Session {
    public ProviderSession(Connection connection) {
        super(connection);
    }
}
