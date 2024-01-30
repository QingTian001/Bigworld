package map.net.link;

import map.cfg.BootConfig;
import msg.net.GClientAnnouceServerInfo;
import msg.net.GServerAnnouceServerInfo;
import msg.plink.LAnnounceOuterNetAddress;
import pcore.io.Client;
import pcore.io.DynamicMultiClientManager;
import pcore.io.ProtocolDispatcher;
import pcore.io.Session;


/**
 * Created by zyao on 2020/3/21 20:31
 */
public class LinkManager extends DynamicMultiClientManager {

    private static LinkManager ins;
    private static final ProtocolDispatcher dispatcher = new ProtocolDispatcher();

    public static LinkManager getInstance() {
        return ins;
    }

    public static ProtocolDispatcher getDispatcher() {
        return dispatcher;
    }

    public static void start(Conf conf) {
        if (ins == null) {
            ins = new LinkManager();
            ins.updateServers(conf.getServers());
        }
    }

    @Override
    protected DynamicClient newClient(String name, Client.Conf config) {
        return new LinkClient(this, name, config);
    }

    private LinkManager() {
        dispatcher.register(GServerAnnouceServerInfo.class, this::process);
        dispatcher.register(LAnnounceOuterNetAddress.class, p -> {});
    }

    @Override
    protected void onAddClient(DynamicClient client) {
        super.onAddClient(client);
        GClientAnnouceServerInfo p = new GClientAnnouceServerInfo(BootConfig.getIns().getServerId());
        client.send(p);
    }

    private void process(GServerAnnouceServerInfo p) {
        register(p.serverId, (DynamicClient) ((Session)p.getContext()).getConnection().getManager(), p.keepAliveInterval);
    }
}
