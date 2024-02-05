package msg.gmap;

public final class GMMapInfosNotify extends pcore.io.Protocol{
    public static final int TYPE_ID = 16216;

    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }

    public java.util.Map<Integer,msg.gmap.MapServerInfo> mapServerInfos;

    public GMMapInfosNotify() {
         this.mapServerInfos = pcore.collection.Factory.newMap();

    }

    public GMMapInfosNotify(java.util.Map<Integer,msg.gmap.MapServerInfo> mapServerInfos ) {
        this.mapServerInfos = mapServerInfos;
         
    }


    @Override
    public String toString() {
        StringBuilder _sb_ = new StringBuilder();
        _sb_.append(this.getClass().getName());
         _sb_.append("[");
         _sb_.append(getTypeId());
         _sb_.append("]");
         
        _sb_.append("{");
        _sb_.append("mapServerInfos").append(':').append(msg.Extensions.tostring_map_int_msg_gmap_MapServerInfo(mapServerInfos)).append(',');

        _sb_.append("}");
        return _sb_.toString();
    }

    @Override
    public void marshal(pcore.marshal.Octets os) {
        msg.Extensions.marshal_map_int_msg_gmap_MapServerInfo(mapServerInfos,os);

    }

    @Override
    public void unmarshal(pcore.marshal.Octets os) {
        mapServerInfos = msg.Extensions.unmarshal_map_int_msg_gmap_MapServerInfo(os);

    }
}
