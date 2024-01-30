package gs;

import cfg.CfgMgr;
import com.mafia.serverex.bi.BIReporter;
import com.mafia.serverex.hotfix.ClassRedefiner;
import com.mafia.serverex.metrics.MetricsHelper;
import gs.cfg.BootConfig;
import gs.cfg.GameConfigValidator;
import gs.event.sysmodule.StartServerSuccess;
import gs.sysmodule.server.ServerModule;
import gs.time.TimeControllerManager;
import gs.time.timerange.TimeRangeControllerManager;
import gs.util.ExecutorUtil;
import gs.util.NameChecker;
import gs.util.ReflectionUtil;
import gs.util.statistic.StatisticUtil;
import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pcore.db.PerfectDb;
import pcore.db.Procedure;
import pcore.db.Trace;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.TimeZone;

public class Main {

    private volatile static boolean shutdown = false;

    private static String log4jFile = "log4j.properties";

    private static void usage() {
        System.out.println("Usage: java -cp gsd.jar gs.Main gs.json");
		
        System.exit(-1);
    }

    public static void onBeforeStop() {
        shutdown = true;
    }

    public static void onAfterStop() {
        ExecutorUtil.shutdown();
    }

    public static boolean isGsdShutDown() {
        return shutdown;
    }

    private static String getConfigFile(String[] args) {
        switch (args.length) {
            case 0:
                return "gs.json";
            case 1:
                return args[0];
            default:
                usage();
                return "";
        }
    }

    private static void initLog4j(String log4jFile) {
        PropertyConfigurator.configure(log4jFile);
    }

    private static void printError(Throwable t) {
        StringWriter sw = new StringWriter();
        t.printStackTrace(new PrintWriter(sw));

        Trace.error(sw.toString());
    }


    public static void main(String[] args) {
        try {

            initLog4j(log4jFile);
            Trace.info("begin load conf");
            var conf = BootConfig.getIns();
            conf.load(getConfigFile(args));

            // 设置默认时区
            TimeZone.setDefault(TimeZone.getTimeZone(conf.getTimeZone()));

            CfgMgr.setDir(conf.getDataDir());
            CfgMgr.load();


            NameChecker.getInstance().loadSensitiveWords(conf.getSensitiveWordsFile());
            GameConfigValidator.validate();
            PerfectDb.getInstance().start(conf.getDbConf());
            ExecutorUtil.start();

            ClassRedefiner.install();

            ReflectionUtil.registerAndStartModules("gs");
            TimeControllerManager.getInstance().start();
            TimeRangeControllerManager.getInstance().start();
            StatisticUtil.start();
            gs.net.Module.Ins.start();

            long startTime = System.currentTimeMillis();

            ServerModule.Ins.tryExecuteServerOpen(startTime);
            new StartServerSuccess(startTime).trigger();


        } catch (Throwable t) {

            printError(t);
            System.exit(-1);
        }
    }
}
