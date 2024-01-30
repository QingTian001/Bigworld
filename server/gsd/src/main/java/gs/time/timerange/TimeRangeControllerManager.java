package gs.time.timerange;

import gs.time.Time;
import gs.time.TimeController;
import gs.util.MathUtil;
import pcore.db.DbExecutor;
import pcore.misc.TaskQueue;

import java.util.Calendar;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zyao on 2020/4/17 10:53
 */
public class TimeRangeControllerManager {


    private final Map<TimeRangeController, TimeRangeController> timeRangeControllerMap = new ConcurrentHashMap<>();
    private final static int timeRangeTaskQueueNum = MathUtil.nextPowerOfTwo(Math.max(16, Runtime.getRuntime().availableProcessors() * 2));
    private final static int timeRangeTaskQueueMask = timeRangeTaskQueueNum - 1;
    private final TaskQueue[] taskQueues = new TaskQueue[timeRangeTaskQueueNum];

    private boolean isStart = false;

    private final static TimeRangeControllerManager instance = new TimeRangeControllerManager();
    private TimeRangeControllerManager() {
        for (int i = 0; i < taskQueues.length; ++i) {
            taskQueues[i] = new TaskQueue(DbExecutor.getInstance());
        }
    }

    public static TimeRangeControllerManager getInstance() {
        return instance;
    }

    public final TaskQueue getTaskQueue(long id) {
        return taskQueues[(int)id & timeRangeTaskQueueMask];
    }

    public synchronized void addTimeRangeController(TimeRangeController controller) {
        timeRangeControllerMap.put(controller, controller);
        if (isStart) {
            controller.start(Calendar.getInstance());
        }
    }

    public void removeTimeRangeController(TimeRangeController controller) {
        timeRangeControllerMap.remove(controller);
    }

    public boolean removeAndCancelTimeRangeController(TimeRangeController controller) {
        timeRangeControllerMap.remove(controller);
        return controller.cancel();
    }

    public synchronized void start() {
        Calendar now = Calendar.getInstance();
        for (TimeRangeController controller : timeRangeControllerMap.values()) {
            controller.start(now);
        }
        isStart = true;
    }
}
