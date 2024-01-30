package gs.time;

import gs.util.ExecutorUtil;
import gs.util.timer.Timer;
import pcore.db.Trace;

import java.util.Calendar;

/**
 * Created by zyao on 2020/4/15 10:00
 */
public class TimeController {
    private final Time time;
    private final ITimeHandler handler;
    private volatile boolean isActive;
    private Timer timer;

    public TimeController(Time time, ITimeHandler handler) {
        this.time = time;
        this.handler = handler;
    }

    public final Time getTime() {
        return time;
    }

    final boolean isActive() {
        return isActive;
    }

    private synchronized void fire(long curMs) {

        handler.handle(curMs);
        timer = null;
        isActive = false;
    }

    synchronized void schedule(long delayMs) {
        if (isActive) {
            Trace.warn("TimeController is active already");
            return;
        }
        Trace.debug("TimeController scheduled. delayMs:{}, timerController:{}", delayMs, this);

        // 找到就启动Timer
        timer = Timer.scheduleOnce(delayMs, new Timer.TimerHandler() {
            @Override
            public void onTimer() {
                // 丢到线程池里执行
                ExecutorUtil.execute(() -> {
                    Trace.debug("TimeController fire. calendar:{}, timerController:{}", Calendar.getInstance().getTime(), TimeController.this);
                    TimeController.this.fire(Calendar.getInstance().getTimeInMillis());
                });
            }
        });
        isActive = true;
    }

    synchronized void stop() {
        if (isActive) {
            timer.cancel();
            isActive = false;
        }
    }
}
