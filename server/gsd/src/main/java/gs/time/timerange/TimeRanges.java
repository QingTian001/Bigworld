package gs.time.timerange;

import gs.time.Util;
import gs.time.timerange.timerangestruct.DateRangeStruct;
import gs.time.timerange.timerangestruct.TimeRangeStruct;
import gs.time.timestruct.DateTimeStruct;
import gs.time.timestruct.TimeStruct;
import pcore.collection.IntSet;

import java.util.*;

/**
 * Created by zyao on 2020/4/16 14:31
 */
public abstract class TimeRanges<R extends TimeStruct<R>, T extends TimeRangeStruct<R>> extends TimeRangesBase<R, T> {

    private final static int YEAR_SIZE = 2;

    protected IntSet year = new IntSet();
    protected IntSet month = new IntSet();

    public TimeRanges(cfg.time.TimeRanges cfg) {
        for (int year : cfg.year) {
            addCfgYear(year);
        }
        for (int month : cfg.month) {
            addCfgMonth(month);
        }
    }

    public TimeRanges() {
    }

    public void addCfgYear(int cfgYear) {
        year.add(cfgYear);
    }

    public void addCfgMonth(int cfgMonth) {
        if (!Util.checkCfgMonth(cfgMonth)) {
            throw new RuntimeException("cfg month error :" + cfgMonth);
        }
        month.add(cfgMonth - 1);
    }

    @Override
    public final void construct() {
        int[] years = null;
        if (year.size() > 0) {
            years = year.toArray(new int[0]);
        }
        else {
            years = new int[YEAR_SIZE]; // TODO 后面优化下内存
            int yearNow  = Calendar.getInstance().get(Calendar.YEAR);
            for (int i = 0; i < YEAR_SIZE; i++) {
                years[i] = yearNow + i;
            }
        }
        int[] months = null;
        if (month.size() > 0) {
            months = month.toArray(new int[0]);
        }
        else {
            months = Util.allMonths;
        }

        for (int year : years) {
            for (int month : months) {
                for (T s : rangeStructs) {
                    Collection<DateRangeStruct> drsList = toDateRangeStruct(year, month, s);
                    for (DateRangeStruct drs : drsList) {
                        dateRangeStructs.put(drs.beginTime, drs);
                    }
                }
            }
        }
    }

    public abstract Collection<DateRangeStruct> toDateRangeStruct(int year, int month, T rangeStruct);
}
