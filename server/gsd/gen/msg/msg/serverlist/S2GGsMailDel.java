package msg.serverlist;

public final class S2GGsMailDel extends pcore.io.Protocol{
    public static final int TYPE_ID = 2974;

    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }

    public long roleId;
    public long gsMailId;

    public S2GGsMailDel() {
    }

    public S2GGsMailDel(long roleId , long gsMailId ) {
        this.roleId = roleId;
         this.gsMailId = gsMailId;
         
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
        _sb_.append("gsMailId").append(':').append(gsMailId).append(',');

        _sb_.append("}");
        return _sb_.toString();
    }

    @Override
    public void marshal(pcore.marshal.Octets os) {
        os.writeLong(roleId);
        os.writeLong(gsMailId);

    }

    @Override
    public void unmarshal(pcore.marshal.Octets os) {
        roleId = os.readLong();
        gsMailId = os.readLong();

    }
}
