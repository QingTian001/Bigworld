package gs.sysmodule.combine;

/**
 * Created by zyao on 2020/12/21 16:52
 */
public abstract class CombineData {

    public CombineKey getCombineKey() {
        return new DefaultCombineKey(this.getClass());
    }
}
