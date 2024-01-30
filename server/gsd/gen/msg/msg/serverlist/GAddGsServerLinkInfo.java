package msg.serverlist;

public final class GAddGsServerLinkInfo extends pcore.io.Protocol{
    public static final int TYPE_ID = 4202;

    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }

    public int serverId;
    public int localId;
    public String innerIp;
    public int innerPort;
    public String outerIp;
    public String outerIpv6;
    public int outerPort;
    public boolean isBackup;

    public GAddGsServerLinkInfo() {
         this.innerIp = "";
         this.outerIp = "";
         this.outerIpv6 = "";

    }

    public GAddGsServerLinkInfo(int serverId , int localId , String innerIp , int innerPort , String outerIp , String outerIpv6 , int outerPort , boolean isBackup ) {
        this.serverId = serverId;
         this.localId = localId;
         this.innerIp = innerIp;
         this.innerPort = innerPort;
         this.outerIp = outerIp;
         this.outerIpv6 = outerIpv6;
         this.outerPort = outerPort;
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
        _sb_.append("serverId").append(':').append(serverId).append(',');
        _sb_.append("localId").append(':').append(localId).append(',');
        _sb_.append("innerIp").append(':').append(innerIp).append(',');
        _sb_.append("innerPort").append(':').append(innerPort).append(',');
        _sb_.append("outerIp").append(':').append(outerIp).append(',');
        _sb_.append("outerIpv6").append(':').append(outerIpv6).append(',');
        _sb_.append("outerPort").append(':').append(outerPort).append(',');
        _sb_.append("isBackup").append(':').append(isBackup).append(',');

        _sb_.append("}");
        return _sb_.toString();
    }

    @Override
    public void marshal(pcore.marshal.Octets os) {
        os.writeInt(serverId);
        os.writeInt(localId);
        os.writeString(innerIp);
        os.writeInt(innerPort);
        os.writeString(outerIp);
        os.writeString(outerIpv6);
        os.writeInt(outerPort);
        os.writeBool(isBackup);

    }

    @Override
    public void unmarshal(pcore.marshal.Octets os) {
        serverId = os.readInt();
        localId = os.readInt();
        innerIp = os.readString();
        innerPort = os.readInt();
        outerIp = os.readString();
        outerIpv6 = os.readString();
        outerPort = os.readInt();
        isBackup = os.readBool();

    }
}
