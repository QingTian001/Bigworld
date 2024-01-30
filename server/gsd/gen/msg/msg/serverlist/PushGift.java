package msg.serverlist;

public final class PushGift  implements pcore.marshal.IMarshal {

    public static final int TYPE_ID = 0;
    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }


    public int id;
    public long pushId;
    public long pushTime;
    public int showTime;
    public int buyLimit;
    public int displayOrder;
    public int showStyle;

    public PushGift() {
    }

    public PushGift(int id , long pushId , long pushTime , int showTime , int buyLimit , int displayOrder , int showStyle ) {
        this.id = id;
         this.pushId = pushId;
         this.pushTime = pushTime;
         this.showTime = showTime;
         this.buyLimit = buyLimit;
         this.displayOrder = displayOrder;
         this.showStyle = showStyle;
         
    }


    @Override
    public String toString() {
        StringBuilder _sb_ = new StringBuilder();
        _sb_.append(this.getClass().getName());
         
        _sb_.append("{");
        _sb_.append("id").append(':').append(id).append(',');
        _sb_.append("pushId").append(':').append(pushId).append(',');
        _sb_.append("pushTime").append(':').append(pushTime).append(',');
        _sb_.append("showTime").append(':').append(showTime).append(',');
        _sb_.append("buyLimit").append(':').append(buyLimit).append(',');
        _sb_.append("displayOrder").append(':').append(displayOrder).append(',');
        _sb_.append("showStyle").append(':').append(showStyle).append(',');

        _sb_.append("}");
        return _sb_.toString();
    }

    @Override
    public void marshal(pcore.marshal.Octets os) {
        os.writeInt(id);
        os.writeLong(pushId);
        os.writeLong(pushTime);
        os.writeInt(showTime);
        os.writeInt(buyLimit);
        os.writeInt(displayOrder);
        os.writeInt(showStyle);

    }

    @Override
    public void unmarshal(pcore.marshal.Octets os) {
        id = os.readInt();
        pushId = os.readLong();
        pushTime = os.readLong();
        showTime = os.readInt();
        buyLimit = os.readInt();
        displayOrder = os.readInt();
        showStyle = os.readInt();

    }

    @Override
    public void marshalCompatible(pcore.marshal.Octets os) {
        os.writeSize(7);
        os.writeCompactUint(pcore.marshal.Tag.INT | (3355 << pcore.marshal.Tag.TAG_SHIFT));  os.writeInt(id);  

        os.writeCompactUint(pcore.marshal.Tag.LONG | (27633 << pcore.marshal.Tag.TAG_SHIFT));  os.writeLong(pushId);  

        os.writeCompactUint(pcore.marshal.Tag.LONG | (61229 << pcore.marshal.Tag.TAG_SHIFT));  os.writeLong(pushTime);  

        os.writeCompactUint(pcore.marshal.Tag.INT | (4196 << pcore.marshal.Tag.TAG_SHIFT));  os.writeInt(showTime);  

        os.writeCompactUint(pcore.marshal.Tag.INT | (50035 << pcore.marshal.Tag.TAG_SHIFT));  os.writeInt(buyLimit);  

        os.writeCompactUint(pcore.marshal.Tag.INT | (18504 << pcore.marshal.Tag.TAG_SHIFT));  os.writeInt(displayOrder);  

        os.writeCompactUint(pcore.marshal.Tag.INT | (58674 << pcore.marshal.Tag.TAG_SHIFT));  os.writeInt(showStyle);  


    }

    @Override
    public void unmarshalCompatible(pcore.marshal.Octets os) {
            for (int _var_num_ = os.readSize(); _var_num_-- > 0; ) {
                final int _id_ = os.readCompactUint();
                switch (_id_) {
                case (pcore.marshal.Tag.INT | (3355 << pcore.marshal.Tag.TAG_SHIFT)):   id = os.readInt(); break;  
                 case (pcore.marshal.Tag.LONG | (27633 << pcore.marshal.Tag.TAG_SHIFT)):   pushId = os.readLong(); break;  
                 case (pcore.marshal.Tag.LONG | (61229 << pcore.marshal.Tag.TAG_SHIFT)):   pushTime = os.readLong(); break;  
                 case (pcore.marshal.Tag.INT | (4196 << pcore.marshal.Tag.TAG_SHIFT)):   showTime = os.readInt(); break;  
                 case (pcore.marshal.Tag.INT | (50035 << pcore.marshal.Tag.TAG_SHIFT)):   buyLimit = os.readInt(); break;  
                 case (pcore.marshal.Tag.INT | (18504 << pcore.marshal.Tag.TAG_SHIFT)):   displayOrder = os.readInt(); break;  
                 case (pcore.marshal.Tag.INT | (58674 << pcore.marshal.Tag.TAG_SHIFT)):   showStyle = os.readInt(); break;  
                 
                default: pcore.marshal.Tag.skipUnknownField(_id_, os);
            }
        }
    }
}
