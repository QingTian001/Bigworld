package msg.serverlist;

public final class S2GSealAcount extends pcore.io.Protocol{
    public static final int TYPE_ID = 12444;

    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }

    public java.util.List<Long> uids;
    public int reasonType;
    public long unSealAt;

    public S2GSealAcount() {
         this.uids = pcore.collection.Factory.newList();

    }

    public S2GSealAcount(java.util.List<Long> uids , int reasonType , long unSealAt ) {
        this.uids = uids;
         this.reasonType = reasonType;
         this.unSealAt = unSealAt;
         
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
        _sb_.append("reasonType").append(':').append(reasonType).append(',');
        _sb_.append("unSealAt").append(':').append(unSealAt).append(',');

        _sb_.append("}");
        return _sb_.toString();
    }

    @Override
    public void marshal(pcore.marshal.Octets os) {
        msg.Extensions.marshal_list_long(uids,os);
        os.writeInt(reasonType);
        os.writeLong(unSealAt);

    }

    @Override
    public void unmarshal(pcore.marshal.Octets os) {
        uids = msg.Extensions.unmarshal_list_long(os);
        reasonType = os.readInt();
        unSealAt = os.readLong();

    }
}
