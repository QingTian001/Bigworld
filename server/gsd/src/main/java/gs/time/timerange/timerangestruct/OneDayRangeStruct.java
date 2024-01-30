package gs.time.timerange.timerangestruct;

import gs.time.timestruct.OneDayTimeStruct;

/**
 * Created by zyao on 2020/4/16 11:48
 */
public class OneDayRangeStruct extends TimeRangeStruct<OneDayTimeStruct> {

    public OneDayRangeStruct(OneDayTimeStruct beginTime, OneDayTimeStruct endTime) {
        super(beginTime, endTime);
    }
}
