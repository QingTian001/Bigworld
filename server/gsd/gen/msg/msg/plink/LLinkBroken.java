package msg.plink;

public final class LLinkBroken extends pcore.io.Protocol{
    public static final int TYPE_ID = 5917;

    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }

    public long userId;
    public long linkSid;

    public LLinkBroken() {
    }

    public LLinkBroken(long userId , long linkSid ) {
        this.userId = userId;
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
        _sb_.append("userId").append(':').append(userId).append(',');
        _sb_.append("linkSid").append(':').append(linkSid).append(',');

        _sb_.append("}");
        return _sb_.toString();
    }

    @Override
    public void marshal(pcore.marshal.Octets os) {
        os.writeLong(userId);
        os.writeLong(linkSid);

    }

    @Override
    public void unmarshal(pcore.marshal.Octets os) {
        userId = os.readLong();
        linkSid = os.readLong();

    }
}
