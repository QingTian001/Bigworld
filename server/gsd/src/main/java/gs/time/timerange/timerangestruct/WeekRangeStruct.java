package gs.time.timerange.timerangestruct;

import gs.time.timestruct.WeekTimeStruct;

/**
 * Created by zyao on 2020/4/16 14:20
 */
public class WeekRangeStruct extends TimeRangeStruct<WeekTimeStruct> {
    public WeekRangeStruct(WeekTimeStruct beginTime, WeekTimeStruct endTime) {
        super(beginTime, endTime);
    }
}
