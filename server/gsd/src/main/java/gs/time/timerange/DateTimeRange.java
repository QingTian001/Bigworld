package gs.time.timerange;

import gs.time.Util;
import gs.time.timerange.timerangestruct.DateRangeStruct;
import gs.time.timestruct.DateTimeStruct;

import java.util.Calendar;

/**
 * Created by zyao on 2020/4/16 14:22
 */
public class DateTimeRange extends OneTimeRange {

    public DateTimeRange(cfg.time.DateRange cfg) {
        data = new DateRangeStruct(new DateTimeStruct(cfg.beginTime), new DateTimeStruct(cfg.endTime));
    }

    public DateTimeRange(DateTimeStruct beginTimeStruct, DateTimeStruct endTimeStruct) {
        data = new DateRangeStruct(beginTimeStruct, endTimeStruct);
    }

    public DateTimeRange(long startTimeMills, long endTimeMills) {
        data = new DateRangeStruct(Util.toDateTimeStruct(startTimeMills), Util.toDateTimeStruct(endTimeMills));
    }

    @Override
    public boolean equals(Object obj) {

        if (obj.getClass() != DateTimeRange.class) {
            return false;
        }
        DateTimeRange dtr = (DateTimeRange)obj;
        return this.data.equals(dtr.data);
    }
}
