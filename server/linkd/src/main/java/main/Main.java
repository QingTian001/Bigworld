package main;

import auth.AuthConf;
import auth.AuthManager;
import com.mafia.serverex.metrics.MetricsHelper;
import gm.GmModule;
import io.netty.channel.nio.NioEventLoopGroup;
import link.LinkConf;
import link.LinkServer;
import msg.Refs;
import org.apache.log4j.PropertyConfigurator;
import pcore.collection.Int2ObjectHashMap;
import provider.ProviderConf;
import provider.ProviderServer;

/**
 * Created by zyao on 2020/2/9 11:38
 */
public class Main {

    private static void usage() {
        System.out.println("usage:  java -cp link.jar link.Main link.json");
        System.exit(1);
    }

    private static void startNetwork() {

        NioEventLoopGroup bossEventLoopGroup = new NioEventLoopGroup(2);
        NioEventLoopGroup workerEventLoopGroup = new NioEventLoopGroup();

        LinkConf linkConf = BootConfig.getIns().getLinkConf();
        linkConf.bossGroup = bossEventLoopGroup;
        linkConf.workGroup = workerEventLoopGroup;
        linkConf.stubs =  new Int2ObjectHashMap<>(Refs.clink);
        linkConf.dispatcher = LinkServer.getDispatcher();
        LinkServer.start(BootConfig.getIns().getLinkConf());

//        AuthConf authConf = BootConfig.getIns().getAuthConf();
//        authConf.init(workerEventLoopGroup, new Int2ObjectHashMap<>(Refs.linkau), AuthManager.getDispatcher());
//        AuthManager.start(authConf);

        ProviderConf providerConf = BootConfig.getIns().getProviderConf();
        providerConf.bossGroup = bossEventLoopGroup;
        providerConf.workGroup = workerEventLoopGroup;
        providerConf.stubs = new Int2ObjectHashMap<>(Refs.plink);
        providerConf.dispatcher = ProviderServer.getDispatcher();
        ProviderServer.start(providerConf);
    }

    public static void main(String[] args) throws Exception {
        PropertyConfigurator.configure("log4j.properties");
        BootConfig.getIns().load(args.length == 1 ? args[0] : "link.json");

        GmModule.start();
        MetricsHelper.start(BootConfig.getIns().getMetricsConf());
        startNetwork();
    }
}
