package gs.sysmodule.login;

import cfg.error.EErrorCode;
import gs.cfg.BootConfig;
import gs.event.sysmodule.RoleInitInProcedure;
import gs.event.sysmodule.RoleLoginBeforeInProcedure;
import gs.event.sysmodule.RoleLoginInProcedure;
import gs.net.link.LinkManager;
import gs.net.link.LinkUser;
import gs.sysmodule.role.FRole;
import gs.util.GProcedure;
import gs.util.ProcedureUtil;
import msg.gs.login.SUserLogin;
import msg.plink.GUserOnline;
import msg.plink.LUserOnline;
import pcore.db.PerfectDb;
import pcore.db.Trace;
import xbean.AccountUser;
import xbean.RoleInfo;

/**
 * Created by zyao on 2019/11/1 19:54
 */
public class PUserLogin extends GProcedure {

    private final LUserOnline p;
    private final LinkUser linkUser;
    private final LoginResult loginResult;

    public PUserLogin(LUserOnline p, LinkUser linkUser, LoginResult loginResult) {
        this.linkUser = linkUser;
        this.p = p;
        this.loginResult = loginResult;
    }
    @Override
    protected boolean doProcess() {
        long userId = p.userId;

        if (xtable.Users.get(userId) == null) {
            // 创建User和Role
            long roleId = PerfectDb.getInstance().nextId(xtable.RoleInfos.table());
            xtable.Users.insert(userId, new xbean.User(roleId));
            xtable.AccountUsers.insert(p.accountId, new AccountUser(p.accountId, userId, roleId));

            RoleInfo roleInfo = RoleInfo.newBean();
            roleInfo.setUserId(userId);
            roleInfo.setAccountId(p.accountId);
            xtable.RoleInfos.insert(roleId, roleInfo);

            // 其他模块各自处理角色创建
            new RoleInitInProcedure(roleId, userId, p.accountId).trigger();
            ProcedureUtil.executeWhileSucc(() -> {
            });
        }

        long roleId = xtable.Users.get(userId).getRoleId();

        GUserOnline linkMsg = new GUserOnline(EErrorCode.OK, linkUser.getLinkSid());

        ProcedureUtil.executeWhileSucc(() -> {
            linkUser.sendDirect(linkMsg);
        });

        ProcedureUtil.executeWhileSucc(() -> {
            LinkManager.getIns().registerRole(roleId, linkUser);

        });

        Trace.info("[PUserLogin] userId:{}, roleId:{}, linkSid:{}", linkUser.getUserId(), roleId, linkUser.getLinkSid());

        long curMs = System.currentTimeMillis();

        SUserLogin re = new SUserLogin();
        re.now = curMs;
        // 数据等到RoleLoginInProcedure之后再执行构建, 先把发协议先加入succtask中
        ProcedureUtil.sendWhileSucc(linkUser, re);


        RoleInfo xRoleInfo = FRole.getRoleInfo(roleId);


        // 必须在RoleLoginInProcedure事件之前调用
        ProcedureUtil.executeWhileSucc(() -> {
            Module.INS.onLogin(linkUser, p.isReconnet);
        });

        new RoleLoginBeforeInProcedure(roleId, p.isReconnet).trigger();

        new RoleLoginInProcedure(roleId, linkUser, p.isReconnet).trigger();

        // 最后构建协议
        FRole.toMsgRoleInfo(roleId, re.roleInfo);

        ProcedureUtil.executeWhileSucc(() ->{
            loginResult.success = true;
        });

        return true;
    }


}
