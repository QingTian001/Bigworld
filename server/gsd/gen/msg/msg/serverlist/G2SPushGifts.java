package msg.serverlist;

public final class G2SPushGifts extends pcore.io.Protocol{
    public static final int TYPE_ID = 1898;

    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }

    public java.util.List<msg.serverlist.PushGiftsRet> results;

    public G2SPushGifts() {
         this.results = pcore.collection.Factory.newList();

    }

    public G2SPushGifts(java.util.List<msg.serverlist.PushGiftsRet> results ) {
        this.results = results;
         
    }


    @Override
    public String toString() {
        StringBuilder _sb_ = new StringBuilder();
        _sb_.append(this.getClass().getName());
         _sb_.append("[");
         _sb_.append(getTypeId());
         _sb_.append("]");
         
        _sb_.append("{");
        _sb_.append("results").append(':').append(msg.Extensions.tostring_list_msg_serverlist_PushGiftsRet(results)).append(',');

        _sb_.append("}");
        return _sb_.toString();
    }

    @Override
    public void marshal(pcore.marshal.Octets os) {
        msg.Extensions.marshal_list_msg_serverlist_PushGiftsRet(results,os);

    }

    @Override
    public void unmarshal(pcore.marshal.Octets os) {
        results = msg.Extensions.unmarshal_list_msg_serverlist_PushGiftsRet(os);

    }
}
