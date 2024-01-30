package msg.plink;

public final class GKickUser extends pcore.io.Protocol{
    public static final int TYPE_ID = 14930;

    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }

    public int err;
    public long linkSid;

    public GKickUser() {
    }

    public GKickUser(int err , long linkSid ) {
        this.err = err;
         this.linkSid = linkSid;
         
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
        _sb_.append("linkSid").append(':').append(linkSid).append(',');

        _sb_.append("}");
        return _sb_.toString();
    }

    @Override
    public void marshal(pcore.marshal.Octets os) {
        os.writeInt(err);
        os.writeLong(linkSid);

    }

    @Override
    public void unmarshal(pcore.marshal.Octets os) {
        err = os.readInt();
        linkSid = os.readLong();

    }
}
