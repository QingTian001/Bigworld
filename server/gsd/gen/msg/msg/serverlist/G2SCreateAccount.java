package msg.serverlist;

public final class G2SCreateAccount extends pcore.io.Protocol{
    public static final int TYPE_ID = 14892;

    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }

    public int serverId;
    public String ip;
    public String language;
    public String platform;

    public G2SCreateAccount() {
         this.ip = "";
         this.language = "";
         this.platform = "";

    }

    public G2SCreateAccount(int serverId , String ip , String language , String platform ) {
        this.serverId = serverId;
         this.ip = ip;
         this.language = language;
         this.platform = platform;
         
    }


    @Override
    public String toString() {
        StringBuilder _sb_ = new StringBuilder();
        _sb_.append(this.getClass().getName());
         _sb_.append("[");
         _sb_.append(getTypeId());
         _sb_.append("]");
         
        _sb_.append("{");
        _sb_.append("serverId").append(':').append(serverId).append(',');
        _sb_.append("ip").append(':').append(ip).append(',');
        _sb_.append("language").append(':').append(language).append(',');
        _sb_.append("platform").append(':').append(platform).append(',');

        _sb_.append("}");
        return _sb_.toString();
    }

    @Override
    public void marshal(pcore.marshal.Octets os) {
        os.writeInt(serverId);
        os.writeString(ip);
        os.writeString(language);
        os.writeString(platform);

    }

    @Override
    public void unmarshal(pcore.marshal.Octets os) {
        serverId = os.readInt();
        ip = os.readString();
        language = os.readString();
        platform = os.readString();

    }
}
