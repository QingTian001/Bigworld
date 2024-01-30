package xtable;

public final class Users {
    private static final pcore.db.TableLong<xbean.User> table =
            new pcore.db.TableLong<xbean.User>("Users", true) {
                @Override
                public xbean.User newValue() {
                    return new xbean.User();
                }

                @Override
                public xbean.User createValue(Object o) {
                    var os = pcore.marshal.Octets.wrap((byte[]) o);
                    return xbean.Extensions.unmarshal_compatible_xbean_User(os);
                }

                public long createKey(Object o) {
                    var os = pcore.marshal.Octets.wrap((byte[]) o);
                    return os.readLong();
                }
            };

    public static pcore.db.TableLong<xbean.User> table() {
        return table;
    }

    public static xbean.User get(long key) {
        return table.get(key);
    }

    public static xbean.User createIfNotExist(long key) {
        return table.createIfNotExist(key);
    }

    public static void insert(long key, xbean.User value) {
        table.insert(key, value);
    }

    public static void delete(long key) {
        table.delete(key);
    }

    public static xbean.User remove(long key) {
        return table.remove(key);
    }

    public static xbean.User put(long key, xbean.User value) {
        return table.put(key, value);
    }

    public static xbean.User select(long key) {
        return table.select(key);
    }
}
