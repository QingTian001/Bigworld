package msg.serverlist;

public final class GRmvGsServerLinkInfo extends pcore.io.Protocol{
    public static final int TYPE_ID = 5751;

    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }

    public int serverId;
    public String innerIp;
    public int innerPort;

    public GRmvGsServerLinkInfo() {
         this.innerIp = "";

    }

    public GRmvGsServerLinkInfo(int serverId , String innerIp , int innerPort ) {
        this.serverId = serverId;
         this.innerIp = innerIp;
         this.innerPort = innerPort;
         
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
        _sb_.append("innerIp").append(':').append(innerIp).append(',');
        _sb_.append("innerPort").append(':').append(innerPort).append(',');

        _sb_.append("}");
        return _sb_.toString();
    }

    @Override
    public void marshal(pcore.marshal.Octets os) {
        os.writeInt(serverId);
        os.writeString(innerIp);
        os.writeInt(innerPort);

    }

    @Override
    public void unmarshal(pcore.marshal.Octets os) {
        serverId = os.readInt();
        innerIp = os.readString();
        innerPort = os.readInt();

    }
}
