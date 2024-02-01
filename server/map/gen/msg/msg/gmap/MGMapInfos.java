package msg.gmap;

public final class MGMapInfos extends pcore.io.Protocol{
    public static final int TYPE_ID = 4055;

    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }

    public java.util.List<msg.gmap.MapInfo> mapInfos;

    public MGMapInfos() {
         this.mapInfos = pcore.collection.Factory.newList();

    }

    public MGMapInfos(java.util.List<msg.gmap.MapInfo> mapInfos ) {
        this.mapInfos = mapInfos;
         
    }


    @Override
    public String toString() {
        StringBuilder _sb_ = new StringBuilder();
        _sb_.append(this.getClass().getName());
         _sb_.append("[");
         _sb_.append(getTypeId());
         _sb_.append("]");
         
        _sb_.append("{");
        _sb_.append("mapInfos").append(':').append(msg.Extensions.tostring_list_msg_gmap_MapInfo(mapInfos)).append(',');

        _sb_.append("}");
        return _sb_.toString();
    }

    @Override
    public void marshal(pcore.marshal.Octets os) {
        msg.Extensions.marshal_list_msg_gmap_MapInfo(mapInfos,os);

    }

    @Override
    public void unmarshal(pcore.marshal.Octets os) {
        mapInfos = msg.Extensions.unmarshal_list_msg_gmap_MapInfo(os);

    }
}
