package msg.gsau;

public final class APullRecharge extends pcore.io.Protocol{
    public static final int TYPE_ID = 1567;

    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }

    public java.util.List<msg.gsau.RechargeInfo> recharges;

    public APullRecharge() {
         this.recharges = pcore.collection.Factory.newList();

    }

    public APullRecharge(java.util.List<msg.gsau.RechargeInfo> recharges ) {
        this.recharges = recharges;
         
    }


    @Override
    public String toString() {
        StringBuilder _sb_ = new StringBuilder();
        _sb_.append(this.getClass().getName());
         _sb_.append("[");
         _sb_.append(getTypeId());
         _sb_.append("]");
         
        _sb_.append("{");
        _sb_.append("recharges").append(':').append(msg.Extensions.tostring_list_msg_gsau_RechargeInfo(recharges)).append(',');

        _sb_.append("}");
        return _sb_.toString();
    }

    @Override
    public void marshal(pcore.marshal.Octets os) {
        msg.Extensions.marshal_list_msg_gsau_RechargeInfo(recharges,os);

    }

    @Override
    public void unmarshal(pcore.marshal.Octets os) {
        recharges = msg.Extensions.unmarshal_list_msg_gsau_RechargeInfo(os);

    }
}
