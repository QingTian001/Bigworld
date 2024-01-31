package msg.map;

public final class SMapDebugInfo extends pcore.io.Protocol{
    public static final int TYPE_ID = 4740;

    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }

    public String info;

    public SMapDebugInfo() {
         this.info = "";

    }

    public SMapDebugInfo(String info ) {
        this.info = info;
         
    }


    @Override
    public String toString() {
        StringBuilder _sb_ = new StringBuilder();
        _sb_.append(this.getClass().getName());
         _sb_.append("[");
         _sb_.append(getTypeId());
         _sb_.append("]");
         
        _sb_.append("{");
        _sb_.append("info").append(':').append(info).append(',');

        _sb_.append("}");
        return _sb_.toString();
    }

    @Override
    public void marshal(pcore.marshal.Octets os) {
        os.writeString(info);

    }

    @Override
    public void unmarshal(pcore.marshal.Octets os) {
        info = os.readString();

    }
}
