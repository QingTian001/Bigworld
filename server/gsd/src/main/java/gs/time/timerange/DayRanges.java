package gs.time.timerange;

import gs.time.Util;
import gs.time.timerange.timerangestruct.DateRangeStruct;
import gs.time.timerange.timerangestruct.DayRangeStruct;
import gs.time.timestruct.DateTimeStruct;
import gs.time.timestruct.DayTimeStruct;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

/**
 * Created by zyao on 2020/4/16 15:05
 */
public class DayRanges extends TimeRanges<DayTimeStruct, DayRangeStruct> {

    public DayRanges(cfg.time.DayRanges cfg) {
        super(cfg);
        for (cfg.time.DayRange cfgDayRange : cfg.dayRanges) {
            this.addRange(new DayRangeStruct(new DayTimeStruct(cfgDayRange.beginTime), new DayTimeStruct(cfgDayRange.endTime)));
        }
    }
    public DayRanges() {

    }

    @Override
    public Collection<DateRangeStruct> toDateRangeStruct(int year, int month, DayRangeStruct rangeStruct) {
        Calendar calendar = Util.fromYearAndMonth(year, month);
        calendar.set(Calendar.DAY_OF_MONTH, rangeStruct.beginTime.dayOfMonth);

        List<DateRangeStruct> drsList = new ArrayList<>();

        DateTimeStruct beginTime = rangeStruct.beginTime.toDateTimeStruct(calendar);
        Calendar endCal = calendar;
        endCal.set(Calendar.DAY_OF_MONTH, rangeStruct.endTime.dayOfMonth);
        if (rangeStruct.isOverCross()) {
            endCal = (Calendar)calendar.clone();
            endCal.add(Calendar.MONTH, 1);
        }

        DateTimeStruct endTime = rangeStruct.endTime.toDateTimeStruct(endCal);
        drsList.add(new DateRangeStruct(beginTime, endTime));
        return drsList;
    }
}
