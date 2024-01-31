package msg.map;

public final class Vector2  implements pcore.marshal.IMarshal {

    public static final int TYPE_ID = 0;
    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }


    public float x;
    public float y;

    public Vector2() {
    }

    public Vector2(float x , float y ) {
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
        os.writeFloat(x);
        os.writeFloat(y);

    }

    @Override
    public void unmarshal(pcore.marshal.Octets os) {
        x = os.readFloat();
        y = os.readFloat();

    }

    @Override
    public void marshalCompatible(pcore.marshal.Octets os) {
        os.writeSize(2);
        os.writeCompactUint(pcore.marshal.Tag.FLOAT | (120 << pcore.marshal.Tag.TAG_SHIFT));  os.writeFloat(x);  

        os.writeCompactUint(pcore.marshal.Tag.FLOAT | (121 << pcore.marshal.Tag.TAG_SHIFT));  os.writeFloat(y);  


    }

    @Override
    public void unmarshalCompatible(pcore.marshal.Octets os) {
            for (int _var_num_ = os.readSize(); _var_num_-- > 0; ) {
                final int _id_ = os.readCompactUint();
                switch (_id_) {
                case (pcore.marshal.Tag.FLOAT | (120 << pcore.marshal.Tag.TAG_SHIFT)):   x = os.readFloat(); break;  
                 case (pcore.marshal.Tag.FLOAT | (121 << pcore.marshal.Tag.TAG_SHIFT)):   y = os.readFloat(); break;  
                 
                default: pcore.marshal.Tag.skipUnknownField(_id_, os);
            }
        }
    }
}
