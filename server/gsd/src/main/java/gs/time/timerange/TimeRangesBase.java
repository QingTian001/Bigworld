package gs.time.timerange;

import gs.time.Util;
import gs.time.timerange.timerangestruct.DateRangeStruct;
import gs.time.timerange.timerangestruct.TimeRangeStruct;
import gs.time.timestruct.DateTimeStruct;
import gs.time.timestruct.TimeStruct;

import java.util.Calendar;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Created by zyao on 2020/12/26 16:44
 */
public abstract class TimeRangesBase<R extends TimeStruct<R>, T extends TimeRangeStruct<R>> extends TimeRange {

    protected TreeSet<T> rangeStructs = new TreeSet<>();

    protected TreeMap<DateTimeStruct, DateRangeStruct> dateRangeStructs = new TreeMap<>();

    @Override
    public DateRangeStruct getNextRangeStruct(DateRangeStruct cur) {
        var e = dateRangeStructs.higherEntry(cur.beginTime);
        if (e != null) {
            return e.getValue();
        }
        return null;
    }

    @Override
    public DateRangeStruct getFirstRangeStruct(Calendar now) {
        DateTimeStruct dtStruct = Util.toDateTimeStruct(now);
        var e = dateRangeStructs.lowerEntry(dtStruct);
        if (e != null) {
            DateRangeStruct drs = e.getValue();
            if (drs.contains(dtStruct)) {
                return drs;
            }
        }
        e = dateRangeStructs.higherEntry(dtStruct);
        if (e != null) {
            return e.getValue();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    protected boolean checkRange(T rangeStruct) {
        for (T s : rangeStructs) {
            if (s.interact(rangeStruct)) {
                return false;
            }
        }
        return true;
    }

    public void addRange(T rangeStruct) {
        if (!checkRange(rangeStruct)) {
            throw new RuntimeException("rangeStruct " + rangeStruct.toString() + "interact with other rangeStruct");
        }
        rangeStructs.add(rangeStruct);
    }

    public abstract void construct();
}
