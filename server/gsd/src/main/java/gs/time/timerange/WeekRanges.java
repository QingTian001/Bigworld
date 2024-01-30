package gs.time.timerange;

import gs.time.Util;
import gs.time.timerange.timerangestruct.DateRangeStruct;
import gs.time.timerange.timerangestruct.WeekRangeStruct;
import gs.time.timestruct.DateTimeStruct;
import gs.time.timestruct.WeekTimeStruct;
import pcore.db.Trace;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

/**
 * Created by zyao on 2020/4/16 14:37
 */
public class WeekRanges extends TimeRanges<WeekTimeStruct, WeekRangeStruct> {

    public WeekRanges(cfg.time.WeekRanges cfg) {
        super(cfg);
        for (cfg.time.WeekRange cfgWeekRange : cfg.weekRanges) {
            this.addRange(new WeekRangeStruct(new WeekTimeStruct(cfgWeekRange.beginTime), new WeekTimeStruct(cfgWeekRange.endTime)));
        }
    }

    public WeekRanges() {

    }

    @Override
    public Collection<DateRangeStruct> toDateRangeStruct(int year, int month, WeekRangeStruct rangeStruct) {
        Calendar calendar = Util.fromYearAndMonth(year, month);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);

        boolean find = false;
        for (int day = 1; day < 31; day++) {
            calendar.set(Calendar.DAY_OF_MONTH, day);
            if (calendar.get(Calendar.DAY_OF_WEEK) == rangeStruct.beginTime.weekDay) {
                find = true;
                break;
            }
        }
        if (!find) {
            throw new RuntimeException("error weekDay: rangeStruct.beginTime.weekDay");
        }

        List<DateRangeStruct> drsList = new ArrayList<>();

        while (calendar.get(Calendar.MONTH) == month) {
            DateTimeStruct beginTime = rangeStruct.beginTime.toDateTimeStruct(calendar);
            Calendar endCal = (Calendar) calendar.clone();
            endCal.set(Calendar.DAY_OF_WEEK, rangeStruct.endTime.weekDay);
            if (rangeStruct.isOverCross()) {
                endCal.add(Calendar.WEEK_OF_MONTH, 1);
            }
            DateTimeStruct endTime = rangeStruct.endTime.toDateTimeStruct(endCal);
            drsList.add(new DateRangeStruct(beginTime, endTime));

            calendar.add(Calendar.DAY_OF_MONTH, 7); // 一周7天
        }

//        for (int i = 0; i < drsList.size(); ++i) {
//            Trace.info(drsList.get(i).toString());
//        }

        return drsList;
    }
}
