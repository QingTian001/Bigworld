package gs.sysmodule.login;

import cfg.CfgMgr;
import gs.net.link.LinkUser;
import gs.util.timer.RoleTimer;
import pcore.db.Trace;
import pcore.io.Protocol;
import pcore.marshal.Octets;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by zyao on 2019/4/14 17:25
 */
public class OnlineRole {

    private final Map<RoleTimer.RoleTimerHandler, RoleTimer> roleTimers = new HashMap<>();
    private final LinkUser linkUser;
    private final long roleId;
    public volatile String deviceId;//设备id
    public volatile String platform;//平台 ios android
    public volatile String version;//游戏版本
    public volatile String sysversion;// 系统版本
    public volatile String deviceType;//设备类型
    public volatile String carrier;// 运营商
    public volatile int language;
    public volatile long loginTimestamp;

    OnlineRole(long roleId, LinkUser linkUser) {
        this.roleId = roleId;
        this.linkUser = linkUser;
    }

    public final long getRoleId() {
        return roleId;
    }

    public final long getUserId() {
        return linkUser.getUserId();
    }

    public final LinkUser getLinkUser() {
        return linkUser;
    }

    public final void send(Protocol p) {
        linkUser.send(p);
    }

    public final void send(Octets o) {
        linkUser.send(o);
    }

    public final void send(byte[] bytes) {
        linkUser.send(bytes);
    }

    public final void addTimer(RoleTimer timer) {
        Trace.info("OnlineRole.addTimer. roleId:{}, timerHandler:{}", timer.getRoleId(), timer.getHandler().getClass().getName());
        synchronized (roleTimers) {

            RoleTimer oldRoleTimer = roleTimers.put(timer.getHandler(), timer);
            if (oldRoleTimer != null) {
                oldRoleTimer.cancel();
            }
        }
    }

    public final RoleTimer removeTimer(RoleTimer timer) {
        return removeTimer(timer.getHandler());
    }

    public final RoleTimer removeTimer(RoleTimer.RoleTimerHandler handler) {
        Trace.info("OnlineRole.removeTimer. roleId:{}, timerHandler:{}", getRoleId(), handler.getClass().getName());
        synchronized (roleTimers) {
            return roleTimers.remove(handler);
        }
    }

    public final void cancelTimer(RoleTimer timer) {
        cancelTimer(timer.getHandler());
    }

    public final void cancelTimer(RoleTimer.RoleTimerHandler handler) {
        Trace.info("OnlineRole.cancelTimer. roleId:{}, timerHandler:{}", getRoleId(), handler.getClass().getName());
        synchronized (roleTimers) {
            RoleTimer RoleTimer = roleTimers.get(handler);
            if (RoleTimer != null) {
                RoleTimer.cancel();
            }
        }
    }

    public final RoleTimer cancelAndRemoveTimer(RoleTimer timer) {
        return cancelAndRemoveTimer(timer.getHandler());
    }

    public final RoleTimer cancelAndRemoveTimer(RoleTimer.RoleTimerHandler handler) {
        Trace.info("OnlineRole.cancelAndRemoveTimer. roleId:{}, timerHandler:{}", getRoleId(), handler.getClass().getName());
        synchronized (roleTimers) {
            RoleTimer RoleTimer = roleTimers.remove(handler);
            if (RoleTimer == null) {
                return null;
            }
            RoleTimer.cancel();
            return RoleTimer;
        }
    }

    public final RoleTimer getTimer(RoleTimer.RoleTimerHandler handler) {
        synchronized (roleTimers) {
            return roleTimers.get(handler);
        }
    }

    void onLogout() {
        synchronized (roleTimers) {
            for (RoleTimer roleTimer : roleTimers.values()) {
                roleTimer.cancel();
            }
            roleTimers.clear();
        }
    }

}
