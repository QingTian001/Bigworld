package msg.gsau;

public final class GUserLogin extends pcore.io.Protocol{
    public static final int TYPE_ID = 9365;

    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }

    public long uid;
    public String accountId;
    public long roleId;
    public String roleName;
    public int roleLevel;
    public int avatarId;
    public int serverId;
    public long loginTime;

    public GUserLogin() {
         this.accountId = "";
         this.roleName = "";

    }

    public GUserLogin(long uid , String accountId , long roleId , String roleName , int roleLevel , int avatarId , int serverId , long loginTime ) {
        this.uid = uid;
         this.accountId = accountId;
         this.roleId = roleId;
         this.roleName = roleName;
         this.roleLevel = roleLevel;
         this.avatarId = avatarId;
         this.serverId = serverId;
         this.loginTime = loginTime;
         
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
        _sb_.append("roleName").append(':').append(roleName).append(',');
        _sb_.append("roleLevel").append(':').append(roleLevel).append(',');
        _sb_.append("avatarId").append(':').append(avatarId).append(',');
        _sb_.append("serverId").append(':').append(serverId).append(',');
        _sb_.append("loginTime").append(':').append(loginTime).append(',');

        _sb_.append("}");
        return _sb_.toString();
    }

    @Override
    public void marshal(pcore.marshal.Octets os) {
        os.writeLong(uid);
        os.writeString(accountId);
        os.writeLong(roleId);
        os.writeString(roleName);
        os.writeInt(roleLevel);
        os.writeInt(avatarId);
        os.writeInt(serverId);
        os.writeLong(loginTime);

    }

    @Override
    public void unmarshal(pcore.marshal.Octets os) {
        uid = os.readLong();
        accountId = os.readString();
        roleId = os.readLong();
        roleName = os.readString();
        roleLevel = os.readInt();
        avatarId = os.readInt();
        serverId = os.readInt();
        loginTime = os.readLong();

    }
}
