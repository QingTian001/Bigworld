package gs.sysmodule.combine;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zyao on 2020/12/21 16:54
 */

// 只支持单线程T
public abstract class CombineExecute<K, T extends CombineData> implements Runnable {

    private Map<K, T> datas = new HashMap<>();

    protected abstract void process(Map<K, T> datas);

    @Override
    public void run() {
        process(datas);
    }

    /**
     * @param key 索引
     * @param data 必须是事务外对象, 不包含任何db数据
     */
    public void tryExecute(K key, T data) {
        datas.put(key, data);
    }
}
