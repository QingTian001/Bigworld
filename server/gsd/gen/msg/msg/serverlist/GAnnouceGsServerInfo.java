package msg.serverlist;

public final class GAnnouceGsServerInfo extends pcore.io.Protocol{
    public static final int TYPE_ID = 11986;

    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }

    public int serverId;
    public int onlineRoleNum;
    public int registerRoleNum;

    public GAnnouceGsServerInfo() {
    }

    public GAnnouceGsServerInfo(int serverId , int onlineRoleNum , int registerRoleNum ) {
        this.serverId = serverId;
         this.onlineRoleNum = onlineRoleNum;
         this.registerRoleNum = registerRoleNum;
         
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
        _sb_.append("onlineRoleNum").append(':').append(onlineRoleNum).append(',');
        _sb_.append("registerRoleNum").append(':').append(registerRoleNum).append(',');

        _sb_.append("}");
        return _sb_.toString();
    }

    @Override
    public void marshal(pcore.marshal.Octets os) {
        os.writeInt(serverId);
        os.writeInt(onlineRoleNum);
        os.writeInt(registerRoleNum);

    }

    @Override
    public void unmarshal(pcore.marshal.Octets os) {
        serverId = os.readInt();
        onlineRoleNum = os.readInt();
        registerRoleNum = os.readInt();

    }
}
