package msg.serverlist;

public final class MailEntryInGM  implements pcore.marshal.IMarshal {

    public static final int TYPE_ID = 0;
    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }


    public long roleId;
    public long mailId;
    public String mailType;
    public String title;
    public int isRead;
    public int isClaimed;
    public java.util.Map<Integer,Long> rewards;
    public long createTime;
    public long expireTime;

    public MailEntryInGM() {
         this.mailType = "";
         this.title = "";
         this.rewards = pcore.collection.Factory.newMap();

    }

    public MailEntryInGM(long roleId , long mailId , String mailType , String title , int isRead , int isClaimed , java.util.Map<Integer,Long> rewards , long createTime , long expireTime ) {
        this.roleId = roleId;
         this.mailId = mailId;
         this.mailType = mailType;
         this.title = title;
         this.isRead = isRead;
         this.isClaimed = isClaimed;
         this.rewards = rewards;
         this.createTime = createTime;
         this.expireTime = expireTime;
         
    }


    @Override
    public String toString() {
        StringBuilder _sb_ = new StringBuilder();
        _sb_.append(this.getClass().getName());
         
        _sb_.append("{");
        _sb_.append("roleId").append(':').append(roleId).append(',');
        _sb_.append("mailId").append(':').append(mailId).append(',');
        _sb_.append("mailType").append(':').append(mailType).append(',');
        _sb_.append("title").append(':').append(title).append(',');
        _sb_.append("isRead").append(':').append(isRead).append(',');
        _sb_.append("isClaimed").append(':').append(isClaimed).append(',');
        _sb_.append("rewards").append(':').append(msg.Extensions.tostring_map_int_long(rewards)).append(',');
        _sb_.append("createTime").append(':').append(createTime).append(',');
        _sb_.append("expireTime").append(':').append(expireTime).append(',');

        _sb_.append("}");
        return _sb_.toString();
    }

    @Override
    public void marshal(pcore.marshal.Octets os) {
        os.writeLong(roleId);
        os.writeLong(mailId);
        os.writeString(mailType);
        os.writeString(title);
        os.writeInt(isRead);
        os.writeInt(isClaimed);
        msg.Extensions.marshal_map_int_long(rewards,os);
        os.writeLong(createTime);
        os.writeLong(expireTime);

    }

    @Override
    public void unmarshal(pcore.marshal.Octets os) {
        roleId = os.readLong();
        mailId = os.readLong();
        mailType = os.readString();
        title = os.readString();
        isRead = os.readInt();
        isClaimed = os.readInt();
        rewards = msg.Extensions.unmarshal_map_int_long(os);
        createTime = os.readLong();
        expireTime = os.readLong();

    }

    @Override
    public void marshalCompatible(pcore.marshal.Octets os) {
        os.writeSize(9);
        os.writeCompactUint(pcore.marshal.Tag.LONG | (33959 << pcore.marshal.Tag.TAG_SHIFT));  os.writeLong(roleId);  

        os.writeCompactUint(pcore.marshal.Tag.LONG | (16983 << pcore.marshal.Tag.TAG_SHIFT));  os.writeLong(mailId);  

        os.writeCompactUint(pcore.marshal.Tag.STRING | (43475 << pcore.marshal.Tag.TAG_SHIFT));  os.writeString(mailType);  

        os.writeCompactUint(pcore.marshal.Tag.STRING | (7108 << pcore.marshal.Tag.TAG_SHIFT));  os.writeString(title);  

        os.writeCompactUint(pcore.marshal.Tag.INT | (33737 << pcore.marshal.Tag.TAG_SHIFT));  os.writeInt(isRead);  

        os.writeCompactUint(pcore.marshal.Tag.INT | (52484 << pcore.marshal.Tag.TAG_SHIFT));  os.writeInt(isClaimed);  

        os.writeCompactUint(pcore.marshal.Tag.MAP | (21898 << pcore.marshal.Tag.TAG_SHIFT));  { var _oldOs = os; os = new pcore.marshal.Octets(); msg.Extensions.marshal_compatible_map_int_long(rewards,os); _oldOs.writeOctets(os); os = _oldOs;}  

        os.writeCompactUint(pcore.marshal.Tag.LONG | (14413 << pcore.marshal.Tag.TAG_SHIFT));  os.writeLong(createTime);  

        os.writeCompactUint(pcore.marshal.Tag.LONG | (45492 << pcore.marshal.Tag.TAG_SHIFT));  os.writeLong(expireTime);  


    }

    @Override
    public void unmarshalCompatible(pcore.marshal.Octets os) {
            for (int _var_num_ = os.readSize(); _var_num_-- > 0; ) {
                final int _id_ = os.readCompactUint();
                switch (_id_) {
                case (pcore.marshal.Tag.LONG | (33959 << pcore.marshal.Tag.TAG_SHIFT)):   roleId = os.readLong(); break;  
                 case (pcore.marshal.Tag.LONG | (16983 << pcore.marshal.Tag.TAG_SHIFT)):   mailId = os.readLong(); break;  
                 case (pcore.marshal.Tag.STRING | (43475 << pcore.marshal.Tag.TAG_SHIFT)):   mailType = os.readString(); break;  
                 case (pcore.marshal.Tag.STRING | (7108 << pcore.marshal.Tag.TAG_SHIFT)):   title = os.readString(); break;  
                 case (pcore.marshal.Tag.INT | (33737 << pcore.marshal.Tag.TAG_SHIFT)):   isRead = os.readInt(); break;  
                 case (pcore.marshal.Tag.INT | (52484 << pcore.marshal.Tag.TAG_SHIFT)):   isClaimed = os.readInt(); break;  
                 case (pcore.marshal.Tag.MAP | (21898 << pcore.marshal.Tag.TAG_SHIFT)):  { var _oldOs = os; os = os.readOctets(); rewards = msg.Extensions.unmarshal_compatible_map_int_long(os); os = _oldOs; break; }   
                 case (pcore.marshal.Tag.LONG | (14413 << pcore.marshal.Tag.TAG_SHIFT)):   createTime = os.readLong(); break;  
                 case (pcore.marshal.Tag.LONG | (45492 << pcore.marshal.Tag.TAG_SHIFT)):   expireTime = os.readLong(); break;  
                 
                default: pcore.marshal.Tag.skipUnknownField(_id_, os);
            }
        }
    }
}
