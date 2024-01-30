package gs.time.timerange;

import gs.time.timerange.timerangestruct.TimeRangeStruct;

import java.util.Calendar;

/**
 * Created by zyao on 2020/4/17 10:54
 */
public class TimeRangeStructPair {
    public final TimeRangeStruct<?> timeRangeStruct;
    public final Calendar calendar;

    public TimeRangeStructPair(TimeRangeStruct<?> timeRangeStruct, Calendar calendar) {
        this.timeRangeStruct = timeRangeStruct;
        this.calendar = calendar;
    }
}
