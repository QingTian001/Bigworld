package msg.serverlist;

public final class GAnnouceGsServerState extends pcore.io.Protocol{
    public static final int TYPE_ID = 1412;

    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }

    public int serverId;
    public int serverState;

    public GAnnouceGsServerState() {
    }

    public GAnnouceGsServerState(int serverId , int serverState ) {
        this.serverId = serverId;
         this.serverState = serverState;
         
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
        _sb_.append("serverState").append(':').append(serverState).append(',');

        _sb_.append("}");
        return _sb_.toString();
    }

    @Override
    public void marshal(pcore.marshal.Octets os) {
        os.writeInt(serverId);
        os.writeInt(serverState);

    }

    @Override
    public void unmarshal(pcore.marshal.Octets os) {
        serverId = os.readInt();
        serverState = os.readInt();

    }
}
