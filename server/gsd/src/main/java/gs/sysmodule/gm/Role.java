package gs.sysmodule.gm;


import com.mafia.serverex.gmbase.GmException;
import com.mafia.serverex.gmbase.GmSession;
import com.mafia.serverex.gmbase.annotation.Cmd;
import com.mafia.serverex.gmbase.annotation.Module;
import com.mafia.serverex.gmbase.annotation.Param;
import gs.cfg.BootConfig;
import gs.sysmodule.role.FRole;

@Module(comment = "角色")
public class Role {

    @Cmd(autoTransaction = false, comment = "GM模拟角色登陆", disableShowInClient = true)
    public Object roleLogin(
            @Param(name = "roleId", comment = "角色id") long roleId) {

        xbean.RoleInfo roleInfo = FRole.selectRoleInfo(roleId);
        if (roleInfo == null) {
            return gs.sysmodule.gm.Module.Ins.error("角色信息未找到. roleId = " + roleId);
        }
        gs.sysmodule.gm.Module.Ins.gmLogin(roleId);
        return "Gm成功登陆角色roleId: " + roleId;
    }

    @Cmd(autoTransaction = false, comment = "查询任意角色信息")
    public Object queryEveryRoleInfo(@Param(name = "roleId", comment = "玩家roleId") int roleId) {
        return FRole.selectRoleInfo(roleId);
    }

    @Cmd(autoTransaction = false, comment = "查询角色信息")
    public Object queryRoleInfo() {
        long roleId = GmSession.current().getRoleId();
        return FRole.selectRoleInfo(roleId);
    }

    @Cmd(autoTransaction = false, comment = "查询用户信息")
    public Object queryUserInfo() {
        long roleId = GmSession.current().getRoleId();

        long userId = FRole.selectRoleInfo(roleId).getUserId();
        return xtable.Users.select(userId);
    }

}
