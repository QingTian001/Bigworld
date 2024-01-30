package msg.serverlist;

public final class G2SCustomMailDel extends pcore.io.Protocol{
    public static final int TYPE_ID = 8478;

    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }

    public msg.serverlist.Result result;

    public G2SCustomMailDel() {
    }

    public G2SCustomMailDel(msg.serverlist.Result result ) {
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
        _sb_.append("result").append(':').append(msg.Extensions.tostring_msg_serverlist_Result(result)).append(',');

        _sb_.append("}");
        return _sb_.toString();
    }

    @Override
    public void marshal(pcore.marshal.Octets os) {
        msg.Extensions.marshal_msg_serverlist_Result(result,os);

    }

    @Override
    public void unmarshal(pcore.marshal.Octets os) {
        result = msg.Extensions.unmarshal_msg_serverlist_Result(os);

    }
}
