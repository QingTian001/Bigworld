package msg.gsau;

public final class GRechargeOrder extends pcore.io.Protocol{
    public static final int TYPE_ID = 7621;

    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }

    public long uid;
    public long roleId;
    public int serverId;
    public String channel;
    public int gear;
    public String accountId;

    public GRechargeOrder() {
         this.channel = "";
         this.accountId = "";

    }

    public GRechargeOrder(long uid , long roleId , int serverId , String channel , int gear , String accountId ) {
        this.uid = uid;
         this.roleId = roleId;
         this.serverId = serverId;
         this.channel = channel;
         this.gear = gear;
         this.accountId = accountId;
         
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
        _sb_.append("roleId").append(':').append(roleId).append(',');
        _sb_.append("serverId").append(':').append(serverId).append(',');
        _sb_.append("channel").append(':').append(channel).append(',');
        _sb_.append("gear").append(':').append(gear).append(',');
        _sb_.append("accountId").append(':').append(accountId).append(',');

        _sb_.append("}");
        return _sb_.toString();
    }

    @Override
    public void marshal(pcore.marshal.Octets os) {
        os.writeLong(uid);
        os.writeLong(roleId);
        os.writeInt(serverId);
        os.writeString(channel);
        os.writeInt(gear);
        os.writeString(accountId);

    }

    @Override
    public void unmarshal(pcore.marshal.Octets os) {
        uid = os.readLong();
        roleId = os.readLong();
        serverId = os.readInt();
        channel = os.readString();
        gear = os.readInt();
        accountId = os.readString();

    }
}
