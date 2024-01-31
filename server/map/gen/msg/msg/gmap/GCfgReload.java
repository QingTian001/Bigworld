package msg.gmap;

public final class GCfgReload extends pcore.io.Protocol{
    public static final int TYPE_ID = 8536;

    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }

    public int version;

    public GCfgReload() {
    }

    public GCfgReload(int version ) {
        this.version = version;
         
    }


    @Override
    public String toString() {
        StringBuilder _sb_ = new StringBuilder();
        _sb_.append(this.getClass().getName());
         _sb_.append("[");
         _sb_.append(getTypeId());
         _sb_.append("]");
         
        _sb_.append("{");
        _sb_.append("version").append(':').append(version).append(',');

        _sb_.append("}");
        return _sb_.toString();
    }

    @Override
    public void marshal(pcore.marshal.Octets os) {
        os.writeInt(version);

    }

    @Override
    public void unmarshal(pcore.marshal.Octets os) {
        version = os.readInt();

    }
}
