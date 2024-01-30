package gs.time.timestruct;

import java.util.Calendar;

/**
 * Created by zyao on 2020/4/16 14:04
 */
public abstract class TimeStruct<T extends TimeStruct<T>> implements Comparable<T> {
    public DateTimeStruct toDateTimeStruct(Calendar calendar) {
        throw new RuntimeException("not supported");
    }
}
