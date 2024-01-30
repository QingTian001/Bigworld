package msg.serverlist;

public abstract class MailSendResultSuccess  extends MailSendResult {


    public long gsCustomMailId;

    public MailSendResultSuccess() {
    }

    public MailSendResultSuccess(long gsCustomMailId ) {
        this.gsCustomMailId = gsCustomMailId;
         
    }


    @Override
    public String toString() {
        StringBuilder _sb_ = new StringBuilder();
        _sb_.append(this.getClass().getName());
         
        _sb_.append("{");
        _sb_.append("gsCustomMailId").append(':').append(gsCustomMailId).append(',');

        _sb_.append("}");
        return _sb_.toString();
    }

    @Override
    public void marshal(pcore.marshal.Octets os) {
        super.marshal(os);
        os.writeLong(gsCustomMailId);

    }

    @Override
    public void unmarshal(pcore.marshal.Octets os) {
        super.unmarshal(os);
        gsCustomMailId = os.readLong();

    }

    @Override
    public void marshalCompatible(pcore.marshal.Octets os) {
        os.writeSize(1);
        os.writeCompactUint(pcore.marshal.Tag.LONG | (8284 << pcore.marshal.Tag.TAG_SHIFT));  os.writeLong(gsCustomMailId);  


    }

    @Override
    public void unmarshalCompatible(pcore.marshal.Octets os) {
            for (int _var_num_ = os.readSize(); _var_num_-- > 0; ) {
                final int _id_ = os.readCompactUint();
                switch (_id_) {
                case (pcore.marshal.Tag.LONG | (8284 << pcore.marshal.Tag.TAG_SHIFT)):   gsCustomMailId = os.readLong(); break;  
                 
                default: pcore.marshal.Tag.skipUnknownField(_id_, os);
            }
        }
    }
}
