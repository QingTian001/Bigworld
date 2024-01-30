package gs.time.timerange.timerangestruct;

import gs.time.timestruct.DayTimeStruct;

/**
 * Created by zyao on 2020/4/16 14:18
 */
public class DayRangeStruct extends TimeRangeStruct<DayTimeStruct> {
    public DayRangeStruct(DayTimeStruct beginTime, DayTimeStruct endTime) {
        super(beginTime, endTime);
    }
}
