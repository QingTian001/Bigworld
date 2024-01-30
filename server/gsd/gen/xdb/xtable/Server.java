package xtable;

public final class Server {
    private static final pcore.db.TableLong<xbean.ServerInfo> table =
            new pcore.db.TableLong<xbean.ServerInfo>("Server", true) {
                @Override
                public xbean.ServerInfo newValue() {
                    return new xbean.ServerInfo();
                }

                @Override
                public xbean.ServerInfo createValue(Object o) {
                    var os = pcore.marshal.Octets.wrap((byte[]) o);
                    return xbean.Extensions.unmarshal_compatible_xbean_ServerInfo(os);
                }

                public long createKey(Object o) {
                    var os = pcore.marshal.Octets.wrap((byte[]) o);
                    return os.readLong();
                }
            };

    public static pcore.db.TableLong<xbean.ServerInfo> table() {
        return table;
    }

    public static xbean.ServerInfo get(long key) {
        return table.get(key);
    }

    public static xbean.ServerInfo createIfNotExist(long key) {
        return table.createIfNotExist(key);
    }

    public static void insert(long key, xbean.ServerInfo value) {
        table.insert(key, value);
    }

    public static void delete(long key) {
        table.delete(key);
    }

    public static xbean.ServerInfo remove(long key) {
        return table.remove(key);
    }

    public static xbean.ServerInfo put(long key, xbean.ServerInfo value) {
        return table.put(key, value);
    }

    public static xbean.ServerInfo select(long key) {
        return table.select(key);
    }
}
