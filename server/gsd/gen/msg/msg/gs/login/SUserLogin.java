package msg.gs.login;

public final class SUserLogin extends pcore.io.Protocol{
    public static final int TYPE_ID = 13651;

    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }

    public int err;
    public msg.gs.login.RoleInfo roleInfo;
    public long now;

    public SUserLogin() {
         this.roleInfo = new msg.gs.login.RoleInfo();

    }

    public SUserLogin(int err , msg.gs.login.RoleInfo roleInfo , long now ) {
        this.err = err;
         this.roleInfo = roleInfo;
         this.now = now;
         
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
        _sb_.append("roleInfo").append(':').append(msg.Extensions.tostring_msg_gs_login_RoleInfo(roleInfo)).append(',');
        _sb_.append("now").append(':').append(now).append(',');

        _sb_.append("}");
        return _sb_.toString();
    }

    @Override
    public void marshal(pcore.marshal.Octets os) {
        os.writeInt(err);
        msg.Extensions.marshal_msg_gs_login_RoleInfo(roleInfo,os);
        os.writeLong(now);

    }

    @Override
    public void unmarshal(pcore.marshal.Octets os) {
        err = os.readInt();
        roleInfo = msg.Extensions.unmarshal_msg_gs_login_RoleInfo(os);
        now = os.readLong();

    }
}
