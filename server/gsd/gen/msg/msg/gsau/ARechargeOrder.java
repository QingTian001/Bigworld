package msg.gsau;

public final class ARechargeOrder extends pcore.io.Protocol{
    public static final int TYPE_ID = 7957;

    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }

    public long roleId;
    public int state;
    public String orderId;
    public int gear;
    public long createTime;

    public ARechargeOrder() {
         this.orderId = "";

    }

    public ARechargeOrder(long roleId , int state , String orderId , int gear , long createTime ) {
        this.roleId = roleId;
         this.state = state;
         this.orderId = orderId;
         this.gear = gear;
         this.createTime = createTime;
         
    }


    @Override
    public String toString() {
        StringBuilder _sb_ = new StringBuilder();
        _sb_.append(this.getClass().getName());
         _sb_.append("[");
         _sb_.append(getTypeId());
         _sb_.append("]");
         
        _sb_.append("{");
        _sb_.append("roleId").append(':').append(roleId).append(',');
        _sb_.append("state").append(':').append(state).append(',');
        _sb_.append("orderId").append(':').append(orderId).append(',');
        _sb_.append("gear").append(':').append(gear).append(',');
        _sb_.append("createTime").append(':').append(createTime).append(',');

        _sb_.append("}");
        return _sb_.toString();
    }

    @Override
    public void marshal(pcore.marshal.Octets os) {
        os.writeLong(roleId);
        os.writeInt(state);
        os.writeString(orderId);
        os.writeInt(gear);
        os.writeLong(createTime);

    }

    @Override
    public void unmarshal(pcore.marshal.Octets os) {
        roleId = os.readLong();
        state = os.readInt();
        orderId = os.readString();
        gear = os.readInt();
        createTime = os.readLong();

    }
}
