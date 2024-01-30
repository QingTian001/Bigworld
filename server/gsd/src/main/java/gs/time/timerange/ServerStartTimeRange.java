package gs.time.timerange;

import gs.sysmodule.server.ServerModule;
import gs.time.Util;
import gs.time.timerange.timerangestruct.DateRangeStruct;
import gs.time.timestruct.DateTimeStruct;
import gs.time.timestruct.OneDayTimeStruct;

import java.util.Calendar;

/**
 * Created by zyao on 2021/4/8 11:06
 */

public class ServerStartTimeRange extends OneTimeRange {

    public ServerStartTimeRange(cfg.time.ServerStartTimeRange cfg) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(ServerModule.Ins.getServerFirstStartTimeMills());
        DateTimeStruct serverStartTime = Util.toDateTimeStruct(c);
        int delayDays = cfg.delayDaysNum;

        if (cfg.delayDaysNum < 0) {
            throw new RuntimeException("deltaDaysNum error:" + cfg.delayDaysNum);
        }
        OneDayTimeStruct beginTime = new OneDayTimeStruct(cfg.beginTime);
        int lastSeconds = cfg.lastSeconds;

        Calendar calendar = Util.toCalendar(serverStartTime);
        Util.setCalendarDaySharp(calendar);

        calendar.add(Calendar.DAY_OF_MONTH, delayDays);
        calendar.set(Calendar.HOUR_OF_DAY, beginTime.hour);
        calendar.set(Calendar.MINUTE, beginTime.minute);

        Calendar calendarEnd = (Calendar) calendar.clone();
        calendarEnd.add(Calendar.SECOND, lastSeconds);

        DateTimeStruct dtBegin = Util.toDateTimeStruct(calendar);
        DateTimeStruct dtEnd = Util.toDateTimeStruct(calendarEnd);

        this.data = new DateRangeStruct(dtBegin, dtEnd);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != ServerStartTimeRange.class) {
            return false;
        }

        ServerStartTimeRange sstr = (ServerStartTimeRange)obj;
        return this.data.equals(sstr.data);
    }
}
