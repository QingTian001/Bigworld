package msg.serverlist;

public final class RolesMail  extends CustomServiceMail {

    public static final int TYPE_ID = 1;
    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }


    public java.util.List<Long> roleIds;

    public RolesMail() {
         this.roleIds = pcore.collection.Factory.newList();

    }

    public RolesMail(int customServiceMailId , String mailType , java.util.Map<String,msg.serverlist.SpecificLanguageMailInfo> mailInfos , long expireTimeStampInSecs , java.util.List<msg.serverlist.RewardInfo> rewardInfos , java.util.Map<String,msg.serverlist.SpecificLanguageUrlInfos> urlInfos , java.util.List<Long> roleIds ) {
        super(customServiceMailId, mailType, mailInfos, expireTimeStampInSecs, rewardInfos, urlInfos); 
        this.roleIds = roleIds;
         
    }


    @Override
    public String toString() {
        StringBuilder _sb_ = new StringBuilder();
        _sb_.append(this.getClass().getName());
         
        _sb_.append("{");
        _sb_.append("customServiceMailId").append(':').append(customServiceMailId).append(',');
        _sb_.append("mailType").append(':').append(mailType).append(',');
        _sb_.append("mailInfos").append(':').append(msg.Extensions.tostring_map_string_msg_serverlist_SpecificLanguageMailInfo(mailInfos)).append(',');
        _sb_.append("expireTimeStampInSecs").append(':').append(expireTimeStampInSecs).append(',');
        _sb_.append("rewardInfos").append(':').append(msg.Extensions.tostring_list_msg_serverlist_RewardInfo(rewardInfos)).append(',');
        _sb_.append("urlInfos").append(':').append(msg.Extensions.tostring_map_string_msg_serverlist_SpecificLanguageUrlInfos(urlInfos)).append(',');
        _sb_.append("roleIds").append(':').append(msg.Extensions.tostring_list_long(roleIds)).append(',');

        _sb_.append("}");
        return _sb_.toString();
    }

    @Override
    public void marshal(pcore.marshal.Octets os) {
        super.marshal(os);
        msg.Extensions.marshal_list_long(roleIds,os);

    }

    @Override
    public void unmarshal(pcore.marshal.Octets os) {
        super.unmarshal(os);
        roleIds = msg.Extensions.unmarshal_list_long(os);

    }

    @Override
    public void marshalCompatible(pcore.marshal.Octets os) {
        os.writeSize(7);
        os.writeCompactUint(pcore.marshal.Tag.INT | (33888 << pcore.marshal.Tag.TAG_SHIFT));  os.writeInt(customServiceMailId);  

        os.writeCompactUint(pcore.marshal.Tag.STRING | (43475 << pcore.marshal.Tag.TAG_SHIFT));  os.writeString(mailType);  

        os.writeCompactUint(pcore.marshal.Tag.MAP | (46682 << pcore.marshal.Tag.TAG_SHIFT));  { var _oldOs = os; os = new pcore.marshal.Octets(); msg.Extensions.marshal_compatible_map_string_msg_serverlist_SpecificLanguageMailInfo(mailInfos,os); _oldOs.writeOctets(os); os = _oldOs;}  

        os.writeCompactUint(pcore.marshal.Tag.LONG | (36061 << pcore.marshal.Tag.TAG_SHIFT));  os.writeLong(expireTimeStampInSecs);  

        os.writeCompactUint(pcore.marshal.Tag.LIST | (58177 << pcore.marshal.Tag.TAG_SHIFT));  { var _oldOs = os; os = new pcore.marshal.Octets(); msg.Extensions.marshal_compatible_list_msg_serverlist_RewardInfo(rewardInfos,os); _oldOs.writeOctets(os); os = _oldOs;}  

        os.writeCompactUint(pcore.marshal.Tag.MAP | (15262 << pcore.marshal.Tag.TAG_SHIFT));  { var _oldOs = os; os = new pcore.marshal.Octets(); msg.Extensions.marshal_compatible_map_string_msg_serverlist_SpecificLanguageUrlInfos(urlInfos,os); _oldOs.writeOctets(os); os = _oldOs;}  

        os.writeCompactUint(pcore.marshal.Tag.LIST | (61522 << pcore.marshal.Tag.TAG_SHIFT));  { var _oldOs = os; os = new pcore.marshal.Octets(); msg.Extensions.marshal_compatible_list_long(roleIds,os); _oldOs.writeOctets(os); os = _oldOs;}  


    }

    @Override
    public void unmarshalCompatible(pcore.marshal.Octets os) {
            for (int _var_num_ = os.readSize(); _var_num_-- > 0; ) {
                final int _id_ = os.readCompactUint();
                switch (_id_) {
                case (pcore.marshal.Tag.INT | (33888 << pcore.marshal.Tag.TAG_SHIFT)):   customServiceMailId = os.readInt(); break;  
                 case (pcore.marshal.Tag.STRING | (43475 << pcore.marshal.Tag.TAG_SHIFT)):   mailType = os.readString(); break;  
                 case (pcore.marshal.Tag.MAP | (46682 << pcore.marshal.Tag.TAG_SHIFT)):  { var _oldOs = os; os = os.readOctets(); mailInfos = msg.Extensions.unmarshal_compatible_map_string_msg_serverlist_SpecificLanguageMailInfo(os); os = _oldOs; break; }   
                 case (pcore.marshal.Tag.LONG | (36061 << pcore.marshal.Tag.TAG_SHIFT)):   expireTimeStampInSecs = os.readLong(); break;  
                 case (pcore.marshal.Tag.LIST | (58177 << pcore.marshal.Tag.TAG_SHIFT)):  { var _oldOs = os; os = os.readOctets(); rewardInfos = msg.Extensions.unmarshal_compatible_list_msg_serverlist_RewardInfo(os); os = _oldOs; break; }   
                 case (pcore.marshal.Tag.MAP | (15262 << pcore.marshal.Tag.TAG_SHIFT)):  { var _oldOs = os; os = os.readOctets(); urlInfos = msg.Extensions.unmarshal_compatible_map_string_msg_serverlist_SpecificLanguageUrlInfos(os); os = _oldOs; break; }   
                 case (pcore.marshal.Tag.LIST | (61522 << pcore.marshal.Tag.TAG_SHIFT)):  { var _oldOs = os; os = os.readOctets(); roleIds = msg.Extensions.unmarshal_compatible_list_long(os); os = _oldOs; break; }   
                 
                default: pcore.marshal.Tag.skipUnknownField(_id_, os);
            }
        }
    }
}
