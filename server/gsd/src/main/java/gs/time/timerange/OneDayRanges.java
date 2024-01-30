package gs.time.timerange;

import gs.time.Util;
import gs.time.timerange.timerangestruct.DateRangeStruct;
import gs.time.timerange.timerangestruct.OneDayRangeStruct;
import gs.time.timestruct.DateTimeStruct;
import gs.time.timestruct.OneDayTimeStruct;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

/**
 * Created by zyao on 2020/4/16 14:35
 */
public class OneDayRanges extends TimeRanges<OneDayTimeStruct, OneDayRangeStruct> {

    public OneDayRanges(cfg.time.OneDayRanges cfg) {
        super(cfg);
        for (cfg.time.OneDayRange cfgDayRange : cfg.oneDayRanges) {
            this.addRange(new OneDayRangeStruct(new OneDayTimeStruct(cfgDayRange.beginTime), new OneDayTimeStruct(cfgDayRange.endTime)));
        }
    }

    public OneDayRanges() {

    }

    @Override
    public Collection<DateRangeStruct> toDateRangeStruct(int year, int month, OneDayRangeStruct rangeStruct) {
        Calendar calendar = Util.fromYearAndMonth(year, month);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Calendar nextMonthCalendar = (Calendar) calendar.clone();
        nextMonthCalendar.add(Calendar.MONTH, 1);
        List<DateRangeStruct> drsList = new ArrayList<>();
        while (calendar.before(nextMonthCalendar)) {

            DateTimeStruct beginTime = rangeStruct.beginTime.toDateTimeStruct(calendar);
            Calendar endCal = null;
            if (rangeStruct.isOverCross()) {
                endCal = (Calendar)calendar.clone();
                endCal.add(Calendar.DAY_OF_MONTH, 1);
            }
            else {
                endCal = calendar;
            }

            DateTimeStruct endTime = rangeStruct.endTime.toDateTimeStruct(endCal);

            drsList.add(new DateRangeStruct(beginTime, endTime));
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        return drsList;
    }
}
