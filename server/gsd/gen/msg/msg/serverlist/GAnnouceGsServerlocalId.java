package msg.serverlist;

public final class GAnnouceGsServerlocalId extends pcore.io.Protocol{
    public static final int TYPE_ID = 4183;

    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }

    public int serverId;
    public int localId;

    public GAnnouceGsServerlocalId() {
    }

    public GAnnouceGsServerlocalId(int serverId , int localId ) {
        this.serverId = serverId;
         this.localId = localId;
         
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

        _sb_.append("}");
        return _sb_.toString();
    }

    @Override
    public void marshal(pcore.marshal.Octets os) {
        os.writeInt(serverId);
        os.writeInt(localId);

    }

    @Override
    public void unmarshal(pcore.marshal.Octets os) {
        serverId = os.readInt();
        localId = os.readInt();

    }
}
