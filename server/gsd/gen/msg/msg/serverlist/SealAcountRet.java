package msg.serverlist;

public final class SealAcountRet  implements pcore.marshal.IMarshal {

    public static final int TYPE_ID = 0;
    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }


    public long roleId;
    public msg.serverlist.Result result;

    public SealAcountRet() {
    }

    public SealAcountRet(long roleId , msg.serverlist.Result result ) {
        this.roleId = roleId;
         this.result = result;
         
    }


    @Override
    public String toString() {
        StringBuilder _sb_ = new StringBuilder();
        _sb_.append(this.getClass().getName());
         
        _sb_.append("{");
        _sb_.append("roleId").append(':').append(roleId).append(',');
        _sb_.append("result").append(':').append(msg.Extensions.tostring_msg_serverlist_Result(result)).append(',');

        _sb_.append("}");
        return _sb_.toString();
    }

    @Override
    public void marshal(pcore.marshal.Octets os) {
        os.writeLong(roleId);
        msg.Extensions.marshal_msg_serverlist_Result(result,os);

    }

    @Override
    public void unmarshal(pcore.marshal.Octets os) {
        roleId = os.readLong();
        result = msg.Extensions.unmarshal_msg_serverlist_Result(os);

    }

    @Override
    public void marshalCompatible(pcore.marshal.Octets os) {
        os.writeSize(2);
        os.writeCompactUint(pcore.marshal.Tag.LONG | (33959 << pcore.marshal.Tag.TAG_SHIFT));  os.writeLong(roleId);  

        os.writeCompactUint(pcore.marshal.Tag.BEAN | (49 << pcore.marshal.Tag.TAG_SHIFT));  { var _oldOs = os; os = new pcore.marshal.Octets(); msg.Extensions.marshal_compatible_msg_serverlist_Result(result,os); _oldOs.writeOctets(os); os = _oldOs;}  


    }

    @Override
    public void unmarshalCompatible(pcore.marshal.Octets os) {
            for (int _var_num_ = os.readSize(); _var_num_-- > 0; ) {
                final int _id_ = os.readCompactUint();
                switch (_id_) {
                case (pcore.marshal.Tag.LONG | (33959 << pcore.marshal.Tag.TAG_SHIFT)):   roleId = os.readLong(); break;  
                 case (pcore.marshal.Tag.BEAN | (49 << pcore.marshal.Tag.TAG_SHIFT)):  { var _oldOs = os; os = os.readOctets(); result = msg.Extensions.unmarshal_compatible_msg_serverlist_Result(os); os = _oldOs; break; }   
                 
                default: pcore.marshal.Tag.skipUnknownField(_id_, os);
            }
        }
    }
}
