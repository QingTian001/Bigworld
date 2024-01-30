package gs.util.timer;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by zyao on 2020/4/15 15:44
 */
public class OnceTimer extends Timer {

    private static AtomicLong curWorkOnceTimerCount = new AtomicLong();
    private long delay;
    OnceTimer(long delay, TimerHandler handler) {
        super(TIMER_TYPE.ONCE, handler);
        this.delay = delay;
    }


    @Override
    public void schedule() {
        curWorkOnceTimerCount.incrementAndGet();
        setFuture(scheduleDelay(delay, this));
    }

    @Override
    public void onCancel(boolean cancelFuture) {
        if (cancelFuture) {
            curWorkOnceTimerCount.decrementAndGet();
        }
    }

    @Override
    public void beforeOnTimer() {
        curWorkOnceTimerCount.decrementAndGet();
    }

    public static long getCurrentCount() {
        return curWorkOnceTimerCount.get();
    }
}
