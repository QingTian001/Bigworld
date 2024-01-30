package msg.serverlist;

public final class QueryRoleMailResultSuccess  extends Success {

    public static final int TYPE_ID = 2;
    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }


    public java.util.List<msg.serverlist.MailEntryInGM> mails;
    public int total;

    public QueryRoleMailResultSuccess() {
         this.mails = pcore.collection.Factory.newList();

    }

    public QueryRoleMailResultSuccess(java.util.List<msg.serverlist.MailEntryInGM> mails , int total ) {
        this.mails = mails;
         this.total = total;
         
    }


    @Override
    public String toString() {
        StringBuilder _sb_ = new StringBuilder();
        _sb_.append(this.getClass().getName());
         
        _sb_.append("{");
        _sb_.append("mails").append(':').append(msg.Extensions.tostring_list_msg_serverlist_MailEntryInGM(mails)).append(',');
        _sb_.append("total").append(':').append(total).append(',');

        _sb_.append("}");
        return _sb_.toString();
    }

    @Override
    public void marshal(pcore.marshal.Octets os) {
        super.marshal(os);
        msg.Extensions.marshal_list_msg_serverlist_MailEntryInGM(mails,os);
        os.writeInt(total);

    }

    @Override
    public void unmarshal(pcore.marshal.Octets os) {
        super.unmarshal(os);
        mails = msg.Extensions.unmarshal_list_msg_serverlist_MailEntryInGM(os);
        total = os.readInt();

    }

    @Override
    public void marshalCompatible(pcore.marshal.Octets os) {
        os.writeSize(2);
        os.writeCompactUint(pcore.marshal.Tag.LIST | (43887 << pcore.marshal.Tag.TAG_SHIFT));  { var _oldOs = os; os = new pcore.marshal.Octets(); msg.Extensions.marshal_compatible_list_msg_serverlist_MailEntryInGM(mails,os); _oldOs.writeOctets(os); os = _oldOs;}  

        os.writeCompactUint(pcore.marshal.Tag.INT | (54446 << pcore.marshal.Tag.TAG_SHIFT));  os.writeInt(total);  


    }

    @Override
    public void unmarshalCompatible(pcore.marshal.Octets os) {
            for (int _var_num_ = os.readSize(); _var_num_-- > 0; ) {
                final int _id_ = os.readCompactUint();
                switch (_id_) {
                case (pcore.marshal.Tag.LIST | (43887 << pcore.marshal.Tag.TAG_SHIFT)):  { var _oldOs = os; os = os.readOctets(); mails = msg.Extensions.unmarshal_compatible_list_msg_serverlist_MailEntryInGM(os); os = _oldOs; break; }   
                 case (pcore.marshal.Tag.INT | (54446 << pcore.marshal.Tag.TAG_SHIFT)):   total = os.readInt(); break;  
                 
                default: pcore.marshal.Tag.skipUnknownField(_id_, os);
            }
        }
    }
}
