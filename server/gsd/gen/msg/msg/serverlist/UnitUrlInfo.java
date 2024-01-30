package msg.serverlist;

public final class UnitUrlInfo  implements pcore.marshal.IMarshal {

    public static final int TYPE_ID = 0;
    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }


    public String urlType;
    public String url;

    public UnitUrlInfo() {
         this.urlType = "";
         this.url = "";

    }

    public UnitUrlInfo(String urlType , String url ) {
        this.urlType = urlType;
         this.url = url;
         
    }


    @Override
    public String toString() {
        StringBuilder _sb_ = new StringBuilder();
        _sb_.append(this.getClass().getName());
         
        _sb_.append("{");
        _sb_.append("urlType").append(':').append(urlType).append(',');
        _sb_.append("url").append(':').append(url).append(',');

        _sb_.append("}");
        return _sb_.toString();
    }

    @Override
    public void marshal(pcore.marshal.Octets os) {
        os.writeString(urlType);
        os.writeString(url);

    }

    @Override
    public void unmarshal(pcore.marshal.Octets os) {
        urlType = os.readString();
        url = os.readString();

    }

    @Override
    public void marshalCompatible(pcore.marshal.Octets os) {
        os.writeSize(2);
        os.writeCompactUint(pcore.marshal.Tag.STRING | (31891 << pcore.marshal.Tag.TAG_SHIFT));  os.writeString(urlType);  

        os.writeCompactUint(pcore.marshal.Tag.STRING | (50542 << pcore.marshal.Tag.TAG_SHIFT));  os.writeString(url);  


    }

    @Override
    public void unmarshalCompatible(pcore.marshal.Octets os) {
            for (int _var_num_ = os.readSize(); _var_num_-- > 0; ) {
                final int _id_ = os.readCompactUint();
                switch (_id_) {
                case (pcore.marshal.Tag.STRING | (31891 << pcore.marshal.Tag.TAG_SHIFT)):   urlType = os.readString(); break;  
                 case (pcore.marshal.Tag.STRING | (50542 << pcore.marshal.Tag.TAG_SHIFT)):   url = os.readString(); break;  
                 
                default: pcore.marshal.Tag.skipUnknownField(_id_, os);
            }
        }
    }
}
