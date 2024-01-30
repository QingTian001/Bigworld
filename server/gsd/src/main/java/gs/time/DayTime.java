package gs.time;

import pcore.collection.IntSet;

import java.util.Calendar;

/**
 * Created by zyao on 2020/4/15 10:12
 */
public class DayTime extends Time {
    private IntSet dayOfMonths = new IntSet();

    public DayTime(cfg.time.CompleteDayTime cfg) {
        super(cfg);
        for (int dayOfMonth : cfg.dayOfMonth) {
            addCfgDayOfMonth(dayOfMonth);
        }
    }

    public DayTime() {

    }

    public final void addCfgDayOfMonth(int dayOfMonth) {
        dayOfMonths.add(Util.toCalendarDayOfMonth(dayOfMonth));
    }

    @Override
    public boolean match(Calendar calendar) {
        if (!super.match(calendar)) {
            return false;
        }
        if (dayOfMonths.size() > 0) {
            if (!dayOfMonths.contains(calendar.get(Calendar.DAY_OF_MONTH))) {
                return false;
            }
        }
        return true;
    }
}
