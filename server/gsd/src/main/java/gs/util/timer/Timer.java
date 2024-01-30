package gs.util.timer;

import gs.util.statistic.StatisticUtil;
import pcore.db.DbExecutor;
import pcore.db.Trace;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by zyao on 2019/4/3 10:36
 */
public abstract class Timer implements Runnable, ITimer {

    public abstract static class TimerHandler {
        private Timer timer;
        public final void cancel() {
            timer.cancel();
        }
        public abstract void onTimer();
    }

    private ScheduledFuture future;
    private TIMER_TYPE timerType;

    protected enum TIMER_TYPE {
        PERIOD,
        ONCE,
        BY_DAY,
    }

    private volatile boolean isStopped = false;
    private TimerHandler handler;

    protected Timer(TIMER_TYPE timer_type, TimerHandler handler) {
        if (handler == null) {
            throw new RuntimeException("handler is null");
        }
        this.timerType = timer_type;
        this.handler = handler;
        this.handler.timer = this;
    }

    protected void setFuture(ScheduledFuture future) {
        this.future = future;
    }

    public abstract void schedule();
    public abstract void onCancel(boolean cancelFuture);
    public void beforeOnTimer() {}
    public void afterOnTimer() {}

    protected static synchronized ScheduledFuture scheduleDelay(long delayInMills, Timer timer) {
        if (delayInMills < 0) {
            delayInMills = 0;
        }
        return DbExecutor.getInstance().schedule(timer, delayInMills, TimeUnit.MILLISECONDS);
    }

    public static synchronized Timer scheduleOnce(long delayInMills, TimerHandler handler) {
        if (handler == null) {
            throw new RuntimeException("handler is not set");
        }
        Timer timer = new OnceTimer(delayInMills, handler);
        timer.schedule();
        return timer;
    }

    public static synchronized Timer schedulePeriod(long delayInMills, long periodInMills, TimerHandler handler) {
        if (handler == null) {
            throw new RuntimeException("handler is not set");
        }
        if (periodInMills <= 0) {
            throw new RuntimeException("period must be greater than 0");
        }
        if (delayInMills < 0) {
            delayInMills = 0;
        }
        Timer timer = new PeriodTimer(delayInMills, periodInMills, handler);
        timer.schedule();
        return timer;
    }

    public static synchronized Timer schedulePerDay(int hour, int minute, int seconds, TimerHandler handler) {
        if (handler == null) {
            throw new RuntimeException("handler is not set");
        }

        Timer timer = new DayTimer(hour, minute, seconds, handler);
        timer.schedule();
        return timer;
    }

    @Override
    public final boolean isStopped() {
        return isStopped;
    }

    @Override
    public synchronized final void cancel() {
        if (isStopped) {
            return;
        }
        boolean cancelFuture = false;
        if (future != null) {
            future.cancel(false);
            cancelFuture = true;
        }
        isStopped = true;
        onCancel(cancelFuture);
    }

    @Override
    public void run() {
        synchronized (this) {
            if (isStopped) {
                return;
            }
            if (timerType == TIMER_TYPE.ONCE) {
                isStopped = true;
            }
        }
        beforeOnTimer();
        long startNanos = System.nanoTime();
        Trace.debug("Timer:{} run.", handler.getClass().getName());
        try {
            handler.onTimer();
        }
        catch (Throwable t) {
            Trace.error(t);
        }
        StatisticUtil.statistic(this, System.nanoTime() - startNanos);
        afterOnTimer();
    }
}
