package msg.net;

public final class GClientAnnouceServerInfo extends pcore.io.Protocol{
    public static final int TYPE_ID = 1236;

    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }

    public int serverId;

    public GClientAnnouceServerInfo() {
    }

    public GClientAnnouceServerInfo(int serverId ) {
        this.serverId = serverId;
         
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

        _sb_.append("}");
        return _sb_.toString();
    }

    @Override
    public void marshal(pcore.marshal.Octets os) {
        os.writeInt(serverId);

    }

    @Override
    public void unmarshal(pcore.marshal.Octets os) {
        serverId = os.readInt();

    }
}
