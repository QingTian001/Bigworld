package msg.serverlist;

public final class MailSendResultRoleNotExists  extends MailSendResultError {

    public static final int TYPE_ID = 11;
    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }


    public java.util.List<Long> notExistRoleIds;

    public MailSendResultRoleNotExists() {
         this.notExistRoleIds = pcore.collection.Factory.newList();

    }

    public MailSendResultRoleNotExists(java.util.List<Long> notExistRoleIds ) {
        this.notExistRoleIds = notExistRoleIds;
         
    }


    @Override
    public String toString() {
        StringBuilder _sb_ = new StringBuilder();
        _sb_.append(this.getClass().getName());
         
        _sb_.append("{");
        _sb_.append("notExistRoleIds").append(':').append(msg.Extensions.tostring_list_long(notExistRoleIds)).append(',');

        _sb_.append("}");
        return _sb_.toString();
    }

    @Override
    public void marshal(pcore.marshal.Octets os) {
        super.marshal(os);
        msg.Extensions.marshal_list_long(notExistRoleIds,os);

    }

    @Override
    public void unmarshal(pcore.marshal.Octets os) {
        super.unmarshal(os);
        notExistRoleIds = msg.Extensions.unmarshal_list_long(os);

    }

    @Override
    public void marshalCompatible(pcore.marshal.Octets os) {
        os.writeSize(1);
        os.writeCompactUint(pcore.marshal.Tag.LIST | (50377 << pcore.marshal.Tag.TAG_SHIFT));  { var _oldOs = os; os = new pcore.marshal.Octets(); msg.Extensions.marshal_compatible_list_long(notExistRoleIds,os); _oldOs.writeOctets(os); os = _oldOs;}  


    }

    @Override
    public void unmarshalCompatible(pcore.marshal.Octets os) {
            for (int _var_num_ = os.readSize(); _var_num_-- > 0; ) {
                final int _id_ = os.readCompactUint();
                switch (_id_) {
                case (pcore.marshal.Tag.LIST | (50377 << pcore.marshal.Tag.TAG_SHIFT)):  { var _oldOs = os; os = os.readOctets(); notExistRoleIds = msg.Extensions.unmarshal_compatible_list_long(os); os = _oldOs; break; }   
                 
                default: pcore.marshal.Tag.skipUnknownField(_id_, os);
            }
        }
    }
}
