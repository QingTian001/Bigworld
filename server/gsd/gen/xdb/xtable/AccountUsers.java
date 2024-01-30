package xtable;

public final class AccountUsers {
    private static final pcore.db.TableRef<String, xbean.AccountUser> table =
            new pcore.db.TableRef<String, xbean.AccountUser>("AccountUsers", true) {
                @Override
                public xbean.AccountUser newValue() {
                    return new xbean.AccountUser();
                }

                @Override
                public xbean.AccountUser createValue(Object o) {
                    var os = pcore.marshal.Octets.wrap((byte[]) o);
                    return xbean.Extensions.unmarshal_compatible_xbean_AccountUser(os);
                }

                public String createKey(Object o) {
                    var os = pcore.marshal.Octets.wrap((byte[]) o);
                    return os.readString();
                }
            };

    public static pcore.db.TableRef<String, xbean.AccountUser> table() {
        return table;
    }

    public static xbean.AccountUser get(String key) {
        return table.get(key);
    }

    public static xbean.AccountUser createIfNotExist(String key) {
        return table.createIfNotExist(key);
    }

    public static void insert(String key, xbean.AccountUser value) {
        table.insert(key, value);
    }

    public static void delete(String key) {
        table.delete(key);
    }

    public static xbean.AccountUser remove(String key) {
        return table.remove(key);
    }

    public static xbean.AccountUser put(String key, xbean.AccountUser value) {
        return table.put(key, value);
    }

    public static xbean.AccountUser select(String key) {
        return table.select(key);
    }
}
