package msg.serverlist;

public final class S2GTest extends pcore.io.Protocol{
    public static final int TYPE_ID = 14607;

    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }

    public int v;

    public S2GTest() {
    }

    public S2GTest(int v ) {
        this.v = v;
         
    }


    @Override
    public String toString() {
        StringBuilder _sb_ = new StringBuilder();
        _sb_.append(this.getClass().getName());
         _sb_.append("[");
         _sb_.append(getTypeId());
         _sb_.append("]");
         
        _sb_.append("{");
        _sb_.append("v").append(':').append(v).append(',');

        _sb_.append("}");
        return _sb_.toString();
    }

    @Override
    public void marshal(pcore.marshal.Octets os) {
        os.writeInt(v);

    }

    @Override
    public void unmarshal(pcore.marshal.Octets os) {
        v = os.readInt();

    }
}
