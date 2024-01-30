package gs.sysmodule.login;


import com.mafia.serverex.gmbase.PackageScanner;
import com.mafia.serverex.metrics.MetricsHelper;
import gs.Main;
import gs.cfg.BootConfig;
import gs.event.net.SessionBreak;
import gs.event.net.SessionEstablish;
import gs.net.link.LinkManager;
import gs.net.link.LinkUser;
import gs.util.MathUtil;
import gs.util.ReflectionUtil;
import gs.util.RoleProcedure;
import msg.net.EErrorCode;
import msg.plink.LUserOnline;
import pcore.collection.LongConcurrentHashMap;
import pcore.db.DbExecutor;
import pcore.db.PerfectDb;
import pcore.db.Trace;
import pcore.event.EventHandler;
import pcore.io.IProcessor;
import pcore.io.Protocol;
import pcore.io.ProtocolUtils;
import pcore.marshal.Octets;
import pcore.misc.IModule;
import pcore.misc.TaskQueue;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public enum Module implements IModule {
    INS;

    private final ReentrantReadWriteLock onlineRolesLock = new ReentrantReadWriteLock();
    private final ReentrantReadWriteLock.ReadLock onlineRolesReadLock = onlineRolesLock.readLock();
    private final ReentrantReadWriteLock.WriteLock onlineRolesWriteLock = onlineRolesLock.writeLock();

    private final LongConcurrentHashMap<OnlineRole> onlineRoles = new LongConcurrentHashMap<>();
    private final LongConcurrentHashMap<OnlineRole> onlineRolesByUserId = new LongConcurrentHashMap<>();
    private final int loginTaskQueueNum = MathUtil.nextPowerOfTwo(BootConfig.getIns().getLoginTaskQueueNum());
    private final int loginTaskQueueMask = loginTaskQueueNum - 1;
    private TaskQueue[] taskQueues = new TaskQueue[loginTaskQueueNum];

    public static final String LOGIN_CHANNEL_SPLIT = "@";

    @Override
    public void start() {
        var d = LinkManager.getDispatcher();
        initTaskQueues();
        registerRoleMsgHandlers();
    }

    @EventHandler
    void onEvent(SessionBreak e) {
        TaskQueue taskQueue = getLoginTaskQueue(e.userId);
        taskQueue.add(() -> {
            logout(e.userId, false);
        });
    }

    @EventHandler
    void onEvent(SessionEstablish e) {
        LUserOnline p = e.p;
        TaskQueue taskQueue = getLoginTaskQueue(p.userId);
        taskQueue.add(() -> {
            // 在线的话, 先logout
            if (onlineRolesByUserId.containsKey(p.userId)) {
                logout(p.userId, p.isReconnet);
            }
            login(p, e.linkUser);
        });
    }


    // 全服的BeforeStop统一写在这里
    @EventHandler
    void onEvent(PerfectDb.BeforeStop e) {
        Trace.info("[Login Moudle] lock onlineRolesWriteLock");
        onlineRolesWriteLock.lock();
        try {
            Main.onBeforeStop();
            for (var it = onlineRoles.longMapIterator(); it.moveToNext(); ) {
                OnlineRole onlineRole = it.value();
                LinkManager.getIns().kickRole(onlineRole.getRoleId(), EErrorCode.GS_SHUTDOWN);
            }
        }
        finally {
            onlineRolesWriteLock.unlock();
            Trace.info("[Login Moudle] unlock onlineRolesWriteLock");
        }

    }

    @EventHandler
    void onEvent(PerfectDb.AfterStop e) {
        Main.onAfterStop();
    }

    public void broadcast(Protocol p) {
        broadcast(ProtocolUtils.encode2Octets(p));
    }

    public void broadcast(Octets data) {
        for (var it = onlineRoles.longMapIterator(); it.moveToNext(); ) {
            OnlineRole onlineRole = it.value();
            onlineRole.send(data);
        }
    }

    private void login(LUserOnline p, LinkUser linkUser) {
        onlineRolesReadLock.lock();
        try {
            if (Main.isGsdShutDown()) {
                return;
            }

            Trace.info("call PUserLogin. linkSid:{}, userId:{}", linkUser.getLinkSid(), linkUser.getUserId());
            LoginResult loginResult = new LoginResult();
            boolean ret = new PUserLogin(p, linkUser, loginResult).call();
            if (!ret) {
                LinkManager.getIns().kickUser(linkUser, EErrorCode.LOGIN_EXCEPTION, true);
                return;
            }

            if (loginResult.success) {
                Trace.info("call PUserLoginFinish. linkSid:{}, userId:{}", linkUser.getLinkSid(), linkUser.getUserId());
                new PUserLoginFinish(p, linkUser).call();
            }
        }
        finally {
            onlineRolesReadLock.unlock();
        }
    }

    private void logout(long userId, boolean isReconnect) {
        Trace.debug("logout. userId:{}, isReconnect:{}", userId, isReconnect);
        new PUserLogout(userId, isReconnect).call();
        new PUserLogoutFinish(userId, isReconnect).call();
    }

    public LongConcurrentHashMap<OnlineRole> getOnlineRoles() {
        return onlineRoles;
    }

    public boolean isOnline(long roleId) {
        return getOnlineRole(roleId) != null;
    }

    public OnlineRole getOnlineRole(long roleId) {
        return onlineRoles.get(roleId);
    }

    private void initTaskQueues() {
        for (int i = 0; i < taskQueues.length; ++i) {
            taskQueues[i] = new TaskQueue(DbExecutor.getInstance().getBlockTaskExecutor());
        }
    }

    private TaskQueue getLoginTaskQueue(long userId) {
        return taskQueues[MathUtil.hash(userId) & loginTaskQueueMask];
    }

    void onLogin(LinkUser linkUser, boolean isReconnect) {
        synchronized (this) {
            OnlineRole onlineRole = new OnlineRole(linkUser.getRoleId(), linkUser);
            onlineRoles.put(linkUser.getRoleId(), onlineRole);
            onlineRolesByUserId.put(linkUser.getUserId(), onlineRole);
        }
        MetricsHelper.getCounter("gs_online_num").set(onlineRoles.size());
    }

    @SuppressWarnings("ConstantConditions")
    void onLogout(long roleId, boolean isReconnect) {
        OnlineRole onlineRole;
        do {
            synchronized (this) {
                onlineRole = onlineRoles.remove(roleId);
                if (onlineRole == null) {
                    Trace.warn("onlineRole not exist. roleId:{}", roleId);
                    break;
                }
                onlineRolesByUserId.remove(onlineRole.getLinkUser().getUserId());
            }

            onlineRole.onLogout();
        }
        while (false);
        MetricsHelper.getCounter("gs_online_num").set(onlineRoles.size());
    }

    private static final Object[] EMPTY_OBJECT_ARRAY = new Object[0];

    @SuppressWarnings("unchecked")
    public static void registerRoleMsgHandlers() {
        var d = LinkManager.getDispatcher();
        for (Class<?> clazz : PackageScanner.scan("gs", true)) {
            if (RoleProcedure.class.isAssignableFrom(clazz) && !Modifier.isAbstract(clazz.getModifiers())) {
                Class<Protocol> msgClazz = ReflectionUtil.getSuperGenericType(clazz);
                Trace.debug("register type:{} msgtype:{}", clazz.getName(), msgClazz.getName());
                try {
                    var constructor = (Constructor<RoleProcedure>) clazz.getDeclaredConstructor();
                    d.register(msgClazz, new IProcessor<Protocol>() {
                        @Override
                        public void process(Protocol m) {
                            try {
                                var user = (LinkUser) m.getContext();
                                if (user.getRoleId() > 0) {
                                    RoleProcedure<?> p = constructor.newInstance(EMPTY_OBJECT_ARRAY);
                                    p.setMsg(m);
                                    user.execute(p);
                                }
                            } catch (Exception e) {
                                Trace.error(e);
                            }
                        }
                    });
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

