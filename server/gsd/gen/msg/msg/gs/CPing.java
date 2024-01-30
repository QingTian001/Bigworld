package msg.gs;

public final class CPing extends pcore.io.Protocol{
    public static final int TYPE_ID = 11149;

    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }

    public CPing() {
    }



    @Override
    public String toString() {
        StringBuilder _sb_ = new StringBuilder();
        _sb_.append(this.getClass().getName());
         _sb_.append("[");
         _sb_.append(getTypeId());
         _sb_.append("]");
         
        _sb_.append("{");
        _sb_.append("}");
        return _sb_.toString();
    }

    @Override
    public void marshal(pcore.marshal.Octets os) {
    }

    @Override
    public void unmarshal(pcore.marshal.Octets os) {
    }
}
