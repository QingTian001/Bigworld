package gs.sysmodule.login;

import gs.event.sysmodule.RoleLogoutInProcedure;
import gs.sysmodule.role.FRole;
import gs.util.GProcedure;
import gs.util.ProcedureUtil;
import pcore.misc.TimeUtils;
import xbean.RoleInfo;

/**
 * Created by zyao on 2020/2/12 11:33
 */
public class PUserLogout extends GProcedure {

    private final long userId;
    private final boolean isReconnect;
    public PUserLogout(long userId, boolean isReconnect) {
        this.userId = userId;
        this.isReconnect = isReconnect;
    }
    @Override
    protected boolean doProcess() {
        long roleId = xtable.Users.get(userId).getRoleId();


        new RoleLogoutInProcedure(roleId, isReconnect).trigger();
        return true;
    }
}
