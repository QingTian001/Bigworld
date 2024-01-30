package gs.time.timerange;

import cfg.CfgMgr;
import gs.time.Util;
import gs.time.timerange.timerangestruct.DateRangeStruct;
import gs.time.timestruct.DateTimeStruct;
import gs.time.timestruct.OneDayTimeStruct;

import java.util.Calendar;
import java.util.Map;

/**
 * Created by zyao on 2020/12/25 10:46
 */

/**
 * beginTime忽略hour和minute + deltaDaysNum = endTime忽略hour和minute
 * 	=======|-----------|-----------|-----------|============|-----------|-----------|-----------|=====
 *         1           2           3           4           7(1)        8(2)        9(3)        10(4)
 * 	1: beginTime忽略hour和minute
 * 	4: endTime忽略hour和minute + 1
 *  deltaDaysNum = 4 - 1 = 3
 * 	loopdaysNum = 7 - 1 = 6
 */
public class MultipleDayRange extends TimeRangesBase<DateTimeStruct, DateRangeStruct> {

    private DateTimeStruct dateTimeStruct;
    private OneDayTimeStruct beginTime;
    private OneDayTimeStruct endTime;
    private int deltaDaysNum;
    private int loopDaysNum;


    public MultipleDayRange(cfg.time.MultipleDayRange cfg) {
        dateTimeStruct = new DateTimeStruct(cfg.baseDateTime);
        dateTimeStruct.hour = 0;
        dateTimeStruct.minute = 0;
        beginTime = new OneDayTimeStruct(cfg.beginTime);
        endTime = new OneDayTimeStruct(cfg.endTime);
        deltaDaysNum = cfg.deltaDaysNum;
        loopDaysNum = cfg.loopDaysNum;

        if (cfg.loopDaysNum < cfg.deltaDaysNum || cfg.loopDaysNum < 1 || cfg.deltaDaysNum < 1) {
            throw new RuntimeException("error cfg: deltaDaysNum:" + cfg.deltaDaysNum + ";loopDaysNum:" + cfg.loopDaysNum);
        }
    }

    @Override
    public final void construct() {
        Calendar calendar = Util.toCalendar(dateTimeStruct);
        Calendar now = Calendar.getInstance();
        Calendar before = (Calendar)now.clone();

        int yearNow = now.get(Calendar.YEAR);

        before.add(Calendar.DAY_OF_MONTH, - 2 * loopDaysNum);

        while (calendar.before(before)) {
            calendar.add(Calendar.DAY_OF_MONTH, loopDaysNum);
        }

        calendar.set(Calendar.HOUR_OF_DAY, beginTime.hour);
        calendar.set(Calendar.MINUTE, beginTime.minute);

        while (calendar.get(Calendar.YEAR) < yearNow + 2) {
            Calendar cal = (Calendar)calendar.clone();
            cal.add(Calendar.DAY_OF_MONTH, deltaDaysNum - 1);
            cal.set(Calendar.HOUR_OF_DAY, endTime.hour);
            cal.set(Calendar.MINUTE, endTime.minute);

            DateTimeStruct dtBegin = Util.toDateTimeStruct(calendar);
            DateTimeStruct dtEnd = Util.toDateTimeStruct(cal);
            rangeStructs.add(new DateRangeStruct(dtBegin, dtEnd));

            calendar.add(Calendar.DAY_OF_MONTH, loopDaysNum);
        }

        for (DateRangeStruct s : rangeStructs) {
            dateRangeStructs.put(s.beginTime, s);
        }
    }

    public static void main(String[] args) {

//        CfgMgr.load();
//        TestTime cfg = CfgMgr.ins.getTestTime(1);
//
//        MultipleDayRange range = new MultipleDayRange(cfg.timeRange);
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
