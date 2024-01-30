package msg.gs.login;

public final class SUserLoginSuccess extends pcore.io.Protocol{
    public static final int TYPE_ID = 6848;

    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }

    public boolean isReconnect;

    public SUserLoginSuccess() {
    }

    public SUserLoginSuccess(boolean isReconnect ) {
        this.isReconnect = isReconnect;
         
    }


    @Override
    public String toString() {
        StringBuilder _sb_ = new StringBuilder();
        _sb_.append(this.getClass().getName());
         _sb_.append("[");
         _sb_.append(getTypeId());
         _sb_.append("]");
         
        _sb_.append("{");
        _sb_.append("isReconnect").append(':').append(isReconnect).append(',');

        _sb_.append("}");
        return _sb_.toString();
    }

    @Override
    public void marshal(pcore.marshal.Octets os) {
        os.writeBool(isReconnect);

    }

    @Override
    public void unmarshal(pcore.marshal.Octets os) {
        isReconnect = os.readBool();

    }
}
