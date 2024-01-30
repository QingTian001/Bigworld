package gs.time;

import gs.util.timer.Timer;
import pcore.db.Trace;

import java.util.Calendar;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zyao on 2020/4/15 10:28
 */
@SuppressWarnings("SynchronizationOnLocalVariableOrMethodParameter")
public class TimeControllerManager {

    public final static TimeControllerManager instance = new TimeControllerManager();
    private final static int FIND_PERIOD_MILLS = 60 * 1000; // 分钟级
    private Calendar findCalendar;
    private final Map<Time, TimeController> timeControllerMap = new ConcurrentHashMap<>();
    private volatile boolean isStart = false;

    private TimeControllerManager() {

    }

    public void start() {
        Timer.schedulePeriod(0, FIND_PERIOD_MILLS, new Timer.TimerHandler() {
            @Override
            public void onTimer() {
                Calendar curCalendar = Calendar.getInstance();
                synchronized (TimeControllerManager.this) {
                    if (findCalendar == null) {
                        findCalendar = (Calendar) curCalendar.clone();
                        // 第一次找的时候, 如果不是整分钟, 会回退second + milliseconds
                        Util.setCalendarMinuteSharp(findCalendar);
                    }
                    Calendar nextCalendar = (Calendar) curCalendar.clone();
                    Util.setCalendarToNextMinuteSharp(nextCalendar);
                    nextCalendar.add(Calendar.MINUTE, 1);

                    Trace.debug("current findCalendar:{}, curCalendar:{}, nextCalendar:{}", findCalendar.getTime(), curCalendar.getTime(), nextCalendar.getTime());
                    while (findCalendar.before(nextCalendar)) {
                        findCalendar.add(Calendar.MINUTE, 1); // 左开右闭
                        for (TimeController timeController : timeControllerMap.values()) {
                            synchronized (timeController) {
                                if (!timeController.isActive() && timeController.getTime().match(findCalendar)) {
                                    long delay = findCalendar.getTimeInMillis() - curCalendar.getTimeInMillis();
                                    timeController.schedule(delay);
                                }
                            }
                        }
                    } // 结束循环时, findCalendar == nextCalendar
                    assert findCalendar.compareTo(nextCalendar) == 0;
                }
            }
        });
        isStart = true;
    }

    public static TimeControllerManager getInstance() {
        return instance;
    }

    public void addTimeController(TimeController timeController) {
        synchronized (this) {
            timeControllerMap.put(timeController.getTime(), timeController);
            if (isStart) {
                if (findCalendar == null) {
                    return;
                }
                Calendar curCalendar = Calendar.getInstance();
                Calendar tmp = (Calendar) curCalendar.clone();
                Util.setCalendarMinuteSharp(tmp);

                // ( tmp , findCalendar]
                synchronized (timeController) {
                    while (tmp.before(findCalendar)) {
                        tmp.add(Calendar.MINUTE, 1);
                        if (!timeController.isActive() && timeController.getTime().match(findCalendar)) {
                            long delay = tmp.getTimeInMillis() - curCalendar.getTimeInMillis();
                            timeController.schedule(delay);
                        }
                    }// 结束循环时tmp == findCalendar
                }
            }
        }
    }

    public void removeTimeController(TimeController controller) {
        timeControllerMap.remove(controller.getTime());
        synchronized (controller) {
            if (controller.isActive()) {
                controller.stop();
            }
        }
    }
}
