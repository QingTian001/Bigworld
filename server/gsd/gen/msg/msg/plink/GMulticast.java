package msg.plink;

public final class GMulticast extends pcore.io.Protocol{
    public static final int TYPE_ID = 3370;

    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }

    public java.util.List<Long> sids;
    public pcore.marshal.Octets data;

    public GMulticast() {
         this.sids = pcore.collection.Factory.newList();

    }

    public GMulticast(java.util.List<Long> sids , pcore.marshal.Octets data ) {
        this.sids = sids;
         this.data = data;
         
    }


    @Override
    public String toString() {
        StringBuilder _sb_ = new StringBuilder();
        _sb_.append(this.getClass().getName());
         _sb_.append("[");
         _sb_.append(getTypeId());
         _sb_.append("]");
         
        _sb_.append("{");
        _sb_.append("sids").append(':').append(msg.Extensions.tostring_list_long(sids)).append(',');
        _sb_.append("data").append(':').append(data).append(',');

        _sb_.append("}");
        return _sb_.toString();
    }

    @Override
    public void marshal(pcore.marshal.Octets os) {
        msg.Extensions.marshal_list_long(sids,os);
        os.writeOctets(data);

    }

    @Override
    public void unmarshal(pcore.marshal.Octets os) {
        sids = msg.Extensions.unmarshal_list_long(os);
        data = os.readOctets();

    }
}
