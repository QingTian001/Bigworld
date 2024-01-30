package msg.gsau;

public final class GUserUpgradeNotify extends pcore.io.Protocol{
    public static final int TYPE_ID = 14214;

    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }

    public long uid;
    public String accountId;
    public long roleId;
    public int roleLevel;

    public GUserUpgradeNotify() {
         this.accountId = "";

    }

    public GUserUpgradeNotify(long uid , String accountId , long roleId , int roleLevel ) {
        this.uid = uid;
         this.accountId = accountId;
         this.roleId = roleId;
         this.roleLevel = roleLevel;
         
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
        _sb_.append("roleLevel").append(':').append(roleLevel).append(',');

        _sb_.append("}");
        return _sb_.toString();
    }

    @Override
    public void marshal(pcore.marshal.Octets os) {
        os.writeLong(uid);
        os.writeString(accountId);
        os.writeLong(roleId);
        os.writeInt(roleLevel);

    }

    @Override
    public void unmarshal(pcore.marshal.Octets os) {
        uid = os.readLong();
        accountId = os.readString();
        roleId = os.readLong();
        roleLevel = os.readInt();

    }
}
