package xtable;

public final class RoleNameInfos {
    private static final pcore.db.TableRef<String, xbean.XLong> table =
            new pcore.db.TableRef<String, xbean.XLong>("RoleNameInfos", true) {
                @Override
                public xbean.XLong newValue() {
                    return new xbean.XLong();
                }

                @Override
                public xbean.XLong createValue(Object o) {
                    var os = pcore.marshal.Octets.wrap((byte[]) o);
                    return xbean.Extensions.unmarshal_compatible_xbean_XLong(os);
                }

                public String createKey(Object o) {
                    var os = pcore.marshal.Octets.wrap((byte[]) o);
                    return os.readString();
                }
            };

    public static pcore.db.TableRef<String, xbean.XLong> table() {
        return table;
    }

    public static xbean.XLong get(String key) {
        return table.get(key);
    }

    public static xbean.XLong createIfNotExist(String key) {
        return table.createIfNotExist(key);
    }

    public static void insert(String key, xbean.XLong value) {
        table.insert(key, value);
    }

    public static void delete(String key) {
        table.delete(key);
    }

    public static xbean.XLong remove(String key) {
        return table.remove(key);
    }

    public static xbean.XLong put(String key, xbean.XLong value) {
        return table.put(key, value);
    }

    public static xbean.XLong select(String key) {
        return table.select(key);
    }
}
