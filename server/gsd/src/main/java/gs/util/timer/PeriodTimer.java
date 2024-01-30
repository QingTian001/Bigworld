package gs.util.timer;

import pcore.db.DbExecutor;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by zyao on 2020/4/15 15:54
 */
public class PeriodTimer extends Timer {

    private static AtomicLong curWorkPeriodTimerCount = new AtomicLong();
    private final long delay;
    private final long period;
    PeriodTimer(long delay, long period, TimerHandler handler) {
        super(TIMER_TYPE.PERIOD, handler);
        this.delay = delay;
        this.period = period;

    }

    @Override
    public void onCancel(boolean cancelFuture) {
        if (cancelFuture) {
            curWorkPeriodTimerCount.decrementAndGet();
        }
    }

    @Override
    public void schedule() {
        setFuture(DbExecutor.getInstance().scheduleAtFixedRate(this, delay, period, TimeUnit.MILLISECONDS));
        curWorkPeriodTimerCount.incrementAndGet();
    }

    public static long getCurrentCount() {
        return curWorkPeriodTimerCount.get();
    }
}
