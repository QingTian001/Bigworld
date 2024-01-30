package gs.net.link;

import gs.util.LogUtil;
import gs.util.statistic.StatisticUtil;
import msg.Refs;
import msg.plink.GBind;
import msg.plink.GForward;
import msg.plink.GUnBind;
import pcore.db.Trace;
import pcore.io.Protocol;
import pcore.io.ProtocolUtils;
import pcore.marshal.Octets;
import pcore.misc.TaskQueue;
import pcore.misc.TimeUtils;
import gs.cfg.BootConfig;

import java.util.concurrent.atomic.AtomicInteger;

public final class LinkUser {


    private final static TaskQueue[] taskQueues = new TaskQueue[BootConfig.getIns().getUserTaskQueueNum()];
    private final static AtomicInteger nextTaskQueueId = new AtomicInteger();

    static {
        for (int i = 0; i < taskQueues.length; i++) {
            taskQueues[i] = new TaskQueue(pcore.db.DbExecutor.getInstance());
        }
    }

    private final LinkSession session;
    private final long linkSid;
    private final long userId;
    private final TaskQueue taskQueue;
    private volatile long roleId;
    private volatile long lastActiveTime;
    private volatile boolean linkOnline;
    private String ip;

    public LinkUser(LinkSession session, long linkSid, long userId, String ip) {
        this.session = session;
        this.linkSid = linkSid;
        this.userId = userId;
        this.taskQueue = taskQueues[nextTaskQueueId.getAndIncrement() % taskQueues.length];
        this.lastActiveTime = TimeUtils.getCachedTimeMillis();
        this.linkOnline = true;
        this.ip = ip;
        // this.taskQueue = new TaskQueue(pcore.db.Executor.getInstance());
    }

    public void setLinkOnline(boolean linkOnline) {
        this.linkOnline = linkOnline;
    }

    public LinkSession getSession() {
        return session;
    }

    public long getLinkSid() {
        return linkSid;
    }

    public long getUserId() {
        return userId;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public String getIp() {
        return this.ip;
    }

    public void refresh() {
        this.lastActiveTime = TimeUtils.getCachedTimeMillis();
    }

    public boolean checkExpire(long minLastActiveTime) {
        return lastActiveTime < minLastActiveTime;
    }

    public void execute(Runnable task) {
        taskQueue.add(task);
    }

    public boolean send(Protocol m) {
        if (!linkOnline) {
            return false;
        }

        LogUtil.infoPrintGForwardProtocol(userId, roleId, m);

        byte[] encode2Bytes = ProtocolUtils.encode2Bytes(m);
        StatisticUtil.statisticSendProt(m, encode2Bytes.length);

        var f = new GForward(getLinkSid(), encode2Bytes);
        return getSession().send(f);
    }

    public boolean send(Octets m) {
        if (!linkOnline) {
            return false;
        }
        //Trace.debug("== send roleId:{} {}", roleId, m);
        var f = new GForward(getLinkSid(), m.copyRemainData());
        return getSession().send(f);
    }

    public boolean send(byte[] m) {
        if (!linkOnline) {
            return false;
        }
        //Trace.debug("== send roleId:{} {}", roleId, m);
        var f = new GForward(getLinkSid(), m);
        return getSession().send(f);
    }

    public boolean sendDirect(Protocol m) {
        if (!linkOnline) {
            return false;
        }
        Trace.info("== sendDirect userId: {}, roleId: {} {}", userId, roleId, m);
        return getSession().send(m);
    }

    /**
     *
     * 因为按照设计, link和gs是交叉连接, 同一个link上可能会出现多个gs连接,
     * 所以不再按照一个gs只发一个Gbind的模式, 而是每一个玩家都发自己的bind
     *
      */

    public synchronized void bind() {
        GBind p = new GBind();
        p.pvid = Refs.gsProviderId;
        p.lindSids.add(this.linkSid);
        sendDirect(p);
    }

    public synchronized void unBind() {
        GUnBind p = new GUnBind();
        p.pvid = Refs.gsProviderId;
        p.lindSids.add(this.linkSid);
        sendDirect(p);
    }
}
