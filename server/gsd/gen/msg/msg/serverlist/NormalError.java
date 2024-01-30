package msg.serverlist;

public final class NormalError  extends ResultError {

    public static final int TYPE_ID = 11;
    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }


    public int errCode;

    public NormalError() {
    }

    public NormalError(int errCode ) {
        this.errCode = errCode;
         
    }


    @Override
    public String toString() {
        StringBuilder _sb_ = new StringBuilder();
        _sb_.append(this.getClass().getName());
         
        _sb_.append("{");
        _sb_.append("errCode").append(':').append(errCode).append(',');

        _sb_.append("}");
        return _sb_.toString();
    }

    @Override
    public void marshal(pcore.marshal.Octets os) {
        super.marshal(os);
        os.writeInt(errCode);

    }

    @Override
    public void unmarshal(pcore.marshal.Octets os) {
        super.unmarshal(os);
        errCode = os.readInt();

    }

    @Override
    public void marshalCompatible(pcore.marshal.Octets os) {
        os.writeSize(1);
        os.writeCompactUint(pcore.marshal.Tag.INT | (14035 << pcore.marshal.Tag.TAG_SHIFT));  os.writeInt(errCode);  


    }

    @Override
    public void unmarshalCompatible(pcore.marshal.Octets os) {
            for (int _var_num_ = os.readSize(); _var_num_-- > 0; ) {
                final int _id_ = os.readCompactUint();
                switch (_id_) {
                case (pcore.marshal.Tag.INT | (14035 << pcore.marshal.Tag.TAG_SHIFT)):   errCode = os.readInt(); break;  
                 
                default: pcore.marshal.Tag.skipUnknownField(_id_, os);
            }
        }
    }
}
