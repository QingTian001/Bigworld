package msg;

public class Extensions {
          public static String tostring_msg_plink_LAnnounceOuterNetAddress(msg.plink.LAnnounceOuterNetAddress x) {
                return x.toString();
          } public static String tostring_msg_map_SMapDebugInfo(msg.map.SMapDebugInfo x) {
                return x.toString();
          } public static String tostring_set_long(java.util.Set<Long> x) {
                StringBuilder _s = new StringBuilder(); _s.append('[');
                for(var _e : x) {
                _s.append(_e).append(',');
                }_s.append(']'); return _s.toString();
          } public static String tostring_msg_plink_LUserOnline(msg.plink.LUserOnline x) {
                return x.toString();
          } public static String tostring_msg_plink_LForward(msg.plink.LForward x) {
                return x.toString();
          } public static String tostring_msg_plink_GForward(msg.plink.GForward x) {
                return x.toString();
          } public static String tostring_msg_gmap_MGMessage(msg.gmap.MGMessage x) {
                return x.toString();
          } public static String tostring_msg_gmap_GMMessage(msg.gmap.GMMessage x) {
                return x.toString();
          } public static String tostring_msg_plink_GMulticast(msg.plink.GMulticast x) {
                return x.toString();
          } public static String tostring_msg_plink_LLinkBroken(msg.plink.LLinkBroken x) {
                return x.toString();
          } public static String tostring_msg_plink_GKickUser(msg.plink.GKickUser x) {
                return x.toString();
          } public static String tostring_msg_plink_GUnBind(msg.plink.GUnBind x) {
                return x.toString();
          } public static String tostring_msg_map_SDebugException(msg.map.SDebugException x) {
                return x.toString();
          } public static String tostring_msg_net_GClientAnnouceServerInfo(msg.net.GClientAnnouceServerInfo x) {
                return x.toString();
          } public static String tostring_msg_plink_GBroadcast(msg.plink.GBroadcast x) {
                return x.toString();
          } public static String tostring_msg_plink_GUserOnline(msg.plink.GUserOnline x) {
                return x.toString();
          } public static String tostring_msg_plink_LUserKeepAlive(msg.plink.LUserKeepAlive x) {
                return x.toString();
          } public static String tostring_msg_net_GServerAnnouceServerInfo(msg.net.GServerAnnouceServerInfo x) {
                return x.toString();
          } public static String tostring_msg_map_Vector2(msg.map.Vector2 x) {
                return x.toString();
          } public static String tostring_msg_map_Vector3(msg.map.Vector3 x) {
                return x.toString();
          } public static String tostring_list_long(java.util.List<Long> x) {
                StringBuilder _s = new StringBuilder(); _s.append('[');
                for(var _e : x) {
                _s.append(_e).append(',');
                }_s.append(']'); return _s.toString();
          } public static String tostring_msg_plink_GBind(msg.plink.GBind x) {
                return x.toString();
          } public static String tostring_array_byte(byte[] x) {
                StringBuilder _s = new StringBuilder(); _s.append('[');
                for(var _e : x) {
                _s.append(_e).append(',');
                }_s.append(']'); return _s.toString();
          } public static String tostring_msg_gmap_GCfgReload(msg.gmap.GCfgReload x) {
                return x.toString();
          } 
        public static void marshal_msg_plink_LAnnounceOuterNetAddress(msg.plink.LAnnounceOuterNetAddress x, pcore.marshal.Octets os) {
            x.marshal(os);
        } public static void marshal_msg_map_SMapDebugInfo(msg.map.SMapDebugInfo x, pcore.marshal.Octets os) {
            x.marshal(os);
        } public static void marshal_set_long(java.util.Set<Long> x, pcore.marshal.Octets os) {
            os.writeSize(x.size());
            for(var _e : x) {
            os.writeLong(_e);
            }
        } public static void marshal_msg_plink_LUserOnline(msg.plink.LUserOnline x, pcore.marshal.Octets os) {
            x.marshal(os);
        } public static void marshal_msg_plink_LForward(msg.plink.LForward x, pcore.marshal.Octets os) {
            x.marshal(os);
        } public static void marshal_msg_plink_GForward(msg.plink.GForward x, pcore.marshal.Octets os) {
            x.marshal(os);
        } public static void marshal_msg_gmap_MGMessage(msg.gmap.MGMessage x, pcore.marshal.Octets os) {
            x.marshal(os);
        } public static void marshal_msg_gmap_GMMessage(msg.gmap.GMMessage x, pcore.marshal.Octets os) {
            x.marshal(os);
        } public static void marshal_msg_plink_GMulticast(msg.plink.GMulticast x, pcore.marshal.Octets os) {
            x.marshal(os);
        } public static void marshal_msg_plink_LLinkBroken(msg.plink.LLinkBroken x, pcore.marshal.Octets os) {
            x.marshal(os);
        } public static void marshal_msg_plink_GKickUser(msg.plink.GKickUser x, pcore.marshal.Octets os) {
            x.marshal(os);
        } public static void marshal_msg_plink_GUnBind(msg.plink.GUnBind x, pcore.marshal.Octets os) {
            x.marshal(os);
        } public static void marshal_msg_map_SDebugException(msg.map.SDebugException x, pcore.marshal.Octets os) {
            x.marshal(os);
        } public static void marshal_msg_net_GClientAnnouceServerInfo(msg.net.GClientAnnouceServerInfo x, pcore.marshal.Octets os) {
            x.marshal(os);
        } public static void marshal_msg_plink_GBroadcast(msg.plink.GBroadcast x, pcore.marshal.Octets os) {
            x.marshal(os);
        } public static void marshal_msg_plink_GUserOnline(msg.plink.GUserOnline x, pcore.marshal.Octets os) {
            x.marshal(os);
        } public static void marshal_msg_plink_LUserKeepAlive(msg.plink.LUserKeepAlive x, pcore.marshal.Octets os) {
            x.marshal(os);
        } public static void marshal_msg_net_GServerAnnouceServerInfo(msg.net.GServerAnnouceServerInfo x, pcore.marshal.Octets os) {
            x.marshal(os);
        } public static void marshal_msg_map_Vector2(msg.map.Vector2 x, pcore.marshal.Octets os) {
            x.marshal(os);
        } public static void marshal_msg_map_Vector3(msg.map.Vector3 x, pcore.marshal.Octets os) {
            x.marshal(os);
        } public static void marshal_list_long(java.util.List<Long> x, pcore.marshal.Octets os) {
            os.writeSize(x.size());
            for(var _e : x) {
            os.writeLong(_e);
            }
        } public static void marshal_msg_plink_GBind(msg.plink.GBind x, pcore.marshal.Octets os) {
            x.marshal(os);
        } public static void marshal_array_byte(byte[] x, pcore.marshal.Octets os) {
            os.writeSize(x.length);
            for(var _e : x) {
            os.writeByte(_e);
            }
        } public static void marshal_msg_gmap_GCfgReload(msg.gmap.GCfgReload x, pcore.marshal.Octets os) {
            x.marshal(os);
        } 
        public static msg.plink.LAnnounceOuterNetAddress unmarshal_msg_plink_LAnnounceOuterNetAddress(pcore.marshal.Octets os) {
            var x = new msg.plink.LAnnounceOuterNetAddress(); x.unmarshal(os); return x;
        } public static msg.map.SMapDebugInfo unmarshal_msg_map_SMapDebugInfo(pcore.marshal.Octets os) {
            var x = new msg.map.SMapDebugInfo(); x.unmarshal(os); return x;
        } public static java.util.Set<Long> unmarshal_set_long(pcore.marshal.Octets os) {
            var n = Math.min(os.readSize(), os.size() + 1);
            java.util.Set<Long> x = pcore.collection.Factory.newSet(n);
            for(int i = 0 ; i < n ; i++) {
            x.add(os.readLong());
            }return x;
        } public static msg.plink.LUserOnline unmarshal_msg_plink_LUserOnline(pcore.marshal.Octets os) {
            var x = new msg.plink.LUserOnline(); x.unmarshal(os); return x;
        } public static msg.plink.LForward unmarshal_msg_plink_LForward(pcore.marshal.Octets os) {
            var x = new msg.plink.LForward(); x.unmarshal(os); return x;
        } public static msg.plink.GForward unmarshal_msg_plink_GForward(pcore.marshal.Octets os) {
            var x = new msg.plink.GForward(); x.unmarshal(os); return x;
        } public static msg.gmap.MGMessage unmarshal_msg_gmap_MGMessage(pcore.marshal.Octets os) {
            var x = new msg.gmap.MGMessage(); x.unmarshal(os); return x;
        } public static msg.gmap.GMMessage unmarshal_msg_gmap_GMMessage(pcore.marshal.Octets os) {
            var x = new msg.gmap.GMMessage(); x.unmarshal(os); return x;
        } public static msg.plink.GMulticast unmarshal_msg_plink_GMulticast(pcore.marshal.Octets os) {
            var x = new msg.plink.GMulticast(); x.unmarshal(os); return x;
        } public static msg.plink.LLinkBroken unmarshal_msg_plink_LLinkBroken(pcore.marshal.Octets os) {
            var x = new msg.plink.LLinkBroken(); x.unmarshal(os); return x;
        } public static msg.plink.GKickUser unmarshal_msg_plink_GKickUser(pcore.marshal.Octets os) {
            var x = new msg.plink.GKickUser(); x.unmarshal(os); return x;
        } public static msg.plink.GUnBind unmarshal_msg_plink_GUnBind(pcore.marshal.Octets os) {
            var x = new msg.plink.GUnBind(); x.unmarshal(os); return x;
        } public static msg.map.SDebugException unmarshal_msg_map_SDebugException(pcore.marshal.Octets os) {
            var x = new msg.map.SDebugException(); x.unmarshal(os); return x;
        } public static msg.net.GClientAnnouceServerInfo unmarshal_msg_net_GClientAnnouceServerInfo(pcore.marshal.Octets os) {
            var x = new msg.net.GClientAnnouceServerInfo(); x.unmarshal(os); return x;
        } public static msg.plink.GBroadcast unmarshal_msg_plink_GBroadcast(pcore.marshal.Octets os) {
            var x = new msg.plink.GBroadcast(); x.unmarshal(os); return x;
        } public static msg.plink.GUserOnline unmarshal_msg_plink_GUserOnline(pcore.marshal.Octets os) {
            var x = new msg.plink.GUserOnline(); x.unmarshal(os); return x;
        } public static msg.plink.LUserKeepAlive unmarshal_msg_plink_LUserKeepAlive(pcore.marshal.Octets os) {
            var x = new msg.plink.LUserKeepAlive(); x.unmarshal(os); return x;
        } public static msg.net.GServerAnnouceServerInfo unmarshal_msg_net_GServerAnnouceServerInfo(pcore.marshal.Octets os) {
            var x = new msg.net.GServerAnnouceServerInfo(); x.unmarshal(os); return x;
        } public static msg.map.Vector2 unmarshal_msg_map_Vector2(pcore.marshal.Octets os) {
            var x = new msg.map.Vector2(); x.unmarshal(os); return x;
        } public static msg.map.Vector3 unmarshal_msg_map_Vector3(pcore.marshal.Octets os) {
            var x = new msg.map.Vector3(); x.unmarshal(os); return x;
        } public static java.util.List<Long> unmarshal_list_long(pcore.marshal.Octets os) {
            var n = Math.min(os.readSize(), os.size() + 1);
            java.util.List<Long> x = pcore.collection.Factory.newList(n);
            for(int i = 0 ; i < n ; i++) {
            x.add(os.readLong());
            }return x;
        } public static msg.plink.GBind unmarshal_msg_plink_GBind(pcore.marshal.Octets os) {
            var x = new msg.plink.GBind(); x.unmarshal(os); return x;
        } public static byte[] unmarshal_array_byte(pcore.marshal.Octets os) {
            var n = Math.min(os.readSize(), os.size() + 1);
            var x = new byte[n];
            for(int i = 0 ; i < n ; i++) {
            x[i] = os.readByte();
            }return x;
        } public static msg.gmap.GCfgReload unmarshal_msg_gmap_GCfgReload(pcore.marshal.Octets os) {
            var x = new msg.gmap.GCfgReload(); x.unmarshal(os); return x;
        } 
        public static void marshal_compatible_msg_plink_LAnnounceOuterNetAddress(msg.plink.LAnnounceOuterNetAddress x, pcore.marshal.Octets os) {
            x.marshalCompatible(os);
        } public static void marshal_compatible_msg_map_SMapDebugInfo(msg.map.SMapDebugInfo x, pcore.marshal.Octets os) {
            x.marshalCompatible(os);
        } public static void marshal_compatible_set_long(java.util.Set<Long> x, pcore.marshal.Octets os) {
            os.writeSize(x.size());
            for(var _e : x) {
            os.writeLong(_e);
            }
        } public static void marshal_compatible_msg_plink_LUserOnline(msg.plink.LUserOnline x, pcore.marshal.Octets os) {
            x.marshalCompatible(os);
        } public static void marshal_compatible_msg_plink_LForward(msg.plink.LForward x, pcore.marshal.Octets os) {
            x.marshalCompatible(os);
        } public static void marshal_compatible_msg_plink_GForward(msg.plink.GForward x, pcore.marshal.Octets os) {
            x.marshalCompatible(os);
        } public static void marshal_compatible_msg_gmap_MGMessage(msg.gmap.MGMessage x, pcore.marshal.Octets os) {
            x.marshalCompatible(os);
        } public static void marshal_compatible_msg_gmap_GMMessage(msg.gmap.GMMessage x, pcore.marshal.Octets os) {
            x.marshalCompatible(os);
        } public static void marshal_compatible_msg_plink_GMulticast(msg.plink.GMulticast x, pcore.marshal.Octets os) {
            x.marshalCompatible(os);
        } public static void marshal_compatible_msg_plink_LLinkBroken(msg.plink.LLinkBroken x, pcore.marshal.Octets os) {
            x.marshalCompatible(os);
        } public static void marshal_compatible_msg_plink_GKickUser(msg.plink.GKickUser x, pcore.marshal.Octets os) {
            x.marshalCompatible(os);
        } public static void marshal_compatible_msg_plink_GUnBind(msg.plink.GUnBind x, pcore.marshal.Octets os) {
            x.marshalCompatible(os);
        } public static void marshal_compatible_msg_map_SDebugException(msg.map.SDebugException x, pcore.marshal.Octets os) {
            x.marshalCompatible(os);
        } public static void marshal_compatible_msg_net_GClientAnnouceServerInfo(msg.net.GClientAnnouceServerInfo x, pcore.marshal.Octets os) {
            x.marshalCompatible(os);
        } public static void marshal_compatible_msg_plink_GBroadcast(msg.plink.GBroadcast x, pcore.marshal.Octets os) {
            x.marshalCompatible(os);
        } public static void marshal_compatible_msg_plink_GUserOnline(msg.plink.GUserOnline x, pcore.marshal.Octets os) {
            x.marshalCompatible(os);
        } public static void marshal_compatible_msg_plink_LUserKeepAlive(msg.plink.LUserKeepAlive x, pcore.marshal.Octets os) {
            x.marshalCompatible(os);
        } public static void marshal_compatible_msg_net_GServerAnnouceServerInfo(msg.net.GServerAnnouceServerInfo x, pcore.marshal.Octets os) {
            x.marshalCompatible(os);
        } public static void marshal_compatible_msg_map_Vector2(msg.map.Vector2 x, pcore.marshal.Octets os) {
            x.marshalCompatible(os);
        } public static void marshal_compatible_msg_map_Vector3(msg.map.Vector3 x, pcore.marshal.Octets os) {
            x.marshalCompatible(os);
        } public static void marshal_compatible_list_long(java.util.List<Long> x, pcore.marshal.Octets os) {
            os.writeSize(x.size());
            for(var _e : x) {
            os.writeLong(_e);
            }
        } public static void marshal_compatible_msg_plink_GBind(msg.plink.GBind x, pcore.marshal.Octets os) {
            x.marshalCompatible(os);
        } public static void marshal_compatible_array_byte(byte[] x, pcore.marshal.Octets os) {
            os.writeSize(x.length);
            for(var _e : x) {
            os.writeByte(_e);
            }
        } public static void marshal_compatible_msg_gmap_GCfgReload(msg.gmap.GCfgReload x, pcore.marshal.Octets os) {
            x.marshalCompatible(os);
        } 
        public static msg.plink.LAnnounceOuterNetAddress unmarshal_compatible_msg_plink_LAnnounceOuterNetAddress(pcore.marshal.Octets os) {
            var x = new msg.plink.LAnnounceOuterNetAddress(); x.unmarshalCompatible(os); return x;
        } public static msg.map.SMapDebugInfo unmarshal_compatible_msg_map_SMapDebugInfo(pcore.marshal.Octets os) {
            var x = new msg.map.SMapDebugInfo(); x.unmarshalCompatible(os); return x;
        } public static java.util.Set<Long> unmarshal_compatible_set_long(pcore.marshal.Octets os) {
            var n = Math.min(os.readSize(), os.size() + 1);
            java.util.Set<Long> x = pcore.collection.Factory.newSet(n);
            for(int i = 0 ; i < n ; i++) {
            x.add(os.readLong());
            }return x;
        } public static msg.plink.LUserOnline unmarshal_compatible_msg_plink_LUserOnline(pcore.marshal.Octets os) {
            var x = new msg.plink.LUserOnline(); x.unmarshalCompatible(os); return x;
        } public static msg.plink.LForward unmarshal_compatible_msg_plink_LForward(pcore.marshal.Octets os) {
            var x = new msg.plink.LForward(); x.unmarshalCompatible(os); return x;
        } public static msg.plink.GForward unmarshal_compatible_msg_plink_GForward(pcore.marshal.Octets os) {
            var x = new msg.plink.GForward(); x.unmarshalCompatible(os); return x;
        } public static msg.gmap.MGMessage unmarshal_compatible_msg_gmap_MGMessage(pcore.marshal.Octets os) {
            var x = new msg.gmap.MGMessage(); x.unmarshalCompatible(os); return x;
        } public static msg.gmap.GMMessage unmarshal_compatible_msg_gmap_GMMessage(pcore.marshal.Octets os) {
            var x = new msg.gmap.GMMessage(); x.unmarshalCompatible(os); return x;
        } public static msg.plink.GMulticast unmarshal_compatible_msg_plink_GMulticast(pcore.marshal.Octets os) {
            var x = new msg.plink.GMulticast(); x.unmarshalCompatible(os); return x;
        } public static msg.plink.LLinkBroken unmarshal_compatible_msg_plink_LLinkBroken(pcore.marshal.Octets os) {
            var x = new msg.plink.LLinkBroken(); x.unmarshalCompatible(os); return x;
        } public static msg.plink.GKickUser unmarshal_compatible_msg_plink_GKickUser(pcore.marshal.Octets os) {
            var x = new msg.plink.GKickUser(); x.unmarshalCompatible(os); return x;
        } public static msg.plink.GUnBind unmarshal_compatible_msg_plink_GUnBind(pcore.marshal.Octets os) {
            var x = new msg.plink.GUnBind(); x.unmarshalCompatible(os); return x;
        } public static msg.map.SDebugException unmarshal_compatible_msg_map_SDebugException(pcore.marshal.Octets os) {
            var x = new msg.map.SDebugException(); x.unmarshalCompatible(os); return x;
        } public static msg.net.GClientAnnouceServerInfo unmarshal_compatible_msg_net_GClientAnnouceServerInfo(pcore.marshal.Octets os) {
            var x = new msg.net.GClientAnnouceServerInfo(); x.unmarshalCompatible(os); return x;
        } public static msg.plink.GBroadcast unmarshal_compatible_msg_plink_GBroadcast(pcore.marshal.Octets os) {
            var x = new msg.plink.GBroadcast(); x.unmarshalCompatible(os); return x;
        } public static msg.plink.GUserOnline unmarshal_compatible_msg_plink_GUserOnline(pcore.marshal.Octets os) {
            var x = new msg.plink.GUserOnline(); x.unmarshalCompatible(os); return x;
        } public static msg.plink.LUserKeepAlive unmarshal_compatible_msg_plink_LUserKeepAlive(pcore.marshal.Octets os) {
            var x = new msg.plink.LUserKeepAlive(); x.unmarshalCompatible(os); return x;
        } public static msg.net.GServerAnnouceServerInfo unmarshal_compatible_msg_net_GServerAnnouceServerInfo(pcore.marshal.Octets os) {
            var x = new msg.net.GServerAnnouceServerInfo(); x.unmarshalCompatible(os); return x;
        } public static msg.map.Vector2 unmarshal_compatible_msg_map_Vector2(pcore.marshal.Octets os) {
            var x = new msg.map.Vector2(); x.unmarshalCompatible(os); return x;
        } public static msg.map.Vector3 unmarshal_compatible_msg_map_Vector3(pcore.marshal.Octets os) {
            var x = new msg.map.Vector3(); x.unmarshalCompatible(os); return x;
        } public static java.util.List<Long> unmarshal_compatible_list_long(pcore.marshal.Octets os) {
            var n = Math.min(os.readSize(), os.size() + 1);
            java.util.List<Long> x = pcore.collection.Factory.newList(n);
            for(int i = 0 ; i < n ; i++) {
            x.add(os.readLong());
            }return x;
        } public static msg.plink.GBind unmarshal_compatible_msg_plink_GBind(pcore.marshal.Octets os) {
            var x = new msg.plink.GBind(); x.unmarshalCompatible(os); return x;
        } public static byte[] unmarshal_compatible_array_byte(pcore.marshal.Octets os) {
            var n = Math.min(os.readSize(), os.size() + 1);
            var x = new byte[n];
            for(int i = 0 ; i < n ; i++) {
            x[i] = os.readByte();
            }return x;
        } public static msg.gmap.GCfgReload unmarshal_compatible_msg_gmap_GCfgReload(pcore.marshal.Octets os) {
            var x = new msg.gmap.GCfgReload(); x.unmarshalCompatible(os); return x;
        } 
}
