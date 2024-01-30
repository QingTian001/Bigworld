package gs.util;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by HuangQiang on 2019/5/9 21:20
 */

public class IdUtil {

    private final static AtomicLong nextRuntimeId = new AtomicLong();

    /**
     * @return 返回本进程内的运行时唯一id, 每次重启此id都会从1开始
     */
    public static long allocRuntimeId() {
        return nextRuntimeId.incrementAndGet();
    }
}
