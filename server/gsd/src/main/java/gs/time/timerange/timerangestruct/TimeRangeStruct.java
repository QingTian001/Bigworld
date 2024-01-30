package gs.time.timerange.timerangestruct;

import gs.time.timestruct.TimeStruct;

/**
 * Created by zyao on 2020/4/16 11:46
 */
public abstract class TimeRangeStruct<T extends TimeStruct<T>> implements Comparable<TimeRangeStruct<T>>{
    public T beginTime;
    public T endTime;

    private final boolean overCross;

    public TimeRangeStruct(T beginTime, T endTime) {
        this.beginTime = beginTime;
        this.endTime = endTime;
        int v = beginTime.compareTo(endTime);

        if (v == 0) {
            throw new RuntimeException("beginTime " + beginTime + " equals to endTime " + endTime);
        }
        overCross = v > 0;
    }

    public boolean isOverCross() {
        return overCross;
    }

    public boolean contains(T time) {
        if (!overCross) { // beginTime < endTime
            /**
             * ||--------|------*-------|--------||
             *           b      t       e
             **/
            return time.compareTo(beginTime) > 0 && time.compareTo(endTime) < 0;
        }
        else {
            /**
             * --------|----*--||--*----|--------
             *         b    t      t    e
             **/
            return time.compareTo(beginTime) > 0 || time.compareTo(endTime) < 0;
        }
    }

    public boolean interact(TimeRangeStruct<T> struct) {
        return this.contains(struct.beginTime) || this.contains(struct.endTime);

    }

    @Override
    public int compareTo(TimeRangeStruct<T> o) {
        return beginTime.compareTo(o.beginTime);
    }

    @Override
    public String toString() {
        return beginTime.toString() + "-" + endTime.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TimeRangeStruct)) {
            return false;
        }

        TimeRangeStruct trs = (TimeRangeStruct)obj;
        return trs.beginTime.equals(this.beginTime) && trs.endTime.equals(this.endTime);
    }
}
