package msg.gs.gm;

public final class ParamInfo  implements pcore.marshal.IMarshal {

    public static final int TYPE_ID = 0;
    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }


    public String name;
    public String desc;
    public String type;

    public ParamInfo() {
         this.name = "";
         this.desc = "";
         this.type = "";

    }

    public ParamInfo(String name , String desc , String type ) {
        this.name = name;
         this.desc = desc;
         this.type = type;
         
    }


    @Override
    public String toString() {
        StringBuilder _sb_ = new StringBuilder();
        _sb_.append(this.getClass().getName());
         
        _sb_.append("{");
        _sb_.append("name").append(':').append(name).append(',');
        _sb_.append("desc").append(':').append(desc).append(',');
        _sb_.append("type").append(':').append(type).append(',');

        _sb_.append("}");
        return _sb_.toString();
    }

    @Override
    public void marshal(pcore.marshal.Octets os) {
        os.writeString(name);
        os.writeString(desc);
        os.writeString(type);

    }

    @Override
    public void unmarshal(pcore.marshal.Octets os) {
        name = os.readString();
        desc = os.readString();
        type = os.readString();

    }

    @Override
    public void marshalCompatible(pcore.marshal.Octets os) {
        os.writeSize(3);
        os.writeCompactUint(pcore.marshal.Tag.STRING | (31320 << pcore.marshal.Tag.TAG_SHIFT));  os.writeString(name);  

        os.writeCompactUint(pcore.marshal.Tag.STRING | (65123 << pcore.marshal.Tag.TAG_SHIFT));  os.writeString(desc);  

        os.writeCompactUint(pcore.marshal.Tag.STRING | (36612 << pcore.marshal.Tag.TAG_SHIFT));  os.writeString(type);  


    }

    @Override
    public void unmarshalCompatible(pcore.marshal.Octets os) {
            for (int _var_num_ = os.readSize(); _var_num_-- > 0; ) {
                final int _id_ = os.readCompactUint();
                switch (_id_) {
                case (pcore.marshal.Tag.STRING | (31320 << pcore.marshal.Tag.TAG_SHIFT)):   name = os.readString(); break;  
                 case (pcore.marshal.Tag.STRING | (65123 << pcore.marshal.Tag.TAG_SHIFT)):   desc = os.readString(); break;  
                 case (pcore.marshal.Tag.STRING | (36612 << pcore.marshal.Tag.TAG_SHIFT)):   type = os.readString(); break;  
                 
                default: pcore.marshal.Tag.skipUnknownField(_id_, os);
            }
        }
    }
}
