package msg.serverlist;

public final class S2GMailSendRoles extends pcore.io.Protocol{
    public static final int TYPE_ID = 12588;

    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }

    public msg.serverlist.RolesMail mail;

    public S2GMailSendRoles() {
         this.mail = new msg.serverlist.RolesMail();

    }

    public S2GMailSendRoles(msg.serverlist.RolesMail mail ) {
        this.mail = mail;
         
    }


    @Override
    public String toString() {
        StringBuilder _sb_ = new StringBuilder();
        _sb_.append(this.getClass().getName());
         _sb_.append("[");
         _sb_.append(getTypeId());
         _sb_.append("]");
         
        _sb_.append("{");
        _sb_.append("mail").append(':').append(msg.Extensions.tostring_msg_serverlist_RolesMail(mail)).append(',');

        _sb_.append("}");
        return _sb_.toString();
    }

    @Override
    public void marshal(pcore.marshal.Octets os) {
        msg.Extensions.marshal_msg_serverlist_RolesMail(mail,os);

    }

    @Override
    public void unmarshal(pcore.marshal.Octets os) {
        mail = msg.Extensions.unmarshal_msg_serverlist_RolesMail(os);

    }
}
