package msg.serverlist;

public final class S2GGameOperate extends pcore.io.Protocol{
    public static final int TYPE_ID = 8015;

    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }

    public String module;
    public String command;
    public java.util.List<String> parameters;

    public S2GGameOperate() {
         this.module = "";
         this.command = "";
         this.parameters = pcore.collection.Factory.newList();

    }

    public S2GGameOperate(String module , String command , java.util.List<String> parameters ) {
        this.module = module;
         this.command = command;
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
        _sb_.append("module").append(':').append(module).append(',');
        _sb_.append("command").append(':').append(command).append(',');
        _sb_.append("parameters").append(':').append(msg.Extensions.tostring_list_string(parameters)).append(',');

        _sb_.append("}");
        return _sb_.toString();
    }

    @Override
    public void marshal(pcore.marshal.Octets os) {
        os.writeString(module);
        os.writeString(command);
        msg.Extensions.marshal_list_string(parameters,os);

    }

    @Override
    public void unmarshal(pcore.marshal.Octets os) {
        module = os.readString();
        command = os.readString();
        parameters = msg.Extensions.unmarshal_list_string(os);

    }
}
