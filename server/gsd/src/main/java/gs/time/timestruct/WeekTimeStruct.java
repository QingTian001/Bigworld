package gs.time.timestruct;

import gs.time.Util;

import java.util.Calendar;

/**
 * Created by zyao on 2020/4/16 14:07
 */
public class WeekTimeStruct extends TimeStruct<WeekTimeStruct> {
    public int weekDay;
    public int hour;
    public int minute;

    public WeekTimeStruct(cfg.time.WeekTime cfg) {
        this.weekDay = Util.toCalendarWeekDay(cfg.weekDay);
        this.hour = Util.toCalendarHour(cfg.hour);
        this.minute = Util.toCalendarMinute(cfg.minute);
    }

    @Override
    public String toString() {
        return "weekDay:" + weekDay + " hour:" + hour + " minute:" + minute;
    }

    @Override
    public int compareTo(WeekTimeStruct o) {
        // 这里不能直接比较, 因为周日在Calendar中是1
        int naturalWeekDay = Util.toNaturalWeekDay(weekDay);
        int objNaturalWeekDay = Util.toNaturalWeekDay(o.weekDay);
        int v = Integer.compare(naturalWeekDay, objNaturalWeekDay);
        if (v != 0) {
            return v;
        }
        v = Integer.compare(hour, o.hour);
        if (v != 0) {
            return v;
        }
        v = Integer.compare(minute, o.minute);
        if (v != 0) {
            return v;
        }
        return 0;
    }

    @Override
    public DateTimeStruct toDateTimeStruct(Calendar calendar) {
        return new DateTimeStruct(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), hour, minute);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != WeekTimeStruct.class) {
            return false;
        }
        WeekTimeStruct wts = (WeekTimeStruct)obj;
        return this.weekDay == wts.weekDay && this.hour == wts.hour && this.minute == wts.minute;
    }
}
