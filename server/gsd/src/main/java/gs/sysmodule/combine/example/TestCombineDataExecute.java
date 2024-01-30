package gs.sysmodule.combine.example;

import gs.sysmodule.combine.CombineExecute;
import pcore.db.Trace;

import java.util.Map;

/**
 * Created by zyao on 2020/12/21 20:08
 */
public class TestCombineDataExecute extends CombineExecute<Long, TestCombineData> {
    @Override
    protected void process(Map<Long, TestCombineData> datas) {

        for (Map.Entry<Long, TestCombineData> entry : datas.entrySet()) {
            Trace.debug("key={}, value={},{}", entry.getKey(), entry.getValue().id, entry.getValue().value);
        }
    }
}
