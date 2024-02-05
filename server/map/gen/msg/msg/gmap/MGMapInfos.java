package msg.gmap;

public final class MGMapInfos extends pcore.io.Protocol{
    public static final int TYPE_ID = 4055;

    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }

    public msg.gmap.MapServerInfo mapServerInfo;

    public MGMapInfos() {
         this.mapServerInfo = new msg.gmap.MapServerInfo();

    }

    public MGMapInfos(msg.gmap.MapServerInfo mapServerInfo ) {
        this.mapServerInfo = mapServerInfo;
         
    }


    @Override
    public String toString() {
        StringBuilder _sb_ = new StringBuilder();
        _sb_.append(this.getClass().getName());
         _sb_.append("[");
         _sb_.append(getTypeId());
         _sb_.append("]");
         
        _sb_.append("{");
        _sb_.append("mapServerInfo").append(':').append(msg.Extensions.tostring_msg_gmap_MapServerInfo(mapServerInfo)).append(',');

        _sb_.append("}");
        return _sb_.toString();
    }

    @Override
    public void marshal(pcore.marshal.Octets os) {
        msg.Extensions.marshal_msg_gmap_MapServerInfo(mapServerInfo,os);

    }

    @Override
    public void unmarshal(pcore.marshal.Octets os) {
        mapServerInfo = msg.Extensions.unmarshal_msg_gmap_MapServerInfo(os);

    }
}
