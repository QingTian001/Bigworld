package msg.plink;

public final class GUnBind extends pcore.io.Protocol{
    public static final int TYPE_ID = 9591;

    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }

    public int pvid;
    public java.util.Set<Long> lindSids;

    public GUnBind() {
         this.lindSids = pcore.collection.Factory.newSet();

    }

    public GUnBind(int pvid , java.util.Set<Long> lindSids ) {
        this.pvid = pvid;
         this.lindSids = lindSids;
         
    }


    @Override
    public String toString() {
        StringBuilder _sb_ = new StringBuilder();
        _sb_.append(this.getClass().getName());
         _sb_.append("[");
         _sb_.append(getTypeId());
         _sb_.append("]");
         
        _sb_.append("{");
        _sb_.append("pvid").append(':').append(pvid).append(',');
        _sb_.append("lindSids").append(':').append(msg.Extensions.tostring_set_long(lindSids)).append(',');

        _sb_.append("}");
        return _sb_.toString();
    }

    @Override
    public void marshal(pcore.marshal.Octets os) {
        os.writeInt(pvid);
        msg.Extensions.marshal_set_long(lindSids,os);

    }

    @Override
    public void unmarshal(pcore.marshal.Octets os) {
        pvid = os.readInt();
        lindSids = msg.Extensions.unmarshal_set_long(os);

    }
}
