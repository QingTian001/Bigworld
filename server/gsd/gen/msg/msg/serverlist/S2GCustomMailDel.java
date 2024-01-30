package msg.serverlist;

public final class S2GCustomMailDel extends pcore.io.Protocol{
    public static final int TYPE_ID = 12631;

    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }

    public long gsCustomMailId;

    public S2GCustomMailDel() {
    }

    public S2GCustomMailDel(long gsCustomMailId ) {
        this.gsCustomMailId = gsCustomMailId;
         
    }


    @Override
    public String toString() {
        StringBuilder _sb_ = new StringBuilder();
        _sb_.append(this.getClass().getName());
         _sb_.append("[");
         _sb_.append(getTypeId());
         _sb_.append("]");
         
        _sb_.append("{");
        _sb_.append("gsCustomMailId").append(':').append(gsCustomMailId).append(',');

        _sb_.append("}");
        return _sb_.toString();
    }

    @Override
    public void marshal(pcore.marshal.Octets os) {
        os.writeLong(gsCustomMailId);

    }

    @Override
    public void unmarshal(pcore.marshal.Octets os) {
        gsCustomMailId = os.readLong();

    }
}
