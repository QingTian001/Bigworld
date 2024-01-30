package gs.sysmodule.combine;

/**
 * Created by zyao on 2021/3/9 18:00
 */
public class DefaultCombineKey extends CombineKey {

    private Class<? extends CombineData> clz = null;

    public DefaultCombineKey(Class<? extends CombineData> clz) {
        this.clz = clz;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof DefaultCombineKey)) {
            return false;
        }
        DefaultCombineKey other = (DefaultCombineKey)obj;
        return this.clz == other.clz;
    }

    @Override
    public int hashCode() {
        return clz.hashCode();
    }
}
