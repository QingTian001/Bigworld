package gs.time.timerange;

import gs.time.Util;
import gs.time.timerange.timerangestruct.DateRangeStruct;
import gs.util.timer.Timer;
import pcore.db.Trace;
import pcore.misc.TaskQueue;

import java.util.Calendar;
import java.util.concurrent.atomic.AtomicLong;

import static gs.time.timerange.TimeRangePair.NOT_VALID;

/**
 * Created by zyao on 2020/4/16 17:36
 */
public class TimeRangeController {

    public enum STATE {
        INIT,
        ACTIVE,
        ENDING, // 执行完fireEnd或者被取消, 设置为ending状态
        NULL,
        END,
    }

    private final TimeRange timeRange;
    private final ITimeRangeHandler handler;
    public volatile STATE state = STATE.NULL;

    private volatile DateRangeStruct curRange;
    private final boolean inRangeActiveWhenStart;

    private final static AtomicLong idGen = new AtomicLong(0);
    private final long id = idGen.incrementAndGet();
    private final TaskQueue taskQueue;
    private volatile Timer curTimer;

    public TimeRangeController(TimeRange timeRange, ITimeRangeHandler handler, boolean inRangeActiveWhenStart) {
        this.timeRange = timeRange;
        this.handler = handler;
        this.inRangeActiveWhenStart = inRangeActiveWhenStart;
        this.taskQueue = TimeRangeControllerManager.getInstance().getTaskQueue(id);
    }

    private TimeRangeController(TimeRange timeRange, ITimeRangeHandler handler, boolean inRangeActiveWhenStart, TaskQueue taskQueue) {
        this.timeRange = timeRange;
        this.handler = handler;
        this.inRangeActiveWhenStart = inRangeActiveWhenStart;
        this.taskQueue = taskQueue;
    }

    public TimeRangeController(TimeRange timeRange, ITimeRangeHandler handler) {
        this(timeRange, handler, true);
    }

    public final boolean initAndActive() {
        return state == STATE.INIT || state == STATE.ACTIVE;
    }

    public final boolean isActive() {
        return state == STATE.ACTIVE;
    }

    public final TaskQueue getTaskQueue() {
        return  taskQueue;
    }

    public final TimeRangePair getCurTimeRangePair() {
        synchronized (TimeRangeController.this) { // 避免和fireEnd并发执行
            DateRangeStruct cur = curRange;
            if (cur != null) {
                return new TimeRangePair(Util.toTimeStampsInMills(cur.beginTime), Util.toTimeStampsInMills(cur.endTime));
            }
            return NOT_VALID;
        }
    }

    public final long getCurStartTimeStampInMills() {
        DateRangeStruct cur = curRange;
        if (cur != null) {
            return Util.toTimeStampsInMills(cur.beginTime);
        }
        return -1;
    }

    public final long getCurEndTimeStampInMills() {
        DateRangeStruct cur = curRange;
        if (cur != null) {
            return Util.toTimeStampsInMills(cur.endTime);
        }
        return -1;
    }

    public void start(Calendar now) {
        synchronized (TimeRangeController.this) {
            curRange = timeRange.getFirstRangeStruct(now);
            if (curRange == null) {
                clear();
                return;
            }
            if (Util.contains(curRange, now)) {
                if (inRangeActiveWhenStart) {
                    fireBegin(now);
                    return;
                } else {
                    curRange = timeRange.getNextRangeStruct(curRange);
                    if (curRange == null) {
                        clear();
                        return;
                    }
                }
            }
            scheduleBegin(now);
        }
    }

    /**
     * 当状态已经是End状态时, 不会再执行了
     * @return
     */
    public final boolean cancel() {
        synchronized (TimeRangeController.this) {
            if (state == STATE.END) {
                return false;
            }

            if (curTimer != null) {
                curTimer.cancel();
            }
            state = STATE.END;
            return true;
        }
    }

    public final STATE getState() {
        return state;
    }

    private void clear() {
        TimeRangeControllerManager.getInstance().removeTimeRangeController(this);
    }

    // 在锁保护下
    private void scheduleBegin(Calendar now) {
        Calendar beginCal = Util.toCalendar(curRange.beginTime);
        long delay = beginCal.getTimeInMillis() - now.getTimeInMillis();
        curTimer = Timer.scheduleOnce(delay, new Timer.TimerHandler() {
            @Override
            public void onTimer() {
                fireBegin(Calendar.getInstance());
            }
        });
    }

    private void fireBegin(Calendar now) {
        taskQueue.add(() -> {
            synchronized (TimeRangeController.this) {
                if (state != STATE.NULL) {
                    Trace.info("fireEnd but state is not NULL. id={}, state={}", id, state);
                    return;
                }
                state = STATE.INIT;
                handler.handleBegin();
                state = STATE.ACTIVE;

                Calendar endCal = Util.toCalendar(curRange.endTime);
                long delay = endCal.getTimeInMillis() - now.getTimeInMillis();
                curTimer = Timer.scheduleOnce(delay, new Timer.TimerHandler() {
                    @Override
                    public void onTimer() {
                        fireEnd();
                    }
                });
            }
        });
    }


    private void fireEnd() {
        taskQueue.add(() -> {
            synchronized (TimeRangeController.this) {
                if (state != STATE.ACTIVE) {
                    Trace.info("fireEnd but state is not ACTIVE. id={}, state={}", id, state);
                    return;
                }
                state = STATE.ENDING;
                handler.handleEnd();

                curRange = timeRange.getNextRangeStruct(curRange);
                if (curRange == null) {
                    TimeRangeControllerManager.getInstance().removeTimeRangeController(this);
                    state = STATE.END;
                    return;
                }
                state = STATE.NULL;
                scheduleBegin(Calendar.getInstance());
            }
        });
    }

    public DateRangeStruct getNextDateRangeStruct() {
        return curRange == null ? null : timeRange.getNextRangeStruct(curRange);
    }
}
