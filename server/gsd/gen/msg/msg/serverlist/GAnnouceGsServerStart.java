package msg.serverlist;

public final class GAnnouceGsServerStart extends pcore.io.Protocol{
    public static final int TYPE_ID = 1365;

    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }

    public int serverId;
    public long startTime;
    public long firstStartTime;

    public GAnnouceGsServerStart() {
    }

    public GAnnouceGsServerStart(int serverId , long startTime , long firstStartTime ) {
        this.serverId = serverId;
         this.startTime = startTime;
         this.firstStartTime = firstStartTime;
         
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
        _sb_.append("startTime").append(':').append(startTime).append(',');
        _sb_.append("firstStartTime").append(':').append(firstStartTime).append(',');

        _sb_.append("}");
        return _sb_.toString();
    }

    @Override
    public void marshal(pcore.marshal.Octets os) {
        os.writeInt(serverId);
        os.writeLong(startTime);
        os.writeLong(firstStartTime);

    }

    @Override
    public void unmarshal(pcore.marshal.Octets os) {
        serverId = os.readInt();
        startTime = os.readLong();
        firstStartTime = os.readLong();

    }
}
