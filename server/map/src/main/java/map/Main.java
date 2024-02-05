package map;

import cfg.CfgMgr;
import com.mafia.serverex.metrics.MetricsHelper;
import map.cfg.BootConfig;
import map.mapmodule.Module;
import org.apache.log4j.PropertyConfigurator;
import pcore.db.Trace;

/**
 * Created by zyao on 2020/3/21 20:22
 */
public class Main {

    private static void usage() {
        System.out.println("Usage: java -cp map.jar main.Main map.json");
        System.exit(-1);
    }

    private static String getConfigFile(String[] args) {
        switch (args.length) {
            case 0:
                return "map.json";
            case 1:
                return args[0];
            default:
                usage();
                return "";
        }
    }

    public static void main(String[] args) {
        try {
            var conf = BootConfig.getIns();
            conf.load(getConfigFile(args));
            PropertyConfigurator.configure(conf.getLog4jFile());

            CfgMgr.setDir(conf.getDataDir());
            CfgMgr.load();

            map.net.Module.Ins.start();
            Module.Ins.start();

            Trace.info("map server started");
        }
        catch (Throwable t) {
            t.printStackTrace();
            System.exit(-1);
        }
    }
}
