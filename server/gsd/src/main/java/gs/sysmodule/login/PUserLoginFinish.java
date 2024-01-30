package gs.sysmodule.login;

import gs.event.sysmodule.RoleLoginFinishInProcedure;
import gs.net.link.LinkUser;
import gs.util.GProcedure;
import gs.util.ProcedureUtil;
import msg.gs.login.SUserLoginSuccess;
import msg.plink.LUserOnline;
import pcore.db.Trace;

/**
 * Created by zyao on 2020/4/1 17:39
 */
public class PUserLoginFinish extends GProcedure {
    private final LUserOnline p;
    private final LinkUser linkUser;

    public PUserLoginFinish(LUserOnline p, LinkUser linkUser) {
        this.linkUser = linkUser;
        this.p = p;
    }
    @Override
    protected boolean doProcess() {
        long roleId = xtable.Users.get(p.userId).getRoleId();
        new RoleLoginFinishInProcedure(roleId, p.isReconnet).trigger();

        // SUserLoginSuccess要放到最后面发
        ProcedureUtil.sendWhileSucc(roleId, new SUserLoginSuccess(p.isReconnet));

        Trace.info("roleId: {} login success", roleId);
        return true;
    }
}
