package gs.util.statistic;

/**
 * Created by zyao on 2020/2/22 12:06
 */
public class Entry {
    private volatile long totalExecNum;
    private volatile double totalExecTimeNanos;
    private volatile double minExecTimeNanos;
    private volatile double maxExecTimeNanos;
    private volatile double avgExecTimeNanos;
    private String targetName;

    @StatisticFunc(type = StatisticUtil.STYPE.AVG_EXEC_TIME)
    final double getAvgExecTimeNanos() {
        return avgExecTimeNanos;
    }

    @StatisticFunc(type = StatisticUtil.STYPE.TOTAL_EXEC_TIME)
    final double getTotalExecTimeNanos() {
        return totalExecTimeNanos;
    }

    final double getMinExecTimeNanos() {
        return minExecTimeNanos;
    }

    @StatisticFunc(type = StatisticUtil.STYPE.MAX_EXEC_TIME)
    final double getMaxExecTimeNanos() {
        return maxExecTimeNanos;
    }

    @StatisticFunc(type = StatisticUtil.STYPE.TOTAL_EXEC_NUM)
    final double getTotalExecNum() {
        return totalExecNum;
    }

    synchronized void addExecNanos(long nanos) {
        totalExecNum++;
        totalExecTimeNanos += nanos;
        avgExecTimeNanos = totalExecTimeNanos / totalExecNum;
        if (minExecTimeNanos == 0 || nanos < minExecTimeNanos) {
            minExecTimeNanos = nanos;
        }
        if (nanos > maxExecTimeNanos) {
            maxExecTimeNanos = nanos;
        }
    }

    @Override
    public String toString() {
        return String.format("%s =>\nmaxTime:%s,\tavgTime:%s,\ttotalNum:%d,\ttotalTime:%s",
                targetName,getFormatTime(maxExecTimeNanos),getFormatTime(avgExecTimeNanos),totalExecNum,getFormatTime(totalExecTimeNanos));
    }

    @Override
    public final int hashCode() {
        return super.hashCode();
    }

    final void setTargetName(String name){
        this.targetName = name;
    }

    private String getFormatTime(double ns){
        if(ns<1000){
            return String.format("%.0fns",ns);
        }else if(ns<1000000){
            return String.format("%.1fus",ns/1000);
        }else if(ns<1000000000){
            return String.format("%.1fms",ns/1000000);
        }else{
            return String.format("%.1fs",ns/1000000000);
        }
    }
}
