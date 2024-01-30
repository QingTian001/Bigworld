package msg.serverlist;

public final class G2SMailSendServer extends pcore.io.Protocol{
    public static final int TYPE_ID = 10617;

    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }

    public msg.serverlist.MailSendResult result;

    public G2SMailSendServer() {
    }

    public G2SMailSendServer(msg.serverlist.MailSendResult result ) {
        this.result = result;
         
    }


    @Override
    public String toString() {
        StringBuilder _sb_ = new StringBuilder();
        _sb_.append(this.getClass().getName());
         _sb_.append("[");
         _sb_.append(getTypeId());
         _sb_.append("]");
         
        _sb_.append("{");
        _sb_.append("result").append(':').append(msg.Extensions.tostring_msg_serverlist_MailSendResult(result)).append(',');

        _sb_.append("}");
        return _sb_.toString();
    }

    @Override
    public void marshal(pcore.marshal.Octets os) {
        msg.Extensions.marshal_msg_serverlist_MailSendResult(result,os);

    }

    @Override
    public void unmarshal(pcore.marshal.Octets os) {
        result = msg.Extensions.unmarshal_msg_serverlist_MailSendResult(os);

    }
}
