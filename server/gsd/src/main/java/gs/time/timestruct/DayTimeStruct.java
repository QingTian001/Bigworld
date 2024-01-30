package gs.time.timestruct;

import gs.time.Util;

import java.util.Calendar;

/**
 * Created by zyao on 2020/4/16 14:06
 */
public class DayTimeStruct extends TimeStruct<DayTimeStruct> {
    public int dayOfMonth;
    public int hour;
    public int minute;

    public DayTimeStruct(cfg.time.DayTime cfg) {
        this.dayOfMonth = Util.toCalendarDayOfMonth(cfg.dayOfMonth);
        this.hour = Util.toCalendarHour(cfg.hour);
        this.minute = Util.toCalendarMinute(cfg.minute);
    }

    @Override
    public String toString() {
        return "dayOfMonth:" + dayOfMonth + " hour:" + hour + " minute:" + minute;
    }

    @Override
    public int compareTo(DayTimeStruct o) {
        int v = Integer.compare(dayOfMonth, o.dayOfMonth);
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
        return new DateTimeStruct(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), dayOfMonth, hour, minute);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != DayTimeStruct.class) {
            return false;
        }
        DayTimeStruct dts = (DayTimeStruct)obj;
        return this.dayOfMonth == dts.dayOfMonth && this.hour == dts.hour && this.minute == dts.minute;
    }
}
