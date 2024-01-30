package xbean;

public class Extensions {
          public static String tostring_list_xbean_TestInnerBean(pcore.db.collection.XList<xbean.TestInnerBean> x) {
                StringBuilder _s = new StringBuilder(); _s.append('[');
                for(var _e : x) {
                _s.append(xbean.Extensions.tostring_xbean_TestInnerBean(_e)).append(',');
                }_s.append(']'); return _s.toString();
          } public static String tostring_map_int_xbean_TestInnerBean(pcore.db.collection.XMap<Integer,xbean.TestInnerBean> x) {
                StringBuilder _s = new StringBuilder(); _s.append('[');
                for(var _e : x.entrySet()) {
                _s.append(_e.getKey()).append('=').append(xbean.Extensions.tostring_xbean_TestInnerBean(_e.getValue())).append(',');
                }_s.append(']'); return _s.toString();
          } public static String tostring_xbean_RoleInfo(xbean.RoleInfo x) {
                return x.toString();
          } public static String tostring_xbean_XString(xbean.XString x) {
                return x.toString();
          } public static String tostring_xbean_XLong(xbean.XLong x) {
                return x.toString();
          } public static String tostring_xbean_TestInnerBean(xbean.TestInnerBean x) {
                return x.toString();
          } public static String tostring_xbean_AccountUser(xbean.AccountUser x) {
                return x.toString();
          } public static String tostring_xbean_TestBean(xbean.TestBean x) {
                return x.toString();
          } public static String tostring_set_int(pcore.db.collection.XSet<Integer> x) {
                StringBuilder _s = new StringBuilder(); _s.append('[');
                for(var _e : x) {
                _s.append(_e).append(',');
                }_s.append(']'); return _s.toString();
          } public static String tostring_xbean_User(xbean.User x) {
                return x.toString();
          } public static String tostring_xbean_ServerInfo(xbean.ServerInfo x) {
                return x.toString();
          } 
        public static void marshal_compatible_list_xbean_TestInnerBean(pcore.db.collection.XList<xbean.TestInnerBean> x, pcore.marshal.Octets os) {
            os.writeSize(x.size());
            for(var _e : x.getDirectlyAccessWrapper()) {
            xbean.Extensions.marshal_compatible_xbean_TestInnerBean(_e,os);
            }
        } public static void marshal_compatible_map_int_xbean_TestInnerBean(pcore.db.collection.XMap<Integer,xbean.TestInnerBean> x, pcore.marshal.Octets os) {
            os.writeSize(x.size());
            for(var _e : x.getDirectlyAccessWrapper().entrySet()) {
            os.writeInt(_e.getKey());
            xbean.Extensions.marshal_compatible_xbean_TestInnerBean(_e.getValue(),os);
            }
        } public static void marshal_compatible_xbean_RoleInfo(xbean.RoleInfo x, pcore.marshal.Octets os) {
            x.marshalCompatible(os);
        } public static void marshal_compatible_xbean_XString(xbean.XString x, pcore.marshal.Octets os) {
            x.marshalCompatible(os);
        } public static void marshal_compatible_xbean_XLong(xbean.XLong x, pcore.marshal.Octets os) {
            x.marshalCompatible(os);
        } public static void marshal_compatible_xbean_TestInnerBean(xbean.TestInnerBean x, pcore.marshal.Octets os) {
            x.marshalCompatible(os);
        } public static void marshal_compatible_xbean_AccountUser(xbean.AccountUser x, pcore.marshal.Octets os) {
            x.marshalCompatible(os);
        } public static void marshal_compatible_xbean_TestBean(xbean.TestBean x, pcore.marshal.Octets os) {
            x.marshalCompatible(os);
        } public static void marshal_compatible_set_int(pcore.db.collection.XSet<Integer> x, pcore.marshal.Octets os) {
            os.writeSize(x.size());
            for(var _e : x.getDirectlyAccessWrapper()) {
            os.writeInt(_e);
            }
        } public static void marshal_compatible_xbean_User(xbean.User x, pcore.marshal.Octets os) {
            x.marshalCompatible(os);
        } public static void marshal_compatible_xbean_ServerInfo(xbean.ServerInfo x, pcore.marshal.Octets os) {
            x.marshalCompatible(os);
        } 
        public static pcore.db.collection.XList<xbean.TestInnerBean> unmarshal_compatible_list_xbean_TestInnerBean(pcore.marshal.Octets os) {
            var n = Math.min(os.readSize(), os.size() + 1);
            pcore.db.collection.XList<xbean.TestInnerBean> x = pcore.db.collection.Factory.newList(n, true);
            var w = x.getDirectlyAccessWrapper();
            for(int i = 0 ; i < n ; i++) {
            w.add(xbean.Extensions.unmarshal_compatible_xbean_TestInnerBean(os));
            }return x;
        } public static pcore.db.collection.XMap<Integer,xbean.TestInnerBean> unmarshal_compatible_map_int_xbean_TestInnerBean(pcore.marshal.Octets os) {
            var n = Math.min(os.readSize(), os.size() + 1);
            pcore.db.collection.XMap<Integer,xbean.TestInnerBean> x = pcore.db.collection.Factory.newMap(n, true);
            var w = x.getDirectlyAccessWrapper();
            for(int i = 0 ; i < n ; i++) {
            w.put(os.readInt(), xbean.Extensions.unmarshal_compatible_xbean_TestInnerBean(os));
            }return x;
        } public static xbean.RoleInfo unmarshal_compatible_xbean_RoleInfo(pcore.marshal.Octets os) {
            var x = new xbean.RoleInfo(); x.unmarshalCompatible(os); return x;
        } public static xbean.XString unmarshal_compatible_xbean_XString(pcore.marshal.Octets os) {
            var x = new xbean.XString(); x.unmarshalCompatible(os); return x;
        } public static xbean.XLong unmarshal_compatible_xbean_XLong(pcore.marshal.Octets os) {
            var x = new xbean.XLong(); x.unmarshalCompatible(os); return x;
        } public static xbean.TestInnerBean unmarshal_compatible_xbean_TestInnerBean(pcore.marshal.Octets os) {
            var x = new xbean.TestInnerBean(); x.unmarshalCompatible(os); return x;
        } public static xbean.AccountUser unmarshal_compatible_xbean_AccountUser(pcore.marshal.Octets os) {
            var x = new xbean.AccountUser(); x.unmarshalCompatible(os); return x;
        } public static xbean.TestBean unmarshal_compatible_xbean_TestBean(pcore.marshal.Octets os) {
            var x = new xbean.TestBean(); x.unmarshalCompatible(os); return x;
        } public static pcore.db.collection.XSet<Integer> unmarshal_compatible_set_int(pcore.marshal.Octets os) {
            var n = Math.min(os.readSize(), os.size() + 1);
            pcore.db.collection.XSet<Integer> x = pcore.db.collection.Factory.newSet(n);
            var w = x.getDirectlyAccessWrapper();
            for(int i = 0 ; i < n ; i++) {
            w.add(os.readInt());
            }return x;
        } public static xbean.User unmarshal_compatible_xbean_User(pcore.marshal.Octets os) {
            var x = new xbean.User(); x.unmarshalCompatible(os); return x;
        } public static xbean.ServerInfo unmarshal_compatible_xbean_ServerInfo(pcore.marshal.Octets os) {
            var x = new xbean.ServerInfo(); x.unmarshalCompatible(os); return x;
        } 
}
