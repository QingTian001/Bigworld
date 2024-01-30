package msg.serverlist;

public final class G2SMailSendRoles extends pcore.io.Protocol{
    public static final int TYPE_ID = 8118;

    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }

    public msg.serverlist.MailSendRolesResultSuccess succResult;
    public msg.serverlist.MailSendResultError errResult;

    public G2SMailSendRoles() {
         this.succResult = new msg.serverlist.MailSendRolesResultSuccess();

    }

    public G2SMailSendRoles(msg.serverlist.MailSendRolesResultSuccess succResult , msg.serverlist.MailSendResultError errResult ) {
        this.succResult = succResult;
         this.errResult = errResult;
         
    }


    @Override
    public String toString() {
        StringBuilder _sb_ = new StringBuilder();
        _sb_.append(this.getClass().getName());
         _sb_.append("[");
         _sb_.append(getTypeId());
         _sb_.append("]");
         
        _sb_.append("{");
        _sb_.append("succResult").append(':').append(msg.Extensions.tostring_msg_serverlist_MailSendRolesResultSuccess(succResult)).append(',');
        _sb_.append("errResult").append(':').append(msg.Extensions.tostring_msg_serverlist_MailSendResultError(errResult)).append(',');

        _sb_.append("}");
        return _sb_.toString();
    }

    @Override
    public void marshal(pcore.marshal.Octets os) {
        msg.Extensions.marshal_msg_serverlist_MailSendRolesResultSuccess(succResult,os);
        msg.Extensions.marshal_msg_serverlist_MailSendResultError(errResult,os);

    }

    @Override
    public void unmarshal(pcore.marshal.Octets os) {
        succResult = msg.Extensions.unmarshal_msg_serverlist_MailSendRolesResultSuccess(os);
        errResult = msg.Extensions.unmarshal_msg_serverlist_MailSendResultError(os);

    }
}
