package msg.serverlist;

public final class G2SCustomServiceForward extends pcore.io.Protocol{
    public static final int TYPE_ID = 6975;

    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }

    public long id;
    public byte[] data;

    public G2SCustomServiceForward() {
         this.data = new byte[0];

    }

    public G2SCustomServiceForward(long id , byte[] data ) {
        this.id = id;
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
        _sb_.append("id").append(':').append(id).append(',');
        _sb_.append("data").append(':').append(msg.Extensions.tostring_array_byte(data)).append(',');

        _sb_.append("}");
        return _sb_.toString();
    }

    @Override
    public void marshal(pcore.marshal.Octets os) {
        os.writeLong(id);
        msg.Extensions.marshal_array_byte(data,os);

    }

    @Override
    public void unmarshal(pcore.marshal.Octets os) {
        id = os.readLong();
        data = msg.Extensions.unmarshal_array_byte(os);

    }
}
