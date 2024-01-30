package gs.sysmodule.login;

import gs.event.sysmodule.RoleLogoutFinishInProcedure;
import gs.util.GProcedure;
import gs.util.ProcedureUtil;

/**
 * Created by zyao on 2020/4/1 17:40
 */
public class PUserLogoutFinish extends GProcedure {
    private final long userId;
    private final boolean isReconnect;
    public PUserLogoutFinish(long userId, boolean isReconnect) {
        this.userId = userId;
        this.isReconnect = isReconnect;
    }
    @Override
    protected boolean doProcess() {
        long roleId = xtable.Users.get(userId).getRoleId();
        new RoleLogoutFinishInProcedure(roleId, isReconnect).trigger();
        ProcedureUtil.executeWhileSucc(() -> Module.INS.onLogout(roleId, isReconnect));
        return true;
    }
}
