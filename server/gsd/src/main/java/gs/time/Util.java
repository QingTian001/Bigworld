package gs.time;

import cfg.time.DateTime;
import gs.time.timerange.*;
import gs.time.timerange.timerangestruct.DateRangeStruct;
import gs.time.timerange.timerangestruct.DayRangeStruct;
import gs.time.timerange.timerangestruct.OneDayRangeStruct;
import gs.time.timerange.timerangestruct.WeekRangeStruct;
import gs.time.timestruct.DateTimeStruct;
import gs.time.timestruct.DayTimeStruct;
import gs.time.timestruct.OneDayTimeStruct;
import gs.time.timestruct.WeekTimeStruct;

import java.util.Calendar;

/**
 * Created by zyao on 2020/4/17 11:02
 */
public class Util {
    public static final long secondMills = 1000;
    public static final long minuteMills = secondMills * 60;
    public static final long hourMills = secondMills * 60 * 60;

    public final static int[] allMonths = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};

    public static DateTimeStruct toDateTimeStruct(long timeStampMills) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeStampMills);
        return toDateTimeStruct(calendar);
    }

    public static DateTimeStruct toDateTimeStruct(Calendar calendar) {
        DateTimeStruct s = new DateTimeStruct();
        s.year = calendar.get(Calendar.YEAR);
        s.month = calendar.get(Calendar.MONTH);
        s.dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        s.hour = calendar.get(Calendar.HOUR_OF_DAY);
        s.minute = calendar.get(Calendar.MINUTE);
        return s;
    }

    public static boolean contains(DateRangeStruct dateRangeStruct, Calendar calendar) {
        return dateRangeStruct.contains(toDateTimeStruct(calendar));
    }

    public static Calendar fromYearAndMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        return calendar;
    }

    public static long toTimestampInMills(DateTime dateTimeCfg) {
        return toCalendar(dateTimeCfg).getTimeInMillis();
    }

    public static Calendar toCalendar(DateTime dateTimeCfg) {
        return toCalendar(new DateTimeStruct(dateTimeCfg));
    }

    public static Calendar toCalendar(DateTimeStruct dts) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, dts.year);
        calendar.set(Calendar.MONTH, dts.month);

        calendar.set(Calendar.DAY_OF_MONTH, dts.dayOfMonth);
        calendar.set(Calendar.HOUR_OF_DAY, dts.hour);

        calendar.set(Calendar.MINUTE, dts.minute);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar;
    }

    public static long toTimeStampsInMills(DateTimeStruct dts) {
        Calendar calendar = toCalendar(dts);
        return calendar.getTimeInMillis();
    }


    public static boolean checkCfgMonth(int cfgMonth) {
        if (cfgMonth < 1 || cfgMonth > 12) {
            return false;
        }
        return true;
    }

    public static boolean checkCfgDayOfMonth(int cfgDay) {
        if (cfgDay < 1 || cfgDay > 31) {
            return false;
        }
        return true;
    }

    public static boolean checkCfgWeekDay(int cfgWeekDay) {
        if (cfgWeekDay < 1 || cfgWeekDay > 7) {
            return false;
        }
        return true;
    }

    public static boolean checkCfgHour(int cfgHour) {
        if (cfgHour < 0 || cfgHour > 23) {
            return false;
        }
        return true;
    }

    public static boolean checkCfgMinute(int cfgMinute) {
        if (cfgMinute < 0 || cfgMinute > 59) {
            return false;
        }
        return true;
    }

    public static int toCalendarYear(int cfgYear) {
        return cfgYear;
    }

    public static int toCalendarMonth(int cfgMonth) {
        if (!checkCfgMonth(cfgMonth)) {
            throw new RuntimeException("cfgMonth error:" + cfgMonth);
        }
        return cfgMonth - 1;
    }

    public static int toCalendarDayOfMonth(int cfgDayOfMonth) {
        if (!checkCfgDayOfMonth(cfgDayOfMonth)) {
            throw new RuntimeException("cfgDayOfMonth error:" + cfgDayOfMonth);
        }
        return cfgDayOfMonth;
    }

    public static int toCalendarHour(int cfgHour) {
        if (!checkCfgHour(cfgHour)) {
            throw new RuntimeException("cfgHour error:" + cfgHour);
        }
        return cfgHour;
    }

    public static int toCalendarMinute(int cfgMinute) {
        if (!checkCfgMinute(cfgMinute)) {
            throw new RuntimeException("cfgMinute error:" + cfgMinute);
        }
        return cfgMinute;
    }

    public static int toCalendarWeekDay(int cfgWeekDay) {
        if (!Util.checkCfgWeekDay(cfgWeekDay)) {
            throw new RuntimeException("cfg weekDay error:" + cfgWeekDay);
        }
        switch (cfgWeekDay) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                return cfgWeekDay + 1;
            case 7 :
                return Calendar.SUNDAY;
            default:
                throw new RuntimeException("error cfg weekday:" + cfgWeekDay);
        }
    }

    public static int toNaturalWeekDay(int calendarWeekDay) {
        switch (calendarWeekDay) {
            case Calendar.SUNDAY:
                return 7;
            case Calendar.MONDAY:
            case Calendar.TUESDAY:
            case Calendar.WEDNESDAY:
            case Calendar.THURSDAY:
            case Calendar.FRIDAY:
            case Calendar.SATURDAY:
                return calendarWeekDay - 1;
            default:
                throw new RuntimeException("error calendar weekday:" + calendarWeekDay);
        }
    }

    // after >= before
    public static int hourMulti(Calendar before, Calendar after) {
        if (before.after(after)) {
            throw new RuntimeException("calendar:" + before + " after calendar:" + after);
        }
        long delta = after.getTimeInMillis() - before.getTimeInMillis();
        return (int)(delta / 3600000);
    }

    // after >= before
    public static int dayMulti(Calendar before, Calendar after) {
        if (before.after(after)) {
            throw new RuntimeException("calendar:" + before + " after calendar:" + after);
        }
        long delta = after.getTimeInMillis() - before.getTimeInMillis();

        int approximateValue = (int)(delta / 86400000);

        Calendar tmp = (Calendar) before.clone();
        int addValue = 0;
        if (approximateValue > 0) {
            addValue = approximateValue - 1;
            tmp.add(Calendar.DAY_OF_MONTH, addValue);
        }

        // |-------*------|---------------|
        // add    after  add+1          add+2
        //  if (after < add + 1) return add 即下面循环停止时addValue = add + 1
        //  if (after == add + 1) return add + 1 即add + 2 - 1, 即下面循环停止时addValue = add + 2

        while (!tmp.after(after)) { // tmp<=after, 保证结束循环tmp一定大于after
            tmp.add(Calendar.DAY_OF_MONTH, 1);
            addValue += 1;

        }
        return addValue - 1;
    }

    // after >= before
    public static int weekMulti(Calendar before, Calendar after) {
        if (before.after(after)) {
            throw new RuntimeException("calendar:" + before + " after calendar:" + after);
        }
        long delta = after.getTimeInMillis() - before.getTimeInMillis();

        int approximateValue = (int)(delta / (7 * 86400000));

        Calendar tmp = (Calendar) before.clone();
        int addValue = 0;
        if (approximateValue > 0) {
            addValue = approximateValue - 1;
            tmp.add(Calendar.WEEK_OF_MONTH, addValue);
        }

        // |-------*------|---------------|
        // add    after  add+1          add+2
        //  if (after < add + 1) return add
        //  if (after == add + 1) return add + 1 即add + 2 - 1

        while (!tmp.after(after)) { // 保证结束循环tmp一定大于after
            tmp.add(Calendar.WEEK_OF_MONTH, 1);
            addValue += 1;

        }
        return addValue - 1;
    }

    // after >= before
    public static int monthMulti(Calendar before, Calendar after) {
        if (before.after(after)) {
            throw new RuntimeException("calendar:" + before + " after calendar:" + after);
        }
        Calendar tmp = (Calendar) before.clone();
        int addValue = 0;
        // |-------*------|---------------|
        // add    after  add+1          add+2
        //  if (after < add + 1) return add
        //  if (after == add + 1) return add + 1 即add + 2 - 1

        while (!tmp.after(after)) { // 保证结束循环tmp一定大于after
            tmp.add(Calendar.MONTH, 1);
            addValue += 1;

        }
        return addValue - 1;
    }

    public static int rangeMulti(Calendar before, Calendar after, long rangeMills) {
        if (before.after(after)) {
            throw new RuntimeException("calendar:" + before + " after calendar:" + after);
        }
        long delta = after.getTimeInMillis() - before.getTimeInMillis();
        return (int)(delta / rangeMills);
    }

    // 将Calendar设置成下一分的整分
    public static void setCalendarToNextMinuteSharp(Calendar calendar) {
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.MINUTE, 1);
    }

    // 将Calendar设置为当前分的整分
    public static void setCalendarMinuteSharp(Calendar calendar) {
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }

    // 将Calendar设置为当前分的整分
    public static void setCalendarDaySharp(Calendar calendar) {
        setCalendarMinuteSharp(calendar);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
    }

    public static TimeRange toGsTimeRange(cfg.time.TimeRange timeRange) {
        TimeRangesBase gsTimeRanges = null;
        switch (timeRange.getTypeId()) {
            case cfg.time.DayRanges.TYPE_ID: {
                gsTimeRanges = new DayRanges((cfg.time.DayRanges)timeRange);
                break;
            }
            case cfg.time.WeekRanges.TYPE_ID: {
                gsTimeRanges = new WeekRanges((cfg.time.WeekRanges)timeRange);
                break;
            }
            case cfg.time.OneDayRanges.TYPE_ID: {
                gsTimeRanges =  new OneDayRanges((cfg.time.OneDayRanges)timeRange);
                break;
            }
            case cfg.time.OneDayRange.TYPE_ID: {
                cfg.time.OneDayRange cfg = (cfg.time.OneDayRange)timeRange;
                OneDayRanges oneDayRanges = new OneDayRanges();
                oneDayRanges.addRange(new OneDayRangeStruct(new OneDayTimeStruct(cfg.beginTime), new OneDayTimeStruct(cfg.endTime)));
                gsTimeRanges = oneDayRanges;
                break;
            }
            case cfg.time.DayRange.TYPE_ID: {
                cfg.time.DayRange cfg = (cfg.time.DayRange)timeRange;
                DayRanges dayRanges = new DayRanges();
                dayRanges.addRange(new DayRangeStruct(new DayTimeStruct(cfg.beginTime), new DayTimeStruct(cfg.endTime)));
                gsTimeRanges = dayRanges;
                break;
            }
            case cfg.time.WeekRange.TYPE_ID: {
                cfg.time.WeekRange cfg = (cfg.time.WeekRange)timeRange;
                WeekRanges weekRanges = new WeekRanges();
                weekRanges.addRange(new WeekRangeStruct(new WeekTimeStruct(cfg.beginTime), new WeekTimeStruct(cfg.endTime)));
                gsTimeRanges = weekRanges;
                break;
            }
            case cfg.time.DateRange.TYPE_ID: {
                return new DateTimeRange((cfg.time.DateRange)timeRange);

            }
            case cfg.time.MultipleDayRange.TYPE_ID: {
                cfg.time.MultipleDayRange cfg = (cfg.time.MultipleDayRange)timeRange;
                gsTimeRanges = new MultipleDayRange(cfg);
                break;
            }
            case cfg.time.MultipleWeekRange.TYPE_ID: {
                cfg.time.MultipleWeekRange cfg = (cfg.time.MultipleWeekRange)timeRange;
                gsTimeRanges = new MultipleWeekRange(cfg);
                break;
            }
            case cfg.time.ServerStartTimeRange.TYPE_ID: {
                return new ServerStartTimeRange((cfg.time.ServerStartTimeRange)timeRange);
            }
            default: {
                throw new RuntimeException("unknown TimeRange typeId:" + timeRange.getTypeId());
            }
        }
        gsTimeRanges.construct();
        return gsTimeRanges;
    }

    /**
     * 计算两个时间戳之间所跨天数 同一天返回0
     * @param after
     * @param before
     * @return 两个时间戳之间所跨天数
     * after >= before
     */
    public static long getDays(long after, long before) {
        if (after < before) {
            throw new RuntimeException("getDays params before must be less or equal after, but now after:" + after + ", before:" + before);
        }

        Calendar start = Calendar.getInstance();
        start.setTimeInMillis(before);
        start.set(Calendar.HOUR_OF_DAY, 0);
        start.set(Calendar.MINUTE, 0);
        start.set(Calendar.SECOND, 0);
        start.set(Calendar.MILLISECOND, 0);

        Calendar end = Calendar.getInstance();
        end.setTimeInMillis(after);
        end.set(Calendar.HOUR_OF_DAY, 0);
        end.set(Calendar.MINUTE, 0);
        end.set(Calendar.SECOND, 0);
        end.set(Calendar.MILLISECOND, 0);

        return dayMulti(start, end);
    }
}
