package xtable;

public final class Test {
    private static final pcore.db.TableLong<xbean.TestBean> table =
            new pcore.db.TableLong<xbean.TestBean>("Test", true) {
                @Override
                public xbean.TestBean newValue() {
                    return new xbean.TestBean();
                }

                @Override
                public xbean.TestBean createValue(Object o) {
                    var os = pcore.marshal.Octets.wrap((byte[]) o);
                    return xbean.Extensions.unmarshal_compatible_xbean_TestBean(os);
                }

                public long createKey(Object o) {
                    var os = pcore.marshal.Octets.wrap((byte[]) o);
                    return os.readLong();
                }
            };

    public static pcore.db.TableLong<xbean.TestBean> table() {
        return table;
    }

    public static xbean.TestBean get(long key) {
        return table.get(key);
    }

    public static xbean.TestBean createIfNotExist(long key) {
        return table.createIfNotExist(key);
    }

    public static void insert(long key, xbean.TestBean value) {
        table.insert(key, value);
    }

    public static void delete(long key) {
        table.delete(key);
    }

    public static xbean.TestBean remove(long key) {
        return table.remove(key);
    }

    public static xbean.TestBean put(long key, xbean.TestBean value) {
        return table.put(key, value);
    }

    public static xbean.TestBean select(long key) {
        return table.select(key);
    }
}
