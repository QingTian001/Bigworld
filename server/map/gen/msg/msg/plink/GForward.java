package msg.plink;

public final class GForward extends pcore.io.Protocol{
    public static final int TYPE_ID = 6098;

    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }

    public long linkSid;
    public byte[] data;

    public GForward() {
         this.data = new byte[0];

    }

    public GForward(long linkSid , byte[] data ) {
        this.linkSid = linkSid;
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
        _sb_.append("linkSid").append(':').append(linkSid).append(',');
        _sb_.append("data").append(':').append(msg.Extensions.tostring_array_byte(data)).append(',');

        _sb_.append("}");
        return _sb_.toString();
    }

    @Override
    public void marshal(pcore.marshal.Octets os) {
        os.writeLong(linkSid);
        msg.Extensions.marshal_array_byte(data,os);

    }

    @Override
    public void unmarshal(pcore.marshal.Octets os) {
        linkSid = os.readLong();
        data = msg.Extensions.unmarshal_array_byte(os);

    }
}
