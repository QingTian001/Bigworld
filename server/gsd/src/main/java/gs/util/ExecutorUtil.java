package gs.util;

import pcore.db.DbExecutor;
import pcore.db.DbThread;
import pcore.db.Procedure;
import pcore.db.Trace;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zyao on 2020/2/20 20:37
 */
public class ExecutorUtil {

    private static SerialKeyExecutor<Long> roleExecutor = new SerialKeyExecutor<>();
    private static ExecutorService sdkExecutor;
    private static ExecutorService customServiceExecutor;
    private static SerialKeyExecutor<Long> sdkRoleExecutor;
    private static SerialKeyExecutor<Long> sdkGuildExecutor;

    private static SerialKeyExecutor<Long> limitedExecutor;
    private static SerialKeyExecutor<Long> globalStatisticsExecutor;


    public static void start() {

        sdkExecutor = Executors.newFixedThreadPool(128, new ThreadFactory() {
                    AtomicInteger num = new AtomicInteger();
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread t = new Thread(r);
                        t.setName("sdk-worker-" + num.incrementAndGet());
                        return t;
                    }
        });
        customServiceExecutor = Executors.newFixedThreadPool(1, new ThreadFactory() {
            AtomicInteger num = new AtomicInteger();
            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setName("custom-service-worker-" + num.incrementAndGet());
                return t;
            }
        });

        sdkRoleExecutor = new SerialKeyExecutor<>(sdkExecutor);
        sdkGuildExecutor = new SerialKeyExecutor<>(sdkExecutor);

        limitedExecutor = new SerialKeyExecutor<>();
        globalStatisticsExecutor = new SerialKeyExecutor<>();
    }

    public static void shutdown() {
        sdkExecutor.shutdownNow();
        customServiceExecutor.shutdownNow();

        try {
            sdkExecutor.awaitTermination(Integer.MAX_VALUE, TimeUnit.SECONDS);
            Trace.info("sdkExecutor shutdown");
            customServiceExecutor.awaitTermination(Integer.MAX_VALUE, TimeUnit.SECONDS);
            Trace.info("customServiceExecutor shutdown");
        }
        catch (InterruptedException e) {
            Trace.error(e);
        }
    }

    public static void executeSdkTask(Runnable r) {
        sdkExecutor.execute(new GsRunnableWrapper(r));
    }

    public static void executeSdkTask(GProcedure p) {
        sdkExecutor.execute(p);
    }

    public static void executeSdkTaskByRoleId(long roleId, Runnable r) {
        sdkRoleExecutor.execute(roleId, new GsRunnableWrapper(r));
    }

    public static void executeSdkTaskByGuildId(long guildId, GProcedure p) {
        sdkGuildExecutor.execute(guildId, p);
    }

    public static void executeSdkTaskByGuildId(long guildId, Runnable r) {
        sdkGuildExecutor.execute(guildId, new GsRunnableWrapper(r));
    }

    public static void executeSdkTaskByRoleId(long roleId, GProcedure p) {
        sdkRoleExecutor.execute(roleId, p);
    }

    public static void executeLimitedTaskByLimitedId(long limitedId, Runnable r) {
        limitedExecutor.execute(limitedId, new GsRunnableWrapper(r));
    }

    public static void executeLimitedTaskByLimitedId(long limitedId, GProcedure p) {
        limitedExecutor.execute(limitedId, p);
    }

    public static void executeGlobalStatisticsTaskById(long id, Runnable r) {
        globalStatisticsExecutor.execute(id, new GsRunnableWrapper(r));
    }

    public static void executeGlobalStatisticsTaskById(long id, GProcedure p) {
        globalStatisticsExecutor.execute(id, p);
    }

    public static void executeCustomServiceTask(Runnable r) {
        customServiceExecutor.execute(new GsRunnableWrapper(r));
    }

    public static void executeCustomServiceTask(GProcedure p) {
        customServiceExecutor.execute(p);
    }

    public static void execute(Runnable r) {
        DbExecutor.getInstance().execute(new GsRunnableWrapper(r));
    }

    public static void execute(long roleId, Runnable r) {
        roleExecutor.execute(roleId, new GsRunnableWrapper(r));
    }

    public static void execute(GProcedure p) {
        DbExecutor.getInstance().execute(p);
    }

    public static void execute(long roleId, GProcedure p) {
        roleExecutor.execute(roleId, p);
    }

    public static void executeMayLongBlockTask(Runnable r) {
        DbExecutor.getInstance().executeMayLongBlockTask(new GsRunnableWrapper(r));
    }

    public static void executeMayLongBlockTask(GProcedure p) {
        DbExecutor.getInstance().executeMayLongBlockTask(p);
    }


    public static void call(Runnable r) {
        new GsRunnableWrapper(r).run();
    }


}
