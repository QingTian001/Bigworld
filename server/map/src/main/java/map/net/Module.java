package map.net;

import io.netty.channel.nio.NioEventLoopGroup;
import map.cfg.BootConfig;
import map.net.gs.GsManager;
import map.net.link.LinkManager;
import msg.Refs;
import pcore.collection.Int2ObjectHashMap;

/**
 * Created by zyao on 2020/3/22 19:52
 */
public enum Module {
    Ins;

    public void start() {
        NioEventLoopGroup boss = new NioEventLoopGroup(1);
        NioEventLoopGroup worker = new NioEventLoopGroup();

        GsManager.Conf gsConf = BootConfig.getIns().getGsConf();
        gsConf.bossGroup = boss;
        gsConf.workGroup = worker;
        gsConf.dispatcher = GsManager.getDispatcher();
        gsConf.stubs = new Int2ObjectHashMap<>(Refs.gmap);
        GsManager.start(gsConf);

        LinkManager.Conf linkConf = BootConfig.getIns().getLinkConf();
        linkConf.init(worker, new Int2ObjectHashMap<>(Refs.plink), LinkManager.getDispatcher());
        LinkManager.start(linkConf);
    }
}
