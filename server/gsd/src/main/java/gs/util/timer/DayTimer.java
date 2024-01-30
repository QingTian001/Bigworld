package gs.util.timer;

import java.util.Calendar;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by zyao on 2020/4/15 15:54
 */
public class DayTimer extends Timer {

    private static AtomicLong curWorkDayTimerCount = new AtomicLong();
    private final int hour;
    private final int minute;
    private final int seconds;
    private Calendar nextCalender;
    DayTimer(int hour, int minute, int seconds, TimerHandler handler) {
        super(TIMER_TYPE.BY_DAY, handler);
        this.hour = hour;
        this.minute = minute;
        this.seconds = seconds;
    }

    private void doSchedule(Calendar now) {
        long delay = nextCalender.getTimeInMillis() - now.getTimeInMillis();
        if (delay < 0) {
            throw new RuntimeException("delay < 0, That's impossilbe");
        }
        setFuture(scheduleDelay(delay, this));
    }

    @Override
    public void schedule() {
        curWorkDayTimerCount.incrementAndGet();
        Calendar nowCalender = Calendar.getInstance();
        nextCalender = (Calendar)nowCalender.clone();
        nextCalender.set(Calendar.HOUR_OF_DAY, hour);
        nextCalender.set(Calendar.MINUTE, minute);
        nextCalender.set(Calendar.SECOND, seconds);

        if (nextCalender.before(nowCalender)) {
            nextCalender.add(Calendar.DAY_OF_MONTH, 1);
        }
        doSchedule(nowCalender);

    }

    @Override
    public void onCancel(boolean cancelFuture) {
        if (cancelFuture) {
            curWorkDayTimerCount.decrementAndGet();
        }
    }

    @Override
    public void afterOnTimer() {
        Calendar nowCalender = Calendar.getInstance();
        nextCalender.add(Calendar.DAY_OF_MONTH, 1);
        doSchedule(nowCalender);
    }

    public static long getCurrentCount() {
        return curWorkDayTimerCount.get();
    }
}
