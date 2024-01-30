package gs.time.timestruct;

import gs.time.Util;

import java.util.Calendar;

/**
 * Created by zyao on 2020/4/16 14:05
 */
public class OneDayTimeStruct extends TimeStruct<OneDayTimeStruct> {
    public int hour;
    public int minute;

    public OneDayTimeStruct(cfg.time.OneDayTime cfg) {
        this.hour = Util.toCalendarHour(cfg.hour);
        this.minute = Util.toCalendarMinute(cfg.minute);
    }

    @Override
    public String toString() {
        return "hour:" + hour + " minute:" + minute;
    }

    @Override
    public int compareTo(OneDayTimeStruct o) {
        int v = Integer.compare(hour, o.hour);
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
        if (obj.getClass() != OneDayTimeStruct.class) {
            return false;
        }
        OneDayTimeStruct dts = (OneDayTimeStruct)obj;
        return this.hour == dts.hour && this.minute == dts.minute;
    }
}
