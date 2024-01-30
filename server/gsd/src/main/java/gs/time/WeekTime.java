package gs.time;

import pcore.collection.IntSet;

import java.util.Calendar;

/**
 * Created by zyao on 2020/4/15 10:12
 */
public class WeekTime extends Time {
    private IntSet weekDays = new IntSet();

    public WeekTime(cfg.time.CompleteWeekTime cfg) {
        super(cfg);
        for (int weekDay : cfg.weekDay) {
            addCfgWeekDay(weekDay);
        }
    }
    public WeekTime() {

    }

    public final void addCfgWeekDay(int weekDay) {
        weekDays.add(Util.toCalendarWeekDay(weekDay));
    }

    @Override
    public boolean match(Calendar calendar) {
        if (!super.match(calendar)) {
            return false;
        }
        if (weekDays.size() > 0) {
            if (!weekDays.contains(calendar.get(Calendar.DAY_OF_WEEK))) {
                return false;
            }
        }
        return true;
    }
}
