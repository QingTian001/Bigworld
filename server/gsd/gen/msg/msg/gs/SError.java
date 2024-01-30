package msg.gs;

public final class SError extends pcore.io.Protocol{
    public static final int TYPE_ID = 4322;

    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }

    public int err;
    public java.util.List<String> parameters;

    public SError() {
         this.parameters = pcore.collection.Factory.newList();

    }

    public SError(int err , java.util.List<String> parameters ) {
        this.err = err;
         this.parameters = parameters;
         
    }


    @Override
    public String toString() {
        StringBuilder _sb_ = new StringBuilder();
        _sb_.append(this.getClass().getName());
         _sb_.append("[");
         _sb_.append(getTypeId());
         _sb_.append("]");
         
        _sb_.append("{");
        _sb_.append("err").append(':').append(err).append(',');
        _sb_.append("parameters").append(':').append(msg.Extensions.tostring_list_string(parameters)).append(',');

        _sb_.append("}");
        return _sb_.toString();
    }

    @Override
    public void marshal(pcore.marshal.Octets os) {
        os.writeInt(err);
        msg.Extensions.marshal_list_string(parameters,os);

    }

    @Override
    public void unmarshal(pcore.marshal.Octets os) {
        err = os.readInt();
        parameters = msg.Extensions.unmarshal_list_string(os);

    }
}
