package gs.net;


import gs.net.aud.AudClient;
import gs.net.link.LinkManager;

import gs.net.map.MapIoManager;
import gs.net.serverlist.ServerListManager;
import pcore.collection.Int2ObjectHashMap;
import gs.cfg.BootConfig;
import io.netty.channel.nio.NioEventLoopGroup;
import msg.Refs;

public enum Module {
    Ins;

    public void start() {
        NioEventLoopGroup worker = new NioEventLoopGroup();
        BootConfig conf = BootConfig.getIns();
        {
            LinkManager.Conf linkConf = conf.getLinkConf();
            linkConf.init(worker, new Int2ObjectHashMap<>(Refs.plink), LinkManager.getDispatcher());
            LinkManager.start(linkConf);
        }

        {
            MapIoManager.Conf mapConf = conf.getMapConf();
            mapConf.init(worker, new Int2ObjectHashMap<>(Refs.gmap), MapIoManager.getDispatcher());
            MapIoManager.start(mapConf);
        }

//        {
//            var config = conf.getAudConf();
//            config.init(worker, AudClient.getDispatcher(), new Int2ObjectHashMap<>(Refs.gsau));
//            AudClient.start(config);
//        }
//        {
//            var config = conf.getServerListConf();
//            config.init(worker, new Int2ObjectHashMap<>(Refs.serverlist), ServerListManager.getDispatcher());
//            ServerListManager.start(config);
//        }

    }
}
