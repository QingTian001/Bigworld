package link;

import pcore.db.Trace;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by majikang on 2021/4/7 11:30
 */
public final class LinkLimiter {
    private class Limit {
        private final int limitNum;
        private final long limitMillis;

        private AtomicInteger count;
        private volatile long startTime;

        public Limit(int limitNum, long limitMillis) {
            this.limitNum = limitNum;
            this.limitMillis = limitMillis;
            this.count = new AtomicInteger(0);
        }

        /**
         *  因为只是检查异常情况，所以为了不影响正常用户的效率，采用了不严谨的检查算法。
         *  如果某段时间内没有达到数量，那么这个时间区间内的数量都清空，重新开始计算。
         *  如果是严谨的算法，需要把时间区间向前滑动，保证区间内的协议都是在limitMillis内，并重新计算数量。
         * @return
         */
        public boolean incAndCheck() {
            if (limitNum <= 0 || limitMillis <= 0) {
                return true;
            }

            if (count.getAndIncrement() == 0) {
                startTime = System.currentTimeMillis();
            } else {
                long now = System.currentTimeMillis();
                if (now - startTime <= limitMillis) {
                    if (count.get() >= limitNum) {
                        return false;
                    }
                } else {
                    count.set(0);
                }
            }

            return true;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("{");
            sb.append("limitNum: ").append(limitNum).append(", ");
            sb.append("limitMillis: ").append(limitMillis).append(", ");
            sb.append("count: ").append(count.get()).append(", ");
            sb.append("startTime: ").append(startTime);
            sb.append("}");
            return sb.toString();
        }
    }

    private final LinkConf conf;
    private final LinkSession session;

    private Limit allLimit;
    private Map<Integer, Limit> oneLimits = new ConcurrentHashMap<>();

    public LinkLimiter(LinkConf conf, LinkSession session) {
        this.conf = conf;
        this.session = session;
        this.allLimit = new Limit(conf.getAllLimitNum(), conf.getAllLimitMillis());
    }

    public boolean check(int type) {
        if (!allLimit.incAndCheck()) {
            return false;
        }

        if (!oneLimits.containsKey(type)) {
            oneLimits.putIfAbsent(type, new Limit(conf.getOneLimitNum(), conf.getOneLimitMillis()));
        }

        if (!oneLimits.get(type).incAndCheck()) {
            return false;
        }

        return true;
    }
}
