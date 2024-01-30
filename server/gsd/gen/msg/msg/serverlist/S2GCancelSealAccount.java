package msg.serverlist;

public final class S2GCancelSealAccount extends pcore.io.Protocol{
    public static final int TYPE_ID = 11528;

    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }

    public java.util.List<Long> uids;

    public S2GCancelSealAccount() {
         this.uids = pcore.collection.Factory.newList();

    }

    public S2GCancelSealAccount(java.util.List<Long> uids ) {
        this.uids = uids;
         
    }


    @Override
    public String toString() {
        StringBuilder _sb_ = new StringBuilder();
        _sb_.append(this.getClass().getName());
         _sb_.append("[");
         _sb_.append(getTypeId());
         _sb_.append("]");
         
        _sb_.append("{");
        _sb_.append("uids").append(':').append(msg.Extensions.tostring_list_long(uids)).append(',');

        _sb_.append("}");
        return _sb_.toString();
    }

    @Override
    public void marshal(pcore.marshal.Octets os) {
        msg.Extensions.marshal_list_long(uids,os);

    }

    @Override
    public void unmarshal(pcore.marshal.Octets os) {
        uids = msg.Extensions.unmarshal_list_long(os);

    }
}
