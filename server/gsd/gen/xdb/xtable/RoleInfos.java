package xtable;

public final class RoleInfos {
    private static final pcore.db.TableLong<xbean.RoleInfo> table =
            new pcore.db.TableLong<xbean.RoleInfo>("RoleInfos", true) {
                @Override
                public xbean.RoleInfo newValue() {
                    return new xbean.RoleInfo();
                }

                @Override
                public xbean.RoleInfo createValue(Object o) {
                    var os = pcore.marshal.Octets.wrap((byte[]) o);
                    return xbean.Extensions.unmarshal_compatible_xbean_RoleInfo(os);
                }

                public long createKey(Object o) {
                    var os = pcore.marshal.Octets.wrap((byte[]) o);
                    return os.readLong();
                }
            };

    public static pcore.db.TableLong<xbean.RoleInfo> table() {
        return table;
    }

    public static xbean.RoleInfo get(long key) {
        return table.get(key);
    }

    public static xbean.RoleInfo createIfNotExist(long key) {
        return table.createIfNotExist(key);
    }

    public static void insert(long key, xbean.RoleInfo value) {
        table.insert(key, value);
    }

    public static void delete(long key) {
        table.delete(key);
    }

    public static xbean.RoleInfo remove(long key) {
        return table.remove(key);
    }

    public static xbean.RoleInfo put(long key, xbean.RoleInfo value) {
        return table.put(key, value);
    }

    public static xbean.RoleInfo select(long key) {
        return table.select(key);
    }
}
