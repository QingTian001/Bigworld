package gs.sysmodule.combine.example;

import gs.util.GProcedure;
import gs.util.ProcedureUtil;

/**
 * Created by zyao on 2020/12/22 9:40
 */
public class PTestCombine extends GProcedure {
    @Override
    protected boolean doProcess() {

        ProcedureUtil.executeCombineWhileSucc(1, new TestCombineData(1, "zz"));
        ProcedureUtil.executeCombineWhileSucc(20, new TestCombineData(2, "zz"));
        ProcedureUtil.executeCombineWhileSucc(1, new TestCombineData(1, "yy"));


        return true;
    }
}
