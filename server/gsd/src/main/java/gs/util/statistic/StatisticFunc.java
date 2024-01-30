package gs.util.statistic;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by zyao on 2020/2/23 12:41
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})

public @interface StatisticFunc {

    public StatisticUtil.STYPE type();
}
