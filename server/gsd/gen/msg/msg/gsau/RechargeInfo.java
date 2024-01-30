package msg.gsau;

public final class RechargeInfo  implements pcore.marshal.IMarshal {

    public static final int TYPE_ID = 0;
    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }


    public long uid;
    public long roleId;
    public String orderId;
    public String sdkOrderId;
    public String loginAccountId;
    public String channel;
    public int serverId;
    public String goodsId;
    public int price;
    public int state;
    public String rechargeAccountId;
    public int gear;

    public RechargeInfo() {
         this.orderId = "";
         this.sdkOrderId = "";
         this.loginAccountId = "";
         this.channel = "";
         this.goodsId = "";
         this.rechargeAccountId = "";

    }

    public RechargeInfo(long uid , long roleId , String orderId , String sdkOrderId , String loginAccountId , String channel , int serverId , String goodsId , int price , int state , String rechargeAccountId , int gear ) {
        this.uid = uid;
         this.roleId = roleId;
         this.orderId = orderId;
         this.sdkOrderId = sdkOrderId;
         this.loginAccountId = loginAccountId;
         this.channel = channel;
         this.serverId = serverId;
         this.goodsId = goodsId;
         this.price = price;
         this.state = state;
         this.rechargeAccountId = rechargeAccountId;
         this.gear = gear;
         
    }


    @Override
    public String toString() {
        StringBuilder _sb_ = new StringBuilder();
        _sb_.append(this.getClass().getName());
         
        _sb_.append("{");
        _sb_.append("uid").append(':').append(uid).append(',');
        _sb_.append("roleId").append(':').append(roleId).append(',');
        _sb_.append("orderId").append(':').append(orderId).append(',');
        _sb_.append("sdkOrderId").append(':').append(sdkOrderId).append(',');
        _sb_.append("loginAccountId").append(':').append(loginAccountId).append(',');
        _sb_.append("channel").append(':').append(channel).append(',');
        _sb_.append("serverId").append(':').append(serverId).append(',');
        _sb_.append("goodsId").append(':').append(goodsId).append(',');
        _sb_.append("price").append(':').append(price).append(',');
        _sb_.append("state").append(':').append(state).append(',');
        _sb_.append("rechargeAccountId").append(':').append(rechargeAccountId).append(',');
        _sb_.append("gear").append(':').append(gear).append(',');

        _sb_.append("}");
        return _sb_.toString();
    }

    @Override
    public void marshal(pcore.marshal.Octets os) {
        os.writeLong(uid);
        os.writeLong(roleId);
        os.writeString(orderId);
        os.writeString(sdkOrderId);
        os.writeString(loginAccountId);
        os.writeString(channel);
        os.writeInt(serverId);
        os.writeString(goodsId);
        os.writeInt(price);
        os.writeInt(state);
        os.writeString(rechargeAccountId);
        os.writeInt(gear);

    }

    @Override
    public void unmarshal(pcore.marshal.Octets os) {
        uid = os.readLong();
        roleId = os.readLong();
        orderId = os.readString();
        sdkOrderId = os.readString();
        loginAccountId = os.readString();
        channel = os.readString();
        serverId = os.readInt();
        goodsId = os.readString();
        price = os.readInt();
        state = os.readInt();
        rechargeAccountId = os.readString();
        gear = os.readInt();

    }

    @Override
    public void marshalCompatible(pcore.marshal.Octets os) {
        os.writeSize(12);
        os.writeCompactUint(pcore.marshal.Tag.LONG | (50255 << pcore.marshal.Tag.TAG_SHIFT));  os.writeLong(uid);  

        os.writeCompactUint(pcore.marshal.Tag.LONG | (33959 << pcore.marshal.Tag.TAG_SHIFT));  os.writeLong(roleId);  

        os.writeCompactUint(pcore.marshal.Tag.STRING | (49925 << pcore.marshal.Tag.TAG_SHIFT));  os.writeString(orderId);  

        os.writeCompactUint(pcore.marshal.Tag.STRING | (19795 << pcore.marshal.Tag.TAG_SHIFT));  os.writeString(sdkOrderId);  

        os.writeCompactUint(pcore.marshal.Tag.STRING | (44548 << pcore.marshal.Tag.TAG_SHIFT));  os.writeString(loginAccountId);  

        os.writeCompactUint(pcore.marshal.Tag.STRING | (20728 << pcore.marshal.Tag.TAG_SHIFT));  os.writeString(channel);  

        os.writeCompactUint(pcore.marshal.Tag.INT | (8587 << pcore.marshal.Tag.TAG_SHIFT));  os.writeInt(serverId);  

        os.writeCompactUint(pcore.marshal.Tag.STRING | (5818 << pcore.marshal.Tag.TAG_SHIFT));  os.writeString(goodsId);  

        os.writeCompactUint(pcore.marshal.Tag.INT | (43754 << pcore.marshal.Tag.TAG_SHIFT));  os.writeInt(price);  

        os.writeCompactUint(pcore.marshal.Tag.INT | (48647 << pcore.marshal.Tag.TAG_SHIFT));  os.writeInt(state);  

        os.writeCompactUint(pcore.marshal.Tag.STRING | (41776 << pcore.marshal.Tag.TAG_SHIFT));  os.writeString(rechargeAccountId);  

        os.writeCompactUint(pcore.marshal.Tag.INT | (22879 << pcore.marshal.Tag.TAG_SHIFT));  os.writeInt(gear);  


    }

    @Override
    public void unmarshalCompatible(pcore.marshal.Octets os) {
            for (int _var_num_ = os.readSize(); _var_num_-- > 0; ) {
                final int _id_ = os.readCompactUint();
                switch (_id_) {
                case (pcore.marshal.Tag.LONG | (50255 << pcore.marshal.Tag.TAG_SHIFT)):   uid = os.readLong(); break;  
                 case (pcore.marshal.Tag.LONG | (33959 << pcore.marshal.Tag.TAG_SHIFT)):   roleId = os.readLong(); break;  
                 case (pcore.marshal.Tag.STRING | (49925 << pcore.marshal.Tag.TAG_SHIFT)):   orderId = os.readString(); break;  
                 case (pcore.marshal.Tag.STRING | (19795 << pcore.marshal.Tag.TAG_SHIFT)):   sdkOrderId = os.readString(); break;  
                 case (pcore.marshal.Tag.STRING | (44548 << pcore.marshal.Tag.TAG_SHIFT)):   loginAccountId = os.readString(); break;  
                 case (pcore.marshal.Tag.STRING | (20728 << pcore.marshal.Tag.TAG_SHIFT)):   channel = os.readString(); break;  
                 case (pcore.marshal.Tag.INT | (8587 << pcore.marshal.Tag.TAG_SHIFT)):   serverId = os.readInt(); break;  
                 case (pcore.marshal.Tag.STRING | (5818 << pcore.marshal.Tag.TAG_SHIFT)):   goodsId = os.readString(); break;  
                 case (pcore.marshal.Tag.INT | (43754 << pcore.marshal.Tag.TAG_SHIFT)):   price = os.readInt(); break;  
                 case (pcore.marshal.Tag.INT | (48647 << pcore.marshal.Tag.TAG_SHIFT)):   state = os.readInt(); break;  
                 case (pcore.marshal.Tag.STRING | (41776 << pcore.marshal.Tag.TAG_SHIFT)):   rechargeAccountId = os.readString(); break;  
                 case (pcore.marshal.Tag.INT | (22879 << pcore.marshal.Tag.TAG_SHIFT)):   gear = os.readInt(); break;  
                 
                default: pcore.marshal.Tag.skipUnknownField(_id_, os);
            }
        }
    }
}
