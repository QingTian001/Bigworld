package msg.serverlist;

public final class S2GQueryRoleMail extends pcore.io.Protocol{
    public static final int TYPE_ID = 11044;

    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }

    public long roleId;
    public long sendStartTimeInSec;
    public long sendEndTimeInSec;
    public int isRead;
    public int isClaimed;
    public String language;
    public int page;
    public int pageSize;
    public String order;

    public S2GQueryRoleMail() {
         this.language = "";
         this.order = "";

    }

    public S2GQueryRoleMail(long roleId , long sendStartTimeInSec , long sendEndTimeInSec , int isRead , int isClaimed , String language , int page , int pageSize , String order ) {
        this.roleId = roleId;
         this.sendStartTimeInSec = sendStartTimeInSec;
         this.sendEndTimeInSec = sendEndTimeInSec;
         this.isRead = isRead;
         this.isClaimed = isClaimed;
         this.language = language;
         this.page = page;
         this.pageSize = pageSize;
         this.order = order;
         
    }


    @Override
    public String toString() {
        StringBuilder _sb_ = new StringBuilder();
        _sb_.append(this.getClass().getName());
         _sb_.append("[");
         _sb_.append(getTypeId());
         _sb_.append("]");
         
        _sb_.append("{");
        _sb_.append("roleId").append(':').append(roleId).append(',');
        _sb_.append("sendStartTimeInSec").append(':').append(sendStartTimeInSec).append(',');
        _sb_.append("sendEndTimeInSec").append(':').append(sendEndTimeInSec).append(',');
        _sb_.append("isRead").append(':').append(isRead).append(',');
        _sb_.append("isClaimed").append(':').append(isClaimed).append(',');
        _sb_.append("language").append(':').append(language).append(',');
        _sb_.append("page").append(':').append(page).append(',');
        _sb_.append("pageSize").append(':').append(pageSize).append(',');
        _sb_.append("order").append(':').append(order).append(',');

        _sb_.append("}");
        return _sb_.toString();
    }

    @Override
    public void marshal(pcore.marshal.Octets os) {
        os.writeLong(roleId);
        os.writeLong(sendStartTimeInSec);
        os.writeLong(sendEndTimeInSec);
        os.writeInt(isRead);
        os.writeInt(isClaimed);
        os.writeString(language);
        os.writeInt(page);
        os.writeInt(pageSize);
        os.writeString(order);

    }

    @Override
    public void unmarshal(pcore.marshal.Octets os) {
        roleId = os.readLong();
        sendStartTimeInSec = os.readLong();
        sendEndTimeInSec = os.readLong();
        isRead = os.readInt();
        isClaimed = os.readInt();
        language = os.readString();
        page = os.readInt();
        pageSize = os.readInt();
        order = os.readString();

    }
}
