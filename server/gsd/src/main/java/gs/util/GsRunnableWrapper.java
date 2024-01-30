package gs.util;

import gs.util.statistic.StatisticUtil;
import pcore.db.Trace;
import pcore.misc.TimeUtils;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by zyao on 2019/11/6 17:24
 */

/**
 * 非Procedure的Runnable用GsRunnableWrapper进行包装
 */
public class GsRunnableWrapper implements Runnable {

    private final Runnable r;
    private static AtomicLong currentCount = new AtomicLong();

    public GsRunnableWrapper(Runnable r) {
        this.r = r;
    }
    @Override
    public void run() {
        var startNanos = System.nanoTime();
        currentCount.incrementAndGet();
        try {
            r.run();
        }
        catch (Throwable ex) {
            //Trace.error(ex);
            throw new RuntimeException(ex);
        }
        finally {
            currentCount.decrementAndGet();
        }
        StatisticUtil.statistic(r, System.nanoTime() - startNanos);
    }

    public static long getCurrentCount() {
        return currentCount.get();
    }
}
