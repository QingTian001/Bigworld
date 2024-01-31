package msg.map;

public final class SDebugException extends pcore.io.Protocol{
    public static final int TYPE_ID = 1189;

    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }

    public String message;
    public String stackTrace;

    public SDebugException() {
         this.message = "";
         this.stackTrace = "";

    }

    public SDebugException(String message , String stackTrace ) {
        this.message = message;
         this.stackTrace = stackTrace;
         
    }


    @Override
    public String toString() {
        StringBuilder _sb_ = new StringBuilder();
        _sb_.append(this.getClass().getName());
         _sb_.append("[");
         _sb_.append(getTypeId());
         _sb_.append("]");
         
        _sb_.append("{");
        _sb_.append("message").append(':').append(message).append(',');
        _sb_.append("stackTrace").append(':').append(stackTrace).append(',');

        _sb_.append("}");
        return _sb_.toString();
    }

    @Override
    public void marshal(pcore.marshal.Octets os) {
        os.writeString(message);
        os.writeString(stackTrace);

    }

    @Override
    public void unmarshal(pcore.marshal.Octets os) {
        message = os.readString();
        stackTrace = os.readString();

    }
}
