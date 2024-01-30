package gs.util.statistic;

import gs.util.timer.Timer;
import pcore.db.Procedure;
import pcore.io.Protocol;

import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zyao on 2020/2/22 12:00
 */
public final class StatisticUtil {

    public enum STYPE {
        TOTAL_EXEC_NUM,
        TOTAL_EXEC_TIME,
        AVG_EXEC_TIME,
        MAX_EXEC_TIME,
    }

    private static class StatisticComparator implements Comparator<Entry> {

        private final Method m;
        StatisticComparator(Method m) {
            this.m = m;
        }
        @Override
        public int compare(Entry o1, Entry o2) {
            try {
                double d1 = (double)m.invoke(o1);
                double d2 = (double)m.invoke(o2);

                int ret = Double.compare(d2, d1);
                return ret == 0 ? Integer.compare(o2.hashCode(), o1.hashCode()) : ret;

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static Map<STYPE, StatisticComparator> comparatorMap = new HashMap<>();
    private static ConcurrentHashMap<Class<? extends Procedure>, ProcedureEntry> procedureEntryMap = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<Class<? extends Runnable>, RunnableEntry> runnableEntryMap = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<Class<? extends Timer>, TimerEntry> timerEntryMap = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<Class<? extends Protocol>, RecvProtocolEntry> recvProtocolEntryMap = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<Class<? extends Protocol>, SendProtocolEntry> sendProtocolEntryMap = new ConcurrentHashMap<>();

    public static void start() {
        Method[] ms = Entry.class.getDeclaredMethods();
        for (Method m : ms) {
            StatisticFunc f = m.getAnnotation(StatisticFunc.class);
            if (f != null) {
                comparatorMap.put(f.type(), new StatisticComparator(m));
            }
        }
    }

    private static <E extends Entry, R> E getEntry(Class<? extends R> c, Class<E> eClass, Map<Class<? extends R>, E> map) {
        E entry = map.get(c);
        if (entry == null) {
            try {
                entry = eClass.getDeclaredConstructor().newInstance();
                entry.setTargetName(c.getName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            E oldEntry = map.putIfAbsent(c, entry);
            if (oldEntry != null) {
                entry = oldEntry;
            }
        }
        return entry;
    }

    public static void statistic(Procedure p, long nanos) {
        ProcedureEntry entry = getEntry(p.getClass(), ProcedureEntry.class, procedureEntryMap);
        entry.addExecNanos(nanos);
    }

    public static void statistic(Runnable r, long nanos) {
        RunnableEntry entry = getEntry(r.getClass(), RunnableEntry.class, runnableEntryMap);
        entry.addExecNanos(nanos);
    }

    public static void statistic(Timer t, long nanos) {
        TimerEntry entry = getEntry(t.getClass(), TimerEntry.class, timerEntryMap);
        entry.addExecNanos(nanos);
    }

    public static void statisticRecvProt(Protocol recvPro, long size) {
        RecvProtocolEntry entry = getEntry(recvPro.getClass(), RecvProtocolEntry.class, recvProtocolEntryMap);
        entry.addExecNanos(size);
    }

    public static void statisticSendProt(Protocol sendPro, long size) {
        SendProtocolEntry entry = getEntry(sendPro.getClass(), SendProtocolEntry.class, sendProtocolEntryMap);
        entry.addExecNanos(size);
    }

    private static StatisticComparator getComparator(STYPE stype) {
        return comparatorMap.get(stype);
    }

    private static <E extends Entry, R> Collection<Entry> getTopEntries(int topNum, STYPE stype, Map<Class<? extends R>, E> map) {
        TreeSet<Entry> set = new TreeSet<>(getComparator(stype));
        var it = map.entrySet().iterator();
        for (; it.hasNext(); ) {
            var e = it.next();
            set.add(e.getValue());
            if (set.size() == topNum + 1) {
                set.pollLast();
            }
        }
        return set;
    }

    public static Collection<Entry> getProcedureTopEntries(int topNum, STYPE stype) {
        return getTopEntries(topNum, stype, procedureEntryMap);
    }

    public static Collection<Entry> getRunnableTopEntries(int topNum, STYPE stype) {
        return getTopEntries(topNum, stype, runnableEntryMap);
    }

    public static Collection<Entry> getTimerTopEntries(int topNum, STYPE stype) {
        return getTopEntries(topNum, stype, timerEntryMap);
    }

    public static Collection<Entry> getRecvProtTopEntries(int topNum, STYPE stype) {
        return getTopEntries(topNum, stype, recvProtocolEntryMap);
    }

    public static Collection<Entry> getSendProtTopEntries(int topNum, STYPE stype) {
        return getTopEntries(topNum, stype, sendProtocolEntryMap);
    }

    public static long getProcedureNums() {
        return procedureEntryMap.size();
    }

    public static long getRunnableNums() {
        return runnableEntryMap.size();
    }

    public static long getTimerNums() {
        return timerEntryMap.size();
    }

    public static long getRecvProtNums() {
        return recvProtocolEntryMap.size();
    }

    public static long getSendProtNums() {
        return sendProtocolEntryMap.size();
    }
}
