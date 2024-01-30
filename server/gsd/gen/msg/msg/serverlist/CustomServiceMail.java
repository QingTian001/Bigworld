package msg.serverlist;

public abstract class CustomServiceMail  implements pcore.marshal.IMarshal {


    public int customServiceMailId;
    public String mailType;
    public java.util.Map<String,msg.serverlist.SpecificLanguageMailInfo> mailInfos;
    public long expireTimeStampInSecs;
    public java.util.List<msg.serverlist.RewardInfo> rewardInfos;
    public java.util.Map<String,msg.serverlist.SpecificLanguageUrlInfos> urlInfos;

    public CustomServiceMail() {
         this.mailType = "";
         this.mailInfos = pcore.collection.Factory.newMap();
         this.rewardInfos = pcore.collection.Factory.newList();
         this.urlInfos = pcore.collection.Factory.newMap();

    }

    public CustomServiceMail(int customServiceMailId , String mailType , java.util.Map<String,msg.serverlist.SpecificLanguageMailInfo> mailInfos , long expireTimeStampInSecs , java.util.List<msg.serverlist.RewardInfo> rewardInfos , java.util.Map<String,msg.serverlist.SpecificLanguageUrlInfos> urlInfos ) {
        this.customServiceMailId = customServiceMailId;
         this.mailType = mailType;
         this.mailInfos = mailInfos;
         this.expireTimeStampInSecs = expireTimeStampInSecs;
         this.rewardInfos = rewardInfos;
         this.urlInfos = urlInfos;
         
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

        _sb_.append("}");
        return _sb_.toString();
    }

    @Override
    public void marshal(pcore.marshal.Octets os) {
        os.writeInt(customServiceMailId);
        os.writeString(mailType);
        msg.Extensions.marshal_map_string_msg_serverlist_SpecificLanguageMailInfo(mailInfos,os);
        os.writeLong(expireTimeStampInSecs);
        msg.Extensions.marshal_list_msg_serverlist_RewardInfo(rewardInfos,os);
        msg.Extensions.marshal_map_string_msg_serverlist_SpecificLanguageUrlInfos(urlInfos,os);

    }

    @Override
    public void unmarshal(pcore.marshal.Octets os) {
        customServiceMailId = os.readInt();
        mailType = os.readString();
        mailInfos = msg.Extensions.unmarshal_map_string_msg_serverlist_SpecificLanguageMailInfo(os);
        expireTimeStampInSecs = os.readLong();
        rewardInfos = msg.Extensions.unmarshal_list_msg_serverlist_RewardInfo(os);
        urlInfos = msg.Extensions.unmarshal_map_string_msg_serverlist_SpecificLanguageUrlInfos(os);

    }

    @Override
    public void marshalCompatible(pcore.marshal.Octets os) {
        os.writeSize(6);
        os.writeCompactUint(pcore.marshal.Tag.INT | (33888 << pcore.marshal.Tag.TAG_SHIFT));  os.writeInt(customServiceMailId);  

        os.writeCompactUint(pcore.marshal.Tag.STRING | (43475 << pcore.marshal.Tag.TAG_SHIFT));  os.writeString(mailType);  

        os.writeCompactUint(pcore.marshal.Tag.MAP | (46682 << pcore.marshal.Tag.TAG_SHIFT));  { var _oldOs = os; os = new pcore.marshal.Octets(); msg.Extensions.marshal_compatible_map_string_msg_serverlist_SpecificLanguageMailInfo(mailInfos,os); _oldOs.writeOctets(os); os = _oldOs;}  

        os.writeCompactUint(pcore.marshal.Tag.LONG | (36061 << pcore.marshal.Tag.TAG_SHIFT));  os.writeLong(expireTimeStampInSecs);  

        os.writeCompactUint(pcore.marshal.Tag.LIST | (58177 << pcore.marshal.Tag.TAG_SHIFT));  { var _oldOs = os; os = new pcore.marshal.Octets(); msg.Extensions.marshal_compatible_list_msg_serverlist_RewardInfo(rewardInfos,os); _oldOs.writeOctets(os); os = _oldOs;}  

        os.writeCompactUint(pcore.marshal.Tag.MAP | (15262 << pcore.marshal.Tag.TAG_SHIFT));  { var _oldOs = os; os = new pcore.marshal.Octets(); msg.Extensions.marshal_compatible_map_string_msg_serverlist_SpecificLanguageUrlInfos(urlInfos,os); _oldOs.writeOctets(os); os = _oldOs;}  


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
                 
                default: pcore.marshal.Tag.skipUnknownField(_id_, os);
            }
        }
    }
}
