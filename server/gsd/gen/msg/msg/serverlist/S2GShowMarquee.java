package msg.serverlist;

public final class S2GShowMarquee extends pcore.io.Protocol{
    public static final int TYPE_ID = 2349;

    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }

    public java.util.Map<String,String> contents;
    public java.util.List<Integer> showZones;

    public S2GShowMarquee() {
         this.contents = pcore.collection.Factory.newMap();
         this.showZones = pcore.collection.Factory.newList();

    }

    public S2GShowMarquee(java.util.Map<String,String> contents , java.util.List<Integer> showZones ) {
        this.contents = contents;
         this.showZones = showZones;
         
    }


    @Override
    public String toString() {
        StringBuilder _sb_ = new StringBuilder();
        _sb_.append(this.getClass().getName());
         _sb_.append("[");
         _sb_.append(getTypeId());
         _sb_.append("]");
         
        _sb_.append("{");
        _sb_.append("contents").append(':').append(msg.Extensions.tostring_map_string_string(contents)).append(',');
        _sb_.append("showZones").append(':').append(msg.Extensions.tostring_list_int(showZones)).append(',');

        _sb_.append("}");
        return _sb_.toString();
    }

    @Override
    public void marshal(pcore.marshal.Octets os) {
        msg.Extensions.marshal_map_string_string(contents,os);
        msg.Extensions.marshal_list_int(showZones,os);

    }

    @Override
    public void unmarshal(pcore.marshal.Octets os) {
        contents = msg.Extensions.unmarshal_map_string_string(os);
        showZones = msg.Extensions.unmarshal_list_int(os);

    }
}
