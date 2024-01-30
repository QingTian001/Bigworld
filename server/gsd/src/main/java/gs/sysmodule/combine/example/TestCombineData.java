package gs.sysmodule.combine.example;

import gs.sysmodule.combine.CombineData;

/**
 * Created by zyao on 2020/12/21 20:02
 */
public class TestCombineData extends CombineData {
    public final int id;
    public final String value;
    public TestCombineData(int id, String value) {
        this.id = id;
        this.value = value;
    }
}
