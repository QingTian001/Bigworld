package msg.serverlist;

public final class MailSendRolesResultSuccess  extends MailSendResultSuccess {

    public static final int TYPE_ID = 1;
    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }


    public java.util.List<Long> roles;

    public MailSendRolesResultSuccess() {
         this.roles = pcore.collection.Factory.newList();

    }

    public MailSendRolesResultSuccess(long gsCustomMailId , java.util.List<Long> roles ) {
        super(gsCustomMailId); 
        this.roles = roles;
         
    }


    @Override
    public String toString() {
        StringBuilder _sb_ = new StringBuilder();
        _sb_.append(this.getClass().getName());
         
        _sb_.append("{");
        _sb_.append("gsCustomMailId").append(':').append(gsCustomMailId).append(',');
        _sb_.append("roles").append(':').append(msg.Extensions.tostring_list_long(roles)).append(',');

        _sb_.append("}");
        return _sb_.toString();
    }

    @Override
    public void marshal(pcore.marshal.Octets os) {
        super.marshal(os);
        msg.Extensions.marshal_list_long(roles,os);

    }

    @Override
    public void unmarshal(pcore.marshal.Octets os) {
        super.unmarshal(os);
        roles = msg.Extensions.unmarshal_list_long(os);

    }

    @Override
    public void marshalCompatible(pcore.marshal.Octets os) {
        os.writeSize(2);
        os.writeCompactUint(pcore.marshal.Tag.LONG | (8284 << pcore.marshal.Tag.TAG_SHIFT));  os.writeLong(gsCustomMailId);  

        os.writeCompactUint(pcore.marshal.Tag.LIST | (34883 << pcore.marshal.Tag.TAG_SHIFT));  { var _oldOs = os; os = new pcore.marshal.Octets(); msg.Extensions.marshal_compatible_list_long(roles,os); _oldOs.writeOctets(os); os = _oldOs;}  


    }

    @Override
    public void unmarshalCompatible(pcore.marshal.Octets os) {
            for (int _var_num_ = os.readSize(); _var_num_-- > 0; ) {
                final int _id_ = os.readCompactUint();
                switch (_id_) {
                case (pcore.marshal.Tag.LONG | (8284 << pcore.marshal.Tag.TAG_SHIFT)):   gsCustomMailId = os.readLong(); break;  
                 case (pcore.marshal.Tag.LIST | (34883 << pcore.marshal.Tag.TAG_SHIFT)):  { var _oldOs = os; os = os.readOctets(); roles = msg.Extensions.unmarshal_compatible_list_long(os); os = _oldOs; break; }   
                 
                default: pcore.marshal.Tag.skipUnknownField(_id_, os);
            }
        }
    }
}
