package gs.time.timerange;

/**
 * Created by zyao on 2021/4/20 10:40
 */
public class TimeRangePair {

    public static TimeRangePair NOT_VALID = new TimeRangePair(-1, -1);
    public final long startTimeInMills;
    public final long endTimeInMills;

    public TimeRangePair(long startTimeInMills, long endTimeInMills) {
        this.startTimeInMills = startTimeInMills;
        this.endTimeInMills = endTimeInMills;
    }
}