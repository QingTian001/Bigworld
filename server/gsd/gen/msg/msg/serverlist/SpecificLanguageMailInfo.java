package msg.serverlist;

public final class SpecificLanguageMailInfo  implements pcore.marshal.IMarshal {

    public static final int TYPE_ID = 0;
    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }


    public String title;
    public String content;

    public SpecificLanguageMailInfo() {
         this.title = "";
         this.content = "";

    }

    public SpecificLanguageMailInfo(String title , String content ) {
        this.title = title;
         this.content = content;
         
    }


    @Override
    public String toString() {
        StringBuilder _sb_ = new StringBuilder();
        _sb_.append(this.getClass().getName());
         
        _sb_.append("{");
        _sb_.append("title").append(':').append(title).append(',');
        _sb_.append("content").append(':').append(content).append(',');

        _sb_.append("}");
        return _sb_.toString();
    }

    @Override
    public void marshal(pcore.marshal.Octets os) {
        os.writeString(title);
        os.writeString(content);

    }

    @Override
    public void unmarshal(pcore.marshal.Octets os) {
        title = os.readString();
        content = os.readString();

    }

    @Override
    public void marshalCompatible(pcore.marshal.Octets os) {
        os.writeSize(2);
        os.writeCompactUint(pcore.marshal.Tag.STRING | (7108 << pcore.marshal.Tag.TAG_SHIFT));  os.writeString(title);  

        os.writeCompactUint(pcore.marshal.Tag.STRING | (64451 << pcore.marshal.Tag.TAG_SHIFT));  os.writeString(content);  


    }

    @Override
    public void unmarshalCompatible(pcore.marshal.Octets os) {
            for (int _var_num_ = os.readSize(); _var_num_-- > 0; ) {
                final int _id_ = os.readCompactUint();
                switch (_id_) {
                case (pcore.marshal.Tag.STRING | (7108 << pcore.marshal.Tag.TAG_SHIFT)):   title = os.readString(); break;  
                 case (pcore.marshal.Tag.STRING | (64451 << pcore.marshal.Tag.TAG_SHIFT)):   content = os.readString(); break;  
                 
                default: pcore.marshal.Tag.skipUnknownField(_id_, os);
            }
        }
    }
}
