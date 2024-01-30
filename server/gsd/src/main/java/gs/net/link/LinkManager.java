package gs.net.link;

import com.google.gson.JsonObject;
import gs.Main;
import gs.cfg.BootConfig;
import gs.event.net.SessionBreak;
import gs.event.net.SessionEstablish;
import gs.net.serverlist.ServerListManager;
import gs.util.statistic.StatisticUtil;
import gs.util.timer.Timer;
import msg.Refs;
import msg.net.EErrorCode;
import msg.net.GClientAnnouceServerInfo;
import msg.net.GServerAnnouceServerInfo;
import msg.plink.*;
import pcore.collection.Int2ObjectHashMap;
import pcore.collection.LongConcurrentHashMap;
import pcore.db.Trace;
import pcore.io.*;
import pcore.marshal.Octets;
import gs.util.LogUtil;
import pcore.misc.TimeUtils;

import java.net.InetSocketAddress;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class LinkManager extends DynamicMultiClientManager {
    public static class LinkAddr {
        private static final String SPLIT_IP_PORT_STR = ":";

        public final String innerIp;
        public final int innerPort;

        public final String outerIp;
        public final String outerIpv6;
        public final int outerPort;
        public final boolean isBackup;

        public LinkAddr(String innerIp, int innerPort, String outerIp, String outerIpv6, int outerPort, boolean isBackup) {
            this.innerIp = innerIp;
            this.innerPort = innerPort;
            this.outerIp = outerIp;
            this.outerIpv6 = outerIpv6;
            this.outerPort = outerPort;
            this.isBackup = isBackup;
        }

        @Override
        public String toString()  {
            StringBuilder sb = new StringBuilder();
            sb.append("innerIp=").append(innerIp);
            sb.append(", innerPort=").append(innerPort);
            sb.append(", outerIp=").append(outerIp);
            sb.append(", outerIpv6=").append(outerIpv6);
            sb.append(", outerPort=").append(outerPort);
            sb.append(", isBackup=").append(isBackup);
            return sb.toString();
        }

        public String buildKey() {
            return makeKey(innerIp, innerPort);
        }

        public static String makeKey(String ip, int port) {
            StringBuilder builder = new StringBuilder();
            builder.append(ip);
            builder.append(SPLIT_IP_PORT_STR);
            builder.append(port);
            return builder.toString();
        }
    }


    public static class LsId {
        private final int linkServerId;
        private final long linkSid;

        public LsId(int linkServerId, long linkSid) {
            this.linkServerId = linkServerId;
            this.linkSid = linkSid;
        }

        @Override
        public int hashCode() {
            return Long.hashCode(linkSid) * 31 + Integer.hashCode(linkServerId);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj.getClass() != LsId.class) {
                return false;
            }
            LsId objLsId = (LsId)obj;

            return this.linkServerId == objLsId.linkServerId && this.linkSid == objLsId.linkSid;
        }
    }

    public static LinkManager getIns() {
        return ins;
    }

    public static void start(Conf conf) {
        if (ins == null) {
            ins = new LinkManager(conf);
            ins.open();
            ins.updateServers(conf.getServers());
        }
    }

    private static final ProtocolDispatcher dispatcher = new GProtocolDispatcher();
    private static LinkManager ins;

    // 这个是在登陆流程结束的时候才会加入此容器中, 删除是在KickUser时删除
    private final LongConcurrentHashMap<LinkUser> usersByRoleId = new LongConcurrentHashMap<LinkUser>();
    // usersByUserId和usersByLinkIdSessionId是在收到LUserOnline之后立即加入容器, 删除是在KickUser时删除
    private final LongConcurrentHashMap<LinkUser> usersByUserId = new LongConcurrentHashMap<>();

    // 网络层Session映射, 下线或者网络断开时会清除LinkUser
    private final ConcurrentHashMap<LsId, LinkUser> usersByLinkIdSessionId = new ConcurrentHashMap<>();
    private final Conf conf;

    private final Int2ObjectHashMap<IProtocolFactory> stubs = new Int2ObjectHashMap<>();


    private LinkManager(Conf conf) {
        this.conf = conf;
        dispatcher.register(GMulticast.class, this::process);
        dispatcher.register(GServerAnnouceServerInfo.class, this::process);
        dispatcher.register(LUserOnline.class, this::process);
        dispatcher.register(LLinkBroken.class, this::process);
        dispatcher.register(LForward.class, this::process);
        dispatcher.register(LAnnounceOuterNetAddress.class, this::process);
        dispatcher.register(LUserKeepAlive.class, this::process);

        stubs.putAll(Refs.gs);

        Timer.schedulePeriod(1000, conf.userExpireTime * 1000, new Timer.TimerHandler() {
            @Override
            public void onTimer() {
                LinkManager.this.checkExpireUsers();
            }
        });
    }


    public static ProtocolDispatcher getDispatcher() {
        return dispatcher;
    }

    @Override
    protected LinkClient newClient(String name, pcore.io.Client.Conf config) {
        return new LinkClient(this, name, config);
    }

    /**
     * 同一个roleId对应的旧的LinkUser执行bind也无所谓, 只要新的LinkUser执行了bind
     * 就OK
     */
    private void bindAll() {
        var it = usersByRoleId.valuesIterator();
        for (; it.hasNext(); ) {
            LinkUser linkUser = it.next();
            linkUser.bind();
        }
    }

    @Override
    protected void onAddClient(DynamicMultiClientManager.DynamicClient client) {
        super.onAddClient(client);

        var re = new GClientAnnouceServerInfo();
        re.serverId = BootConfig.getIns().getServerId();
        client.send(re);
        bindAll();
    }

    @Override
    protected void onDelClient(DynamicClient client) {
        super.onDelClient(client);

        Client.Conf conf = client.getConf();
        ServerListManager.removeLinkInfo(conf.ip, conf.port);
    }

    public synchronized void registerRole(long roleId, LinkUser user) {
        user.bind();
        user.setRoleId(roleId);
        usersByRoleId.put(roleId, user);
    }

    public static boolean send(long roleId, Octets m) {
        if (getIns() == null) {
            return false;
        }
        return getIns().doSend(roleId, m);
    }

    public static boolean send(long roleId, Protocol m) {
        if (getIns() == null) {
            return false;
        }
        return getIns().doSend(roleId, m);
    }

    public static boolean send(long roleId, byte[] m) {
        if (getIns() == null) {
            return false;
        }
        return getIns().doSend(roleId, m);
    }

    public static boolean sendDirect(long roleId, Protocol m) {
        if (getIns() == null) {
            return false;
        }
        return getIns().doSendDirect(roleId, m);
    }

    private boolean doSend(long roleId, Protocol m) {
        var user = usersByRoleId.get(roleId);
        return user != null && user.send(m);
    }

    private boolean doSend(long roleId, Octets m) {
        var user = usersByRoleId.get(roleId);
        return user != null && user.send(m);
    }

    private boolean doSend(long roleId, byte[] m) {
        var user = usersByRoleId.get(roleId);
        return user != null && user.send(m);
    }

    private boolean doSendDirect(long roleId, Protocol p) {
        var user = usersByRoleId.get(roleId);
        return user != null && user.sendDirect(p);
    }


    public void kickRole(long roleId, int reason) {
        var user = usersByRoleId.get(roleId);
        if (user != null) {
            kickUser(user, reason, true);
        }
    }

    public boolean kickUser(LinkUser user, int reason, boolean sendKick) {
        var linkServerId = user.getSession().getServerId();
        var linkSid = user.getLinkSid();
        var userId = user.getUserId();
        var roleId = user.getRoleId();
        synchronized (this) { // kickUser有可能会和LUserOnline并发, 把锁得力度搞大, 保证不会出现SessionBreak和SessionEstablish得乱序问题
            LinkUser userByUserId = usersByUserId.get(userId);
            if (userByUserId == null) {
                Trace.info("kick role failed. role is kicked already. linkServerId:{} linkSid:{} userId:{} roleId:{} reason:{}", linkServerId, linkSid, userId, roleId);
                return false;
            }

            if (user != userByUserId) {
                Trace.info("kick role failed. role is logining or relogined. linkServerId:{} linkSid:{} newLinkServerId:{} newLinkSid:{} userId:{} roleId:{} reason:{}", linkServerId, linkSid, userByUserId.getSession().getSessionId(), userByUserId.getLinkSid(), userId, roleId);
                return false;
            }
            usersByLinkIdSessionId.remove(makeLSId(linkServerId, linkSid), user);
            usersByUserId.remove(userId);
            if (roleId != 0) { // PUserLogin执行失败的情况下, roleId尚未设置, 也无需触发SessionBreak事件
                usersByRoleId.remove(roleId);
                new SessionBreak(userId, roleId, reason).trigger();
            }
        }
        if (sendKick) {
            user.sendDirect(new GKickUser(reason, linkSid));
        }

        Trace.info("kick role linkServerId:{} linkSid:{} userId:{} roleId:{} reason:{} secondTimeStamps:{}", linkServerId, linkSid, userId, roleId, reason, TimeUtils.getCachedTimeMillis());
        return true;
    }

    private void process(GMulticast msg) {
        // link -> gsd 的协议，不知道什么用
        // 不处理会报异常，所以这里处理一下
    }

    private void process(GServerAnnouceServerInfo msg) {
        LinkSession s = (LinkSession) msg.getContext();
        register(msg.serverId, (LinkClient) s.getConnection().getManager(), msg.keepAliveInterval);
    }

    private LsId makeLSId(int linkServerId, long linkSessionId) {
        return new LsId(linkServerId, linkSessionId);
    }

    private void process(LUserOnline msg) {

        var session = (LinkSession) msg.getContext();
        if (Main.isGsdShutDown()) {
            GUserOnline p  = new GUserOnline(EErrorCode.GS_SHUTDOWN, msg.linkSid);
            session.send(p);
            return;
        }

        var user = new LinkUser(session, msg.linkSid, msg.userId, msg.ip);
        var linkServerId = session.getServerId();

        if (msg.isReconnet) {
            if (!usersByUserId.containsKey(msg.userId)) { // session已经超时
                Trace.warn("user reconnect but user already expired. linkServerId:{} linkSid:{} userId:{}", linkServerId, msg.linkSid, msg.userId);
                GUserOnline p  = new GUserOnline(EErrorCode.GS_USER_EXPIRE, msg.linkSid);
                session.send(p);
                return;
            }
        }

        synchronized (this) {
            LinkUser linkUser = usersByUserId.put(msg.userId, user);
            if (linkUser != null) {
                linkUser.sendDirect(new GKickUser(EErrorCode.LOGIN_SAME_USER, linkUser.getLinkSid()));
            }
            usersByLinkIdSessionId.put(makeLSId(linkServerId, msg.linkSid), user);
            new SessionEstablish(msg, user).trigger();
        }
        Trace.info("user online linkServerId:{} linkSid:{} userId:{} isReconnect:{}", linkServerId, msg.linkSid, msg.userId, msg.isReconnet);


    }

    private void process(LLinkBroken msg) {
        var session = (Session) msg.getContext();
        var linkServerId = session.getServerId();
        var user = usersByLinkIdSessionId.get(makeLSId(linkServerId, msg.linkSid));
        if (user != null) {
            user.setLinkOnline(false);
        }
    }


    private void process(LForward p) {
        var session = (Session) p.getContext();
        var linkServerId = session.getServerId();
        var user = usersByLinkIdSessionId.get(makeLSId(linkServerId, p.linkSid));
        if (user != null) {
            try {
                var m = ProtocolUtils.decodeFromBodyBytes(p.type, p.data, stubs);
                if (m == null) {
                    kickUser(user, EErrorCode.UNKNOWN_PROTOCOL, true);
                    return;
                }

                StatisticUtil.statisticRecvProt(m, p.data.length);
                LogUtil.infoPrintLForwardProtocol(p.linkSid, user.getUserId(), user.getRoleId(), m);

                m.setContext(user);
                dispatcher.dispatch(m);
                user.refresh();
            } catch (Exception e) {
                Trace.error("lforward", e);
            }
        }
    }

    private void process(LUserKeepAlive p) {
        var session = (Session) p.getContext();
        var linkServerId = session.getServerId();
        var user = usersByLinkIdSessionId.get(makeLSId(linkServerId, p.linkSid));
        if (user != null) {
            Trace.debug("LUserKeepAlive linkSid:{} userId:{} roleId:{} secondTimeStamps:{}", p.linkSid, user.getUserId(), user.getRoleId(), TimeUtils.getCachedTimeMillis());
            user.refresh();
        }
    }

    private void process(LAnnounceOuterNetAddress p) {
        InetSocketAddress addr = ((Session)p.getContext()).getConnection().getRemoteSocketAddress();
        LinkAddr linkAddr = new LinkAddr(addr.getAddress().getHostAddress(),
                addr.getPort(), p.ip, p.ipv6, p.port, p.isBackup);
        ServerListManager.addLinkInfo(linkAddr);
    }

    private void checkExpireUsers() {
        long expireTime = pcore.misc.TimeUtils.getCachedTimeMillis() - TimeUnit.SECONDS.toMillis(conf.userExpireTime);
        for (var it = usersByLinkIdSessionId.values().iterator(); it.hasNext(); ) {
            var user = it.next();
            if (user.checkExpire(expireTime)) {
                Trace.info("== remove expire LinkUser linkSid:{} userId:{} roleId:{}", user.getLinkSid(), user.getUserId(), user.getRoleId());
                it.remove();
                kickUser(user, EErrorCode.GS_USER_EXPIRE, true);
            }
        }
    }

    public static class Conf extends DynamicMultiClientManager.Conf {
        public int userExpireTime = 300;

        public void parse(JsonObject jo) {
            super.parse(jo);
            userExpireTime = jo.get("userExpireTime").getAsInt();
        }
    }
}
