package gs.util;

import pcore.db.collection.XMap;

import java.util.List;
import java.util.Map;

public class Factory {
    public static <K, V> XMap<K, V> newXBeanMap(boolean valueTypeIsBean) {
        return pcore.db.collection.Factory.newMap(valueTypeIsBean);
    }


    public static <V> List<V> newProtocolList() {
        return pcore.collection.Factory.newList();
    }

    public static <K, V> Map<K, V> newProtocolMap() {
        return pcore.collection.Factory.newMap();
    }
}
