package msg.serverlist;

public abstract class MailSendResultError  extends MailSendResult {


    public MailSendResultError() {
    }



    @Override
    public String toString() {
        StringBuilder _sb_ = new StringBuilder();
        _sb_.append(this.getClass().getName());
         
        _sb_.append("{");
        _sb_.append("}");
        return _sb_.toString();
    }

    @Override
    public void marshal(pcore.marshal.Octets os) {
        super.marshal(os);
    }

    @Override
    public void unmarshal(pcore.marshal.Octets os) {
        super.unmarshal(os);
    }

    @Override
    public void marshalCompatible(pcore.marshal.Octets os) {
        os.writeSize(0);
    }

    @Override
    public void unmarshalCompatible(pcore.marshal.Octets os) {
            for (int _var_num_ = os.readSize(); _var_num_-- > 0; ) {
                final int _id_ = os.readCompactUint();
                switch (_id_) {
                default: pcore.marshal.Tag.skipUnknownField(_id_, os);
            }
        }
    }
}