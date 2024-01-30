package gs.time.timerange;

import gs.time.Util;
import gs.time.timerange.timerangestruct.DateRangeStruct;
import gs.time.timestruct.DateTimeStruct;

import java.util.Calendar;

/**
 * Created by zyao on 2021/6/2 16:16
 */
public abstract class OneTimeRange extends TimeRange {
    protected DateRangeStruct data;

    public void setDateRangeStruct(DateRangeStruct data) {
        this.data = data;
    }

    @Override
    public DateRangeStruct getNextRangeStruct(DateRangeStruct cur) {
        return null;
    }


    @Override
    public DateRangeStruct getFirstRangeStruct(Calendar now) {

        DateTimeStruct nowDt = Util.toDateTimeStruct(now);
        if (data.contains(nowDt) || nowDt.compareTo(data.beginTime) < 0) {
            return data;
        }

        return null;
    }
}
