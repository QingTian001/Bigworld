package msg.plink;

public final class LAnnounceOuterNetAddress extends pcore.io.Protocol{
    public static final int TYPE_ID = 11369;

    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }

    public String ip;
    public String ipv6;
    public int port;
    public boolean isBackup;

    public LAnnounceOuterNetAddress() {
         this.ip = "";
         this.ipv6 = "";

    }

    public LAnnounceOuterNetAddress(String ip , String ipv6 , int port , boolean isBackup ) {
        this.ip = ip;
         this.ipv6 = ipv6;
         this.port = port;
         this.isBackup = isBackup;
         
    }


    @Override
    public String toString() {
        StringBuilder _sb_ = new StringBuilder();
        _sb_.append(this.getClass().getName());
         _sb_.append("[");
         _sb_.append(getTypeId());
         _sb_.append("]");
         
        _sb_.append("{");
        _sb_.append("ip").append(':').append(ip).append(',');
        _sb_.append("ipv6").append(':').append(ipv6).append(',');
        _sb_.append("port").append(':').append(port).append(',');
        _sb_.append("isBackup").append(':').append(isBackup).append(',');

        _sb_.append("}");
        return _sb_.toString();
    }

    @Override
    public void marshal(pcore.marshal.Octets os) {
        os.writeString(ip);
        os.writeString(ipv6);
        os.writeInt(port);
        os.writeBool(isBackup);

    }

    @Override
    public void unmarshal(pcore.marshal.Octets os) {
        ip = os.readString();
        ipv6 = os.readString();
        port = os.readInt();
        isBackup = os.readBool();

    }
}
