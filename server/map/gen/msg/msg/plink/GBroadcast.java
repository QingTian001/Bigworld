package msg.plink;

public final class GBroadcast extends pcore.io.Protocol{
    public static final int TYPE_ID = 9233;

    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }

    public pcore.marshal.Octets data;

    public GBroadcast() {
    }

    public GBroadcast(pcore.marshal.Octets data ) {
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
        _sb_.append("data").append(':').append(data).append(',');

        _sb_.append("}");
        return _sb_.toString();
    }

    @Override
    public void marshal(pcore.marshal.Octets os) {
        os.writeOctets(data);

    }

    @Override
    public void unmarshal(pcore.marshal.Octets os) {
        data = os.readOctets();

    }
}
