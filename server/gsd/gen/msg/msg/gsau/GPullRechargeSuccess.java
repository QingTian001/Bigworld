package msg.gsau;

public final class GPullRechargeSuccess extends pcore.io.Protocol{
    public static final int TYPE_ID = 15887;

    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }

    public long uid;
    public long roleId;
    public String orderId;
    public String channel;
    public String accountId;

    public GPullRechargeSuccess() {
         this.orderId = "";
         this.channel = "";
         this.accountId = "";

    }

    public GPullRechargeSuccess(long uid , long roleId , String orderId , String channel , String accountId ) {
        this.uid = uid;
         this.roleId = roleId;
         this.orderId = orderId;
         this.channel = channel;
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
        _sb_.append("orderId").append(':').append(orderId).append(',');
        _sb_.append("channel").append(':').append(channel).append(',');
        _sb_.append("accountId").append(':').append(accountId).append(',');

        _sb_.append("}");
        return _sb_.toString();
    }

    @Override
    public void marshal(pcore.marshal.Octets os) {
        os.writeLong(uid);
        os.writeLong(roleId);
        os.writeString(orderId);
        os.writeString(channel);
        os.writeString(accountId);

    }

    @Override
    public void unmarshal(pcore.marshal.Octets os) {
        uid = os.readLong();
        roleId = os.readLong();
        orderId = os.readString();
        channel = os.readString();
        accountId = os.readString();

    }
}
