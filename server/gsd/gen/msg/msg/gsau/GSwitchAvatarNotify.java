package msg.gsau;

public final class GSwitchAvatarNotify extends pcore.io.Protocol{
    public static final int TYPE_ID = 11081;

    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }

    public long uid;
    public String accountId;
    public long roleId;
    public int avatarId;

    public GSwitchAvatarNotify() {
         this.accountId = "";

    }

    public GSwitchAvatarNotify(long uid , String accountId , long roleId , int avatarId ) {
        this.uid = uid;
         this.accountId = accountId;
         this.roleId = roleId;
         this.avatarId = avatarId;
         
    }


    @Override
    public String toString() {
        StringBuilder _sb_ = new StringBuilder();
        _sb_.append(this.getClass().getName());
         _sb_.append("[");
         _sb_.append(getTypeId());
         _sb_.append("]");
         
        _sb_.append("{");
        _sb_.append("uid").append(':').append(uid).append(',');
        _sb_.append("accountId").append(':').append(accountId).append(',');
        _sb_.append("roleId").append(':').append(roleId).append(',');
        _sb_.append("avatarId").append(':').append(avatarId).append(',');

        _sb_.append("}");
        return _sb_.toString();
    }

    @Override
    public void marshal(pcore.marshal.Octets os) {
        os.writeLong(uid);
        os.writeString(accountId);
        os.writeLong(roleId);
        os.writeInt(avatarId);

    }

    @Override
    public void unmarshal(pcore.marshal.Octets os) {
        uid = os.readLong();
        accountId = os.readString();
        roleId = os.readLong();
        avatarId = os.readInt();

    }
}
