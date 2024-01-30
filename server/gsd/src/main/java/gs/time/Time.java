package gs.time;

import cfg.time.CompleteTime;
import pcore.collection.IntSet;

import java.util.Calendar;

/**
 * Created by zyao on 2020/4/15 10:04
 */

/*
month 1月是0
weekday sunday = 1, saturday = 7
 */
public abstract class Time {
    private final IntSet yearList = new IntSet();
    private final IntSet monthList = new IntSet();

    private final IntSet hourList = new IntSet();
    private final IntSet minuteList = new IntSet();

    public Time(CompleteTime cfg) {
        for (int year : cfg.year) {
            addCfgYear(year);
        }
        for (int month : cfg.month) {
            addCfgMonth(month);
        }
        for (int hour : cfg.hour) {
            addCfgHour(hour);
        }
        for (int minute : cfg.minute) {
            addCfgMinute(minute);
        }
    }

    public Time() {

    }

    public final void addCfgYear(int year) {
        yearList.add(Util.toCalendarYear(year));
    }

    public final void addCfgMonth(int month) {
        monthList.add(Util.toCalendarMonth(month));
    }

    public final void addCfgHour(int hour) {
        hourList.add(Util.toCalendarHour(hour));
    }

    public final void addCfgMinute(int minute) {
        minuteList.add(Util.toCalendarMinute(minute));
    }

    public boolean match(Calendar calendar) {
        if (yearList.size() > 0) {
            if (!yearList.contains(calendar.get(Calendar.YEAR))) {
                return false;
            }
        }
        if (monthList.size() > 0) {
            if (!monthList.contains(calendar.get(Calendar.MONTH))) {
                return false;
            }
        }
        if (hourList.size() > 0) {
            if (!hourList.contains(calendar.get(Calendar.HOUR_OF_DAY))) {
                return false;
            }
        }
        if (minuteList.size() > 0) {
            if (!minuteList.contains(calendar.get(Calendar.MINUTE))) {
                return false;
            }
        }
        return true;
    }


}
