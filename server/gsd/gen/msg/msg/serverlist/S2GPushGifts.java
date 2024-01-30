package msg.serverlist;

public final class S2GPushGifts extends pcore.io.Protocol{
    public static final int TYPE_ID = 8537;

    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }

    public java.util.List<msg.serverlist.PushRole> pushRoles;

    public S2GPushGifts() {
         this.pushRoles = pcore.collection.Factory.newList();

    }

    public S2GPushGifts(java.util.List<msg.serverlist.PushRole> pushRoles ) {
        this.pushRoles = pushRoles;
         
    }


    @Override
    public String toString() {
        StringBuilder _sb_ = new StringBuilder();
        _sb_.append(this.getClass().getName());
         _sb_.append("[");
         _sb_.append(getTypeId());
         _sb_.append("]");
         
        _sb_.append("{");
        _sb_.append("pushRoles").append(':').append(msg.Extensions.tostring_list_msg_serverlist_PushRole(pushRoles)).append(',');

        _sb_.append("}");
        return _sb_.toString();
    }

    @Override
    public void marshal(pcore.marshal.Octets os) {
        msg.Extensions.marshal_list_msg_serverlist_PushRole(pushRoles,os);

    }

    @Override
    public void unmarshal(pcore.marshal.Octets os) {
        pushRoles = msg.Extensions.unmarshal_list_msg_serverlist_PushRole(os);

    }
}
