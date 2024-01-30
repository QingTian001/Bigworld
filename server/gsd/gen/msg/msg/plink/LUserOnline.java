package msg.plink;

public final class LUserOnline extends pcore.io.Protocol{
    public static final int TYPE_ID = 4593;

    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }

    public long userId;
    public long linkSid;
    public boolean isReconnet;
    public String accountId;
    public String ip;
    public byte[] extra;

    public LUserOnline() {
         this.accountId = "";
         this.ip = "";
         this.extra = new byte[0];

    }

    public LUserOnline(long userId , long linkSid , boolean isReconnet , String accountId , String ip , byte[] extra ) {
        this.userId = userId;
         this.linkSid = linkSid;
         this.isReconnet = isReconnet;
         this.accountId = accountId;
         this.ip = ip;
         this.extra = extra;
         
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
        _sb_.append("isReconnet").append(':').append(isReconnet).append(',');
        _sb_.append("accountId").append(':').append(accountId).append(',');
        _sb_.append("ip").append(':').append(ip).append(',');
        _sb_.append("extra").append(':').append(msg.Extensions.tostring_array_byte(extra)).append(',');

        _sb_.append("}");
        return _sb_.toString();
    }

    @Override
    public void marshal(pcore.marshal.Octets os) {
        os.writeLong(userId);
        os.writeLong(linkSid);
        os.writeBool(isReconnet);
        os.writeString(accountId);
        os.writeString(ip);
        msg.Extensions.marshal_array_byte(extra,os);

    }

    @Override
    public void unmarshal(pcore.marshal.Octets os) {
        userId = os.readLong();
        linkSid = os.readLong();
        isReconnet = os.readBool();
        accountId = os.readString();
        ip = os.readString();
        extra = msg.Extensions.unmarshal_array_byte(os);

    }
}
