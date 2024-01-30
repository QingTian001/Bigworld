package map.util;

import cfg.CfgMgr;
import cfg.abtest.AbTest;
import cfg.abtest.EAbTestGroup;
import cfg.abtest.EAbTestState;
import cfg.abtest.FixModuleBean;
import pcore.collection.Int2ObjectHashMap;
import pcore.db.Trace;

/**
 * Created by zyao on 2020/12/29 11:29
 */
public class AbTestState {

    private static final int ROLE_ID_OFFSET = 12;
    private static final int GROUP_NUM = 8;

    private static volatile Int2ObjectHashMap<Integer> states = new Int2ObjectHashMap<>();
    private static volatile Int2ObjectHashMap<Int2ObjectHashMap<AbTest>> abTestCfgs;

    public static void loadCfg() {
        Int2ObjectHashMap<Int2ObjectHashMap<AbTest>> tmpAbTestCfgs = new Int2ObjectHashMap<>();
        for (AbTest cfg : CfgMgr.ins.getAbTestList()) {
            tmpAbTestCfgs.computeIfAbsent(cfg.module, k -> new Int2ObjectHashMap<>());

            Int2ObjectHashMap<AbTest> moduleAbTestCfgs = tmpAbTestCfgs.get(cfg.module);

            if (moduleAbTestCfgs.containsKey(cfg.designState)) {
                throw new RuntimeException("duplicated AbTestDesignState:" + cfg.designState);
            }

            moduleAbTestCfgs.put(cfg.designState, cfg);
        }
        abTestCfgs = tmpAbTestCfgs;
    }

    public static void setStates(Int2ObjectHashMap<Integer> states) {
        Trace.info("AbTestState. setStates called");
        AbTestState.states = states;
    }

    public static boolean isAbTestOpen(int eAbTestModule) {
         Integer state = states.get(eAbTestModule);
         if (state != null) {
             return state == EAbTestState.OPEN;
         }
         return false;
    }

    public static int getAbTestDesignState(long roleId) {
        int eAbTestGroup = getAbTestGroup(roleId);
        return CfgMgr.ins.getAbTestGroupMap().get(eAbTestGroup).designState;
    }

    @SuppressWarnings("unchecked")
    public static <T extends FixModuleBean> T getAbTestFixModuleBean(Class<T> clz, int eAbTestModule, int eAbTestState) {
        return (T)abTestCfgs.get(eAbTestModule).get(eAbTestState).fixBean;
    }

    private static int getAbTestGroup(long roleId) {
        long v = roleId >> ROLE_ID_OFFSET;
        long num = v % GROUP_NUM;

        switch ((int)num) {
            case 0:
                return EAbTestGroup.G1;
            case 1:
                return EAbTestGroup.G2;
            case 2:
                return EAbTestGroup.G3;
            case 3:
                return EAbTestGroup.G4;
            case 4:
                return EAbTestGroup.G5;
            case 5:
                return EAbTestGroup.G6;
            case 6:
                return EAbTestGroup.G7;
            case 7:
                return EAbTestGroup.G8;
            default: {
                throw new RuntimeException("unexpected num" + num);
            }
        }
    }
}
