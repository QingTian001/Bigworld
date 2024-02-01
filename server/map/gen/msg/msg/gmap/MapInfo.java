package msg.gmap;

public final class MapInfo  implements pcore.marshal.IMarshal {

    public static final int TYPE_ID = 0;
    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }


    public int x;
    public int y;

    public MapInfo() {
    }

    public MapInfo(int x , int y ) {
        this.x = x;
         this.y = y;
         
    }


    @Override
    public String toString() {
        StringBuilder _sb_ = new StringBuilder();
        _sb_.append(this.getClass().getName());
         
        _sb_.append("{");
        _sb_.append("x").append(':').append(x).append(',');
        _sb_.append("y").append(':').append(y).append(',');

        _sb_.append("}");
        return _sb_.toString();
    }

    @Override
    public void marshal(pcore.marshal.Octets os) {
        os.writeInt(x);
        os.writeInt(y);

    }

    @Override
    public void unmarshal(pcore.marshal.Octets os) {
        x = os.readInt();
        y = os.readInt();

    }

    @Override
    public void marshalCompatible(pcore.marshal.Octets os) {
        os.writeSize(2);
        os.writeCompactUint(pcore.marshal.Tag.INT | (120 << pcore.marshal.Tag.TAG_SHIFT));  os.writeInt(x);  

        os.writeCompactUint(pcore.marshal.Tag.INT | (121 << pcore.marshal.Tag.TAG_SHIFT));  os.writeInt(y);  


    }

    @Override
    public void unmarshalCompatible(pcore.marshal.Octets os) {
            for (int _var_num_ = os.readSize(); _var_num_-- > 0; ) {
                final int _id_ = os.readCompactUint();
                switch (_id_) {
                case (pcore.marshal.Tag.INT | (120 << pcore.marshal.Tag.TAG_SHIFT)):   x = os.readInt(); break;  
                 case (pcore.marshal.Tag.INT | (121 << pcore.marshal.Tag.TAG_SHIFT)):   y = os.readInt(); break;  
                 
                default: pcore.marshal.Tag.skipUnknownField(_id_, os);
            }
        }
    }
}
