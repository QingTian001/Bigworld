package gs.util;


import gs.net.link.LinkUser;
import pcore.io.Protocol;

public abstract class RoleProcedure<T extends Protocol> extends GProcedure {
    private LinkUser linkUser;
    private T p;

    @SuppressWarnings("unchecked")
    public void setMsg(Protocol msg) {
        this.p = (T) msg;
        this.linkUser = (LinkUser) msg.getContext();
        setDebugExceptionUser(this.linkUser);
    }

    public long getUserId() {
        return linkUser.getUserId();
    }

    protected LinkUser getLinkUser() {
        return linkUser;
    }

    protected boolean sendError(int err, String... params) {
        ProcedureUtil.sendError(linkUser, err, params);
        return false;
    }

    protected boolean sendResponse(Protocol p){
        ProcedureUtil.sendWhileSucc(linkUser, p);
        return true;
    }


    @Override
    public final boolean doProcess() {
        return doProcess(linkUser.getRoleId(), p);
    }

    public abstract boolean doProcess(long roleId, T msg);
}
