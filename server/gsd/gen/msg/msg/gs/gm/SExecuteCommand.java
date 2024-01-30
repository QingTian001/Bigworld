package msg.gs.gm;

public final class SExecuteCommand extends pcore.io.Protocol{
    public static final int TYPE_ID = 4721;

    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }

    public String result;

    public SExecuteCommand() {
         this.result = "";

    }

    public SExecuteCommand(String result ) {
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
        _sb_.append("result").append(':').append(result).append(',');

        _sb_.append("}");
        return _sb_.toString();
    }

    @Override
    public void marshal(pcore.marshal.Octets os) {
        os.writeString(result);

    }

    @Override
    public void unmarshal(pcore.marshal.Octets os) {
        result = os.readString();

    }
}
