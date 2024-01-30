package gs.sysmodule.role;



import gs.cfg.BootConfig;


public final class FRole {
    private static final int PERMANENT_AVATAR_FRAME = -1;

    public static msg.gs.login.RoleInfo toMsgRoleInfo(long roleId) {
        msg.gs.login.RoleInfo msgRoleInfo = new msg.gs.login.RoleInfo();
        toMsgRoleInfo(roleId, msgRoleInfo);
        return msgRoleInfo;
    }

    public static void toMsgRoleInfo(long roleId, msg.gs.login.RoleInfo msgRoleInfo) {
        xbean.RoleInfo role = getRoleInfo(roleId);
        msgRoleInfo.roleId = roleId;
        msgRoleInfo.userId = role.getUserId();
        msgRoleInfo.serverId = BootConfig.getIns().getServerId();
        msgRoleInfo.createTimeInMillis = role.getCreateTimeMills();
    }

    public static xbean.RoleInfo getRoleInfo(long roleId) {
        xbean.RoleInfo xRoleInfo = xtable.RoleInfos.get(roleId);  // 只能用get, 绝不能用createifnotexist
        if (xRoleInfo == null) {
            throw new RuntimeException("roleInfo not exist. roleId:" + roleId);
        }
        return xRoleInfo;
    }

    public static xbean.RoleInfo selectRoleInfo(long roleId) {
        xbean.RoleInfo xRoleInfo = xtable.RoleInfos.select(roleId);
        return xRoleInfo;
    }
}
