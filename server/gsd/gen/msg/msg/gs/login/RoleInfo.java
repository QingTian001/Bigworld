package msg.gs.login;

public final class RoleInfo  implements pcore.marshal.IMarshal {

    public static final int TYPE_ID = 0;
    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }


    public long roleId;
    public long userId;
    public String name;
    public int serverId;
    public String accountId;
    public long createTimeInMillis;
    public long lastLogicLoginTimeMills;

    public RoleInfo() {
         this.name = "";
         this.accountId = "";

    }

    public RoleInfo(long roleId , long userId , String name , int serverId , String accountId , long createTimeInMillis , long lastLogicLoginTimeMills ) {
        this.roleId = roleId;
         this.userId = userId;
         this.name = name;
         this.serverId = serverId;
         this.accountId = accountId;
         this.createTimeInMillis = createTimeInMillis;
         this.lastLogicLoginTimeMills = lastLogicLoginTimeMills;
         
    }


    @Override
    public String toString() {
        StringBuilder _sb_ = new StringBuilder();
        _sb_.append(this.getClass().getName());
         
        _sb_.append("{");
        _sb_.append("roleId").append(':').append(roleId).append(',');
        _sb_.append("userId").append(':').append(userId).append(',');
        _sb_.append("name").append(':').append(name).append(',');
        _sb_.append("serverId").append(':').append(serverId).append(',');
        _sb_.append("accountId").append(':').append(accountId).append(',');
        _sb_.append("createTimeInMillis").append(':').append(createTimeInMillis).append(',');
        _sb_.append("lastLogicLoginTimeMills").append(':').append(lastLogicLoginTimeMills).append(',');

        _sb_.append("}");
        return _sb_.toString();
    }

    @Override
    public void marshal(pcore.marshal.Octets os) {
        os.writeLong(roleId);
        os.writeLong(userId);
        os.writeString(name);
        os.writeInt(serverId);
        os.writeString(accountId);
        os.writeLong(createTimeInMillis);
        os.writeLong(lastLogicLoginTimeMills);

    }

    @Override
    public void unmarshal(pcore.marshal.Octets os) {
        roleId = os.readLong();
        userId = os.readLong();
        name = os.readString();
        serverId = os.readInt();
        accountId = os.readString();
        createTimeInMillis = os.readLong();
        lastLogicLoginTimeMills = os.readLong();

    }

    @Override
    public void marshalCompatible(pcore.marshal.Octets os) {
        os.writeSize(7);
        os.writeCompactUint(pcore.marshal.Tag.LONG | (33959 << pcore.marshal.Tag.TAG_SHIFT));  os.writeLong(roleId);  

        os.writeCompactUint(pcore.marshal.Tag.LONG | (40934 << pcore.marshal.Tag.TAG_SHIFT));  os.writeLong(userId);  

        os.writeCompactUint(pcore.marshal.Tag.STRING | (31320 << pcore.marshal.Tag.TAG_SHIFT));  os.writeString(name);  

        os.writeCompactUint(pcore.marshal.Tag.INT | (8587 << pcore.marshal.Tag.TAG_SHIFT));  os.writeInt(serverId);  

        os.writeCompactUint(pcore.marshal.Tag.STRING | (55027 << pcore.marshal.Tag.TAG_SHIFT));  os.writeString(accountId);  

        os.writeCompactUint(pcore.marshal.Tag.LONG | (60934 << pcore.marshal.Tag.TAG_SHIFT));  os.writeLong(createTimeInMillis);  

        os.writeCompactUint(pcore.marshal.Tag.LONG | (40308 << pcore.marshal.Tag.TAG_SHIFT));  os.writeLong(lastLogicLoginTimeMills);  


    }

    @Override
    public void unmarshalCompatible(pcore.marshal.Octets os) {
            for (int _var_num_ = os.readSize(); _var_num_-- > 0; ) {
                final int _id_ = os.readCompactUint();
                switch (_id_) {
                case (pcore.marshal.Tag.LONG | (33959 << pcore.marshal.Tag.TAG_SHIFT)):   roleId = os.readLong(); break;  
                 case (pcore.marshal.Tag.LONG | (40934 << pcore.marshal.Tag.TAG_SHIFT)):   userId = os.readLong(); break;  
                 case (pcore.marshal.Tag.STRING | (31320 << pcore.marshal.Tag.TAG_SHIFT)):   name = os.readString(); break;  
                 case (pcore.marshal.Tag.INT | (8587 << pcore.marshal.Tag.TAG_SHIFT)):   serverId = os.readInt(); break;  
                 case (pcore.marshal.Tag.STRING | (55027 << pcore.marshal.Tag.TAG_SHIFT)):   accountId = os.readString(); break;  
                 case (pcore.marshal.Tag.LONG | (60934 << pcore.marshal.Tag.TAG_SHIFT)):   createTimeInMillis = os.readLong(); break;  
                 case (pcore.marshal.Tag.LONG | (40308 << pcore.marshal.Tag.TAG_SHIFT)):   lastLogicLoginTimeMills = os.readLong(); break;  
                 
                default: pcore.marshal.Tag.skipUnknownField(_id_, os);
            }
        }
    }
}
