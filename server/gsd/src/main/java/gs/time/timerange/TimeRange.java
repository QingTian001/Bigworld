package gs.time.timerange;

import gs.time.timerange.timerangestruct.DateRangeStruct;
import gs.time.timerange.timerangestruct.TimeRangeStruct;

import java.util.Calendar;

/**
 * Created by zyao on 2020/4/16 14:21
 */
public abstract class TimeRange {

    public abstract DateRangeStruct getNextRangeStruct(DateRangeStruct cur);
    public abstract DateRangeStruct getFirstRangeStruct(Calendar now);
}
