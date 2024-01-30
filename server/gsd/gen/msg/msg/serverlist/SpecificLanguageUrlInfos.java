package msg.serverlist;

public final class SpecificLanguageUrlInfos  implements pcore.marshal.IMarshal {

    public static final int TYPE_ID = 0;
    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }


    public java.util.List<msg.serverlist.UnitUrlInfo> specificLanuageUrlInfos;

    public SpecificLanguageUrlInfos() {
         this.specificLanuageUrlInfos = pcore.collection.Factory.newList();

    }

    public SpecificLanguageUrlInfos(java.util.List<msg.serverlist.UnitUrlInfo> specificLanuageUrlInfos ) {
        this.specificLanuageUrlInfos = specificLanuageUrlInfos;
         
    }


    @Override
    public String toString() {
        StringBuilder _sb_ = new StringBuilder();
        _sb_.append(this.getClass().getName());
         
        _sb_.append("{");
        _sb_.append("specificLanuageUrlInfos").append(':').append(msg.Extensions.tostring_list_msg_serverlist_UnitUrlInfo(specificLanuageUrlInfos)).append(',');

        _sb_.append("}");
        return _sb_.toString();
    }

    @Override
    public void marshal(pcore.marshal.Octets os) {
        msg.Extensions.marshal_list_msg_serverlist_UnitUrlInfo(specificLanuageUrlInfos,os);

    }

    @Override
    public void unmarshal(pcore.marshal.Octets os) {
        specificLanuageUrlInfos = msg.Extensions.unmarshal_list_msg_serverlist_UnitUrlInfo(os);

    }

    @Override
    public void marshalCompatible(pcore.marshal.Octets os) {
        os.writeSize(1);
        os.writeCompactUint(pcore.marshal.Tag.LIST | (25365 << pcore.marshal.Tag.TAG_SHIFT));  { var _oldOs = os; os = new pcore.marshal.Octets(); msg.Extensions.marshal_compatible_list_msg_serverlist_UnitUrlInfo(specificLanuageUrlInfos,os); _oldOs.writeOctets(os); os = _oldOs;}  


    }

    @Override
    public void unmarshalCompatible(pcore.marshal.Octets os) {
            for (int _var_num_ = os.readSize(); _var_num_-- > 0; ) {
                final int _id_ = os.readCompactUint();
                switch (_id_) {
                case (pcore.marshal.Tag.LIST | (25365 << pcore.marshal.Tag.TAG_SHIFT)):  { var _oldOs = os; os = os.readOctets(); specificLanuageUrlInfos = msg.Extensions.unmarshal_compatible_list_msg_serverlist_UnitUrlInfo(os); os = _oldOs; break; }   
                 
                default: pcore.marshal.Tag.skipUnknownField(_id_, os);
            }
        }
    }
}
