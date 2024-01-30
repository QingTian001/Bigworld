package gs.time.timerange;

import cfg.CfgMgr;
import gs.time.Util;
import gs.time.timerange.timerangestruct.DateRangeStruct;
import gs.time.timestruct.DateTimeStruct;
import gs.time.timestruct.WeekTimeStruct;

import java.util.Calendar;
import java.util.Map;

/**
 * Created by zyao on 2020/12/25 10:46
 */

public class MultipleWeekRange extends TimeRangesBase<DateTimeStruct, DateRangeStruct> {

    private DateTimeStruct dateTimeStruct;
    private WeekTimeStruct beginTime;
    private WeekTimeStruct endTime;
    private int deltaWeeksNum;
    private int loopWeeksNum;


    public MultipleWeekRange(cfg.time.MultipleWeekRange cfg) {
        dateTimeStruct = new DateTimeStruct(cfg.baseDateTime);
        dateTimeStruct.minute = 0;
        dateTimeStruct.hour = 0;
        beginTime = new WeekTimeStruct(cfg.beginTime);
        endTime = new WeekTimeStruct(cfg.endTime);
        deltaWeeksNum = cfg.deltaWeeksNum;
        loopWeeksNum = cfg.loopWeeksNum;

        if (cfg.loopWeeksNum < cfg.deltaWeeksNum || cfg.loopWeeksNum < 1 || cfg.deltaWeeksNum < 1) {
            throw new RuntimeException("error cfg: deltaWeeksNum:" + cfg.deltaWeeksNum + ";loopWeeksNum:" + cfg.loopWeeksNum);
        }
    }

    @Override
    public final void construct() {
        Calendar calendar = Util.toCalendar(dateTimeStruct);
        Calendar now = Calendar.getInstance();
        Calendar before = (Calendar)now.clone();

        int yearNow = now.get(Calendar.YEAR);

        before.add(Calendar.DAY_OF_MONTH, - 2 * loopWeeksNum);

        while (calendar.before(before)) {
            calendar.add(Calendar.WEEK_OF_MONTH, loopWeeksNum);
        }

        calendar.set(Calendar.DAY_OF_WEEK, beginTime.weekDay);
        calendar.set(Calendar.HOUR_OF_DAY, beginTime.hour);
        calendar.set(Calendar.MINUTE, beginTime.minute);

        while (calendar.get(Calendar.YEAR) < yearNow + 2) {
            Calendar cal = (Calendar)calendar.clone();
            cal.add(Calendar.WEEK_OF_MONTH, deltaWeeksNum - 1);
            cal.set(Calendar.DAY_OF_WEEK, endTime.weekDay);
            cal.set(Calendar.HOUR_OF_DAY, endTime.hour);
            cal.set(Calendar.MINUTE, endTime.minute);

            DateTimeStruct dtBegin = Util.toDateTimeStruct(calendar);
            DateTimeStruct dtEnd = Util.toDateTimeStruct(cal);
            rangeStructs.add(new DateRangeStruct(dtBegin, dtEnd));

            calendar.add(Calendar.WEEK_OF_MONTH, loopWeeksNum);
        }

        for (DateRangeStruct s : rangeStructs) {
            dateRangeStructs.put(s.beginTime, s);
        }
    }

    public static void main(String[] args) {

//        CfgMgr.load();
//        TestTime cfg = CfgMgr.ins.getTestTime(1);
//
//        MultipleWeekRange range = new MultipleWeekRange(cfg.weekTimeRange);
//
//        range.construct();
//
//        for (Map.Entry<DateTimeStruct, DateRangeStruct> entry : range.dateRangeStructs.entrySet() ) {
//
//            System.out.println(entry.getValue());
//        }
//
//        DateRangeStruct drt = range.getFirstRangeStruct(Calendar.getInstance());
//
//        System.out.println(drt);
    }
}
