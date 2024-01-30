package gs.util;

import pcore.db.DbExecutor;
import pcore.misc.TaskQueue;

import java.util.concurrent.Executor;

/**
 * Created by zyao on 2020/7/16 15:23
 */
public class SerialKeyExecutor<K> implements Executor {

    private final TaskQueue[] taskQueues;

    public SerialKeyExecutor(Executor executor, int concurrencyLevel) {
        if (concurrencyLevel < 0 || concurrencyLevel > 0x40000000)
            throw new RuntimeException("Illegal concurrencyLevel: " + concurrencyLevel);

        int capacity = 1;
        while (capacity < concurrencyLevel) {
            capacity = capacity << 1;
        }

        taskQueues = new TaskQueue[capacity];
        for (int i = 0; i < capacity; ++i) {
            taskQueues[i] = new TaskQueue(executor);
        }
    }

    public SerialKeyExecutor(Executor executor) {
        this(executor, Runtime.getRuntime().availableProcessors() * 2);
    }

    public SerialKeyExecutor() {
        this(DbExecutor.getInstance());
    }

    private int getLength() {
        return taskQueues.length;
    }

    /**
     * Applies a supplemental hash function to a given hashCode, which defends
     * against poor quality hash functions. This is critical because HashMap
     * uses power-of-two length hash tables, that otherwise encounter collisions
     * for hashCodes that do not differ in lower bits. Note: Null keys always
     * map to hash 0, thus index 0.
     */
    private static int hash(int h) {
        // This function ensures that hashCodes that differ only by
        // constant multiples at each bit position have a bounded
        // number of collisions (approximately 8 at default load factor).
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    private static int indexFor(int h, int length) {
        return h & (length - 1);
    }

    public void execute(K k, Runnable r) {
        taskQueues[indexFor(hash(k.hashCode()), getLength())].add(r);
    }

    @Override
    public void execute(Runnable command) {
        throw new UnsupportedOperationException("not suppport");
    }

    public static void main(String[] args) {
        SerialKeyExecutor<Long> e = new SerialKeyExecutor<Long>();

        for (long i = 0; i < 1000; i++) {
            final long j = i;
            e.execute(1L,
                    () -> System.out.println(j)
            );
        }

    }
}
