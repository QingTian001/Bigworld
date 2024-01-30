package gs.time.timestruct;

import gs.time.Util;
import gs.time.timerange.DateTimeRange;

import java.util.Calendar;

/**
 * Created by zyao on 2020/4/16 14:25
 */
public class DateTimeStruct extends TimeStruct<DateTimeStruct>{
    public int year;
    public int month;
    public int dayOfMonth;
    public int hour;
    public int minute;

    public DateTimeStruct(int year, int month, int dayOfMonth, int hour, int minute) {
        this.year = year;
        this.month = month;
        this.dayOfMonth = dayOfMonth;
        this.hour = hour;
        this.minute = minute;
    }

    public DateTimeStruct(cfg.time.DateTime cfg) {
        this.year = Util.toCalendarYear(cfg.year);
        this.month = Util.toCalendarMonth(cfg.month);
        this.dayOfMonth = Util.toCalendarDayOfMonth(cfg.day);
        this.hour = Util.toCalendarHour(cfg.hour);
        this.minute = Util.toCalendarMinute(cfg.minute);
    }

    public DateTimeStruct() {

    }

    @Override
    public String toString() {
        return "year:" + year + " month:" + month + " dayOfMonth:" + dayOfMonth + " hour:" + hour + " minute:" + minute;
    }

    @Override
    public int compareTo(DateTimeStruct o) {
        int v = Integer.compare(year, o.year);
        if (v != 0) {
            return v;
        }
        v = Integer.compare(month, o.month);
        if (v != 0) {
            return v;
        }
        v = Integer.compare(dayOfMonth, o.dayOfMonth);
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
        return new DateTimeStruct(year, month, dayOfMonth, hour, minute);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != DateTimeStruct.class) {
            return false;
        }
        DateTimeStruct dts = (DateTimeStruct)obj;
        return this.year == dts.year && this.month == dts.month && this.dayOfMonth == dts.dayOfMonth && this.hour == dts.hour && this.minute == dts.minute;
    }
}
