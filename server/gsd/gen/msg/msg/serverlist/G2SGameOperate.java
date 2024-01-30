package msg.serverlist;

public final class G2SGameOperate extends pcore.io.Protocol{
    public static final int TYPE_ID = 15018;

    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }

    public boolean isSuccess;
    public String result;

    public G2SGameOperate() {
         this.result = "";

    }

    public G2SGameOperate(boolean isSuccess , String result ) {
        this.isSuccess = isSuccess;
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
        _sb_.append("isSuccess").append(':').append(isSuccess).append(',');
        _sb_.append("result").append(':').append(result).append(',');

        _sb_.append("}");
        return _sb_.toString();
    }

    @Override
    public void marshal(pcore.marshal.Octets os) {
        os.writeBool(isSuccess);
        os.writeString(result);

    }

    @Override
    public void unmarshal(pcore.marshal.Octets os) {
        isSuccess = os.readBool();
        result = os.readString();

    }
}
