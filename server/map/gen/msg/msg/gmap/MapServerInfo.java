package msg.gmap;

public final class MapServerInfo  implements pcore.marshal.IMarshal {

    public static final int TYPE_ID = 0;
    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }


    public java.util.List<msg.gmap.MapInfo> mapInfos;
    public msg.gmap.IpPort serverIpPort;

    public MapServerInfo() {
         this.mapInfos = pcore.collection.Factory.newList();
         this.serverIpPort = new msg.gmap.IpPort();

    }

    public MapServerInfo(java.util.List<msg.gmap.MapInfo> mapInfos , msg.gmap.IpPort serverIpPort ) {
        this.mapInfos = mapInfos;
         this.serverIpPort = serverIpPort;
         
    }


    @Override
    public String toString() {
        StringBuilder _sb_ = new StringBuilder();
        _sb_.append(this.getClass().getName());
         
        _sb_.append("{");
        _sb_.append("mapInfos").append(':').append(msg.Extensions.tostring_list_msg_gmap_MapInfo(mapInfos)).append(',');
        _sb_.append("serverIpPort").append(':').append(msg.Extensions.tostring_msg_gmap_IpPort(serverIpPort)).append(',');

        _sb_.append("}");
        return _sb_.toString();
    }

    @Override
    public void marshal(pcore.marshal.Octets os) {
        msg.Extensions.marshal_list_msg_gmap_MapInfo(mapInfos,os);
        msg.Extensions.marshal_msg_gmap_IpPort(serverIpPort,os);

    }

    @Override
    public void unmarshal(pcore.marshal.Octets os) {
        mapInfos = msg.Extensions.unmarshal_list_msg_gmap_MapInfo(os);
        serverIpPort = msg.Extensions.unmarshal_msg_gmap_IpPort(os);

    }

    @Override
    public void marshalCompatible(pcore.marshal.Octets os) {
        os.writeSize(2);
        os.writeCompactUint(pcore.marshal.Tag.LIST | (35862 << pcore.marshal.Tag.TAG_SHIFT));  { var _oldOs = os; os = new pcore.marshal.Octets(); msg.Extensions.marshal_compatible_list_msg_gmap_MapInfo(mapInfos,os); _oldOs.writeOctets(os); os = _oldOs;}  

        os.writeCompactUint(pcore.marshal.Tag.BEAN | (10981 << pcore.marshal.Tag.TAG_SHIFT));  { var _oldOs = os; os = new pcore.marshal.Octets(); msg.Extensions.marshal_compatible_msg_gmap_IpPort(serverIpPort,os); _oldOs.writeOctets(os); os = _oldOs;}  


    }

    @Override
    public void unmarshalCompatible(pcore.marshal.Octets os) {
            for (int _var_num_ = os.readSize(); _var_num_-- > 0; ) {
                final int _id_ = os.readCompactUint();
                switch (_id_) {
                case (pcore.marshal.Tag.LIST | (35862 << pcore.marshal.Tag.TAG_SHIFT)):  { var _oldOs = os; os = os.readOctets(); mapInfos = msg.Extensions.unmarshal_compatible_list_msg_gmap_MapInfo(os); os = _oldOs; break; }   
                 case (pcore.marshal.Tag.BEAN | (10981 << pcore.marshal.Tag.TAG_SHIFT)):  { var _oldOs = os; os = os.readOctets(); serverIpPort = msg.Extensions.unmarshal_compatible_msg_gmap_IpPort(os); os = _oldOs; break; }   
                 
                default: pcore.marshal.Tag.skipUnknownField(_id_, os);
            }
        }
    }
}
