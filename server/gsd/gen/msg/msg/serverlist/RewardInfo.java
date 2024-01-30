package msg.serverlist;

public final class RewardInfo  implements pcore.marshal.IMarshal {

    public static final int TYPE_ID = 0;
    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }


    public int bonusCfgId;
    public int count;

    public RewardInfo() {
    }

    public RewardInfo(int bonusCfgId , int count ) {
        this.bonusCfgId = bonusCfgId;
         this.count = count;
         
    }


    @Override
    public String toString() {
        StringBuilder _sb_ = new StringBuilder();
        _sb_.append(this.getClass().getName());
         
        _sb_.append("{");
        _sb_.append("bonusCfgId").append(':').append(bonusCfgId).append(',');
        _sb_.append("count").append(':').append(count).append(',');

        _sb_.append("}");
        return _sb_.toString();
    }

    @Override
    public void marshal(pcore.marshal.Octets os) {
        os.writeInt(bonusCfgId);
        os.writeInt(count);

    }

    @Override
    public void unmarshal(pcore.marshal.Octets os) {
        bonusCfgId = os.readInt();
        count = os.readInt();

    }

    @Override
    public void marshalCompatible(pcore.marshal.Octets os) {
        os.writeSize(2);
        os.writeCompactUint(pcore.marshal.Tag.INT | (50656 << pcore.marshal.Tag.TAG_SHIFT));  os.writeInt(bonusCfgId);  

        os.writeCompactUint(pcore.marshal.Tag.INT | (19304 << pcore.marshal.Tag.TAG_SHIFT));  os.writeInt(count);  


    }

    @Override
    public void unmarshalCompatible(pcore.marshal.Octets os) {
            for (int _var_num_ = os.readSize(); _var_num_-- > 0; ) {
                final int _id_ = os.readCompactUint();
                switch (_id_) {
                case (pcore.marshal.Tag.INT | (50656 << pcore.marshal.Tag.TAG_SHIFT)):   bonusCfgId = os.readInt(); break;  
                 case (pcore.marshal.Tag.INT | (19304 << pcore.marshal.Tag.TAG_SHIFT)):   count = os.readInt(); break;  
                 
                default: pcore.marshal.Tag.skipUnknownField(_id_, os);
            }
        }
    }
}
