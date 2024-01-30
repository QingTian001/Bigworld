package msg.serverlist;

public final class LinkInfo  implements pcore.marshal.IMarshal {

    public static final int TYPE_ID = 0;
    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }


    public String ip;
    public int port;

    public LinkInfo() {
         this.ip = "";

    }

    public LinkInfo(String ip , int port ) {
        this.ip = ip;
         this.port = port;
         
    }


    @Override
    public String toString() {
        StringBuilder _sb_ = new StringBuilder();
        _sb_.append(this.getClass().getName());
         
        _sb_.append("{");
        _sb_.append("ip").append(':').append(ip).append(',');
        _sb_.append("port").append(':').append(port).append(',');

        _sb_.append("}");
        return _sb_.toString();
    }

    @Override
    public void marshal(pcore.marshal.Octets os) {
        os.writeString(ip);
        os.writeInt(port);

    }

    @Override
    public void unmarshal(pcore.marshal.Octets os) {
        ip = os.readString();
        port = os.readInt();

    }

    @Override
    public void marshalCompatible(pcore.marshal.Octets os) {
        os.writeSize(2);
        os.writeCompactUint(pcore.marshal.Tag.STRING | (3367 << pcore.marshal.Tag.TAG_SHIFT));  os.writeString(ip);  

        os.writeCompactUint(pcore.marshal.Tag.INT | (38989 << pcore.marshal.Tag.TAG_SHIFT));  os.writeInt(port);  


    }

    @Override
    public void unmarshalCompatible(pcore.marshal.Octets os) {
            for (int _var_num_ = os.readSize(); _var_num_-- > 0; ) {
                final int _id_ = os.readCompactUint();
                switch (_id_) {
                case (pcore.marshal.Tag.STRING | (3367 << pcore.marshal.Tag.TAG_SHIFT)):   ip = os.readString(); break;  
                 case (pcore.marshal.Tag.INT | (38989 << pcore.marshal.Tag.TAG_SHIFT)):   port = os.readInt(); break;  
                 
                default: pcore.marshal.Tag.skipUnknownField(_id_, os);
            }
        }
    }
}
