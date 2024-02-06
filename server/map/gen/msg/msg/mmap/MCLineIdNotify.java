package msg.mmap;

public final class MCLineIdNotify extends pcore.io.Protocol{
    public static final int TYPE_ID = 7751;

    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }

    public int lineId;

    public MCLineIdNotify() {
    }

    public MCLineIdNotify(int lineId ) {
        this.lineId = lineId;
         
    }


    @Override
    public String toString() {
        StringBuilder _sb_ = new StringBuilder();
        _sb_.append(this.getClass().getName());
         _sb_.append("[");
         _sb_.append(getTypeId());
         _sb_.append("]");
         
        _sb_.append("{");
        _sb_.append("lineId").append(':').append(lineId).append(',');

        _sb_.append("}");
        return _sb_.toString();
    }

    @Override
    public void marshal(pcore.marshal.Octets os) {
        os.writeInt(lineId);

    }

    @Override
    public void unmarshal(pcore.marshal.Octets os) {
        lineId = os.readInt();

    }
}
