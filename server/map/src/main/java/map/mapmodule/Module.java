package map.mapmodule;

import map.cfg.BootConfig;
import map.mapmodule.map.GMap;
import map.mapmodule.map.MapId;
import map.mapmodule.msg.GsMsgContext;
import map.mapmodule.msg.LinkMsgContext;
import map.mapmodule.msg.MsgUtil;
import map.mapmodule.role.LsId;
import map.mapmodule.role.Role;
import map.net.gs.GsManager;
import map.net.gs.GsSession;
import map.net.link.LinkManager;
import map.net.link.LinkSession;
import map.util.ConfigUtil;
import map.util.MathUtil;
import msg.Refs;
import msg.gmap.*;
import msg.plink.LForward;
import msg.plink.LLinkBroken;
import pcore.collection.Int2ObjectHashMap;
import pcore.collection.LongConcurrentHashMap;
import pcore.db.Trace;
import pcore.event.EventHandler;
import pcore.io.IProtocolFactory;
import pcore.io.Protocol;
import pcore.io.ProtocolUtils;
import pcore.misc.TaskQueue;

import java.util.concurrent.*;
import java.util.*;

public enum Module {
    Ins;

    private ExecutorService jobExecutor = Executors.newFixedThreadPool(Math.max(16, Runtime.getRuntime().availableProcessors() * 2));
    private final ScheduledExecutorService scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
    private final ScheduledExecutorService timeoutExecutor = Executors.newSingleThreadScheduledExecutor();
    private final LongConcurrentHashMap<GMap> maps = new LongConcurrentHashMap<>();

    private final Int2ObjectHashMap<IProtocolFactory> stubs = new Int2ObjectHashMap<>();
    private final int QUEUE_LENGTH = MathUtil.nextPowerOfTwo(Math.max(16, Runtime.getRuntime().availableProcessors() * 2));
    private final int QUEUE_MASK = QUEUE_LENGTH - 1;
    private final TaskQueue[] taskQueues = new TaskQueue[QUEUE_LENGTH];
    private final ConcurrentHashMap<LsId, Role> lsId2RoleMap = new ConcurrentHashMap<>();

    private volatile boolean gsConnected = false;
    public Map<MapId, GMap> gMaps = new ConcurrentHashMap<>();

    public volatile GsSession gsSession;

    private void initTaskQueues() {

        Trace.info("init taskQueues");
        for (int i = 0; i < taskQueues.length; ++i) {
            taskQueues[i] = new TaskQueue(jobExecutor);
        }
    }

    public void start() {
        if(BootConfig.getIns().isDebug()){
            jobExecutor = Executors.newSingleThreadExecutor();
        }
        initTaskQueues();
        stubs.putAll(Refs.mapServer);
        GsManager.getDispatcher().register(GMMessage.class, this::process);
        LinkManager.getDispatcher().register(LForward.class, this::process);
        LinkManager.getDispatcher().register(LLinkBroken.class, this::process);

        ConfigUtil.load();
        MsgUtil.init();


    }


    public final void stop() {
        scheduledExecutor.shutdown();
        jobExecutor.shutdown();
    }

    public void addGMap(MapId mapId, GMap map) {
        gMaps.put(mapId, map);
        if  (gsConnected) {
            syncMapInfo(gsSession);
        }
    }

//    public void destroyMap(long mapId) {
//        GMap map = maps.remove(mapId);
//        if (map != null) {
//            map.safeStop();
//        }
//    }
//
//    public void synDestroyMap(long mapId) {
//        GMap map = maps.remove(mapId);
//        if (map != null) {
//        }
//    }
//
//    public GMap getMap(long mapId) {
//        return maps.get(mapId);
//    }

    public final TaskQueue getTaskQueue(long mapId) {
        return taskQueues[(int)((mapId >> 16) & QUEUE_MASK)];
    }

    public final Role getRole(LinkSession linkSession, long linkSid) {
        int linkServerId = linkSession.getServerId();
        return getRole(linkServerId, linkSid);
    }

    public final Role getRole(int linkServerId, long linkSid) {
        return lsId2RoleMap.get(new LsId(linkServerId, linkSid));
    }

    public final void bindRole(int linkServerId, long linkSid, Role role) {
        lsId2RoleMap.put(new LsId(linkServerId, linkSid), role);
    }

    public final void unBindRole(int linkServerId, long linkSid) {
        lsId2RoleMap.remove(new LsId(linkServerId, linkSid));
    }

    private void process(GMMessage p) {
        long mapId = p.mapId;
        Protocol msg = ProtocolUtils.decodeFromBytes(p.data, stubs);
        msg.setContext(new GsMsgContext(mapId, (GsSession) p.getContext()));
        GsManager.getDispatcher().dispatch(msg);
    }

    private void process(LLinkBroken p) {
        LinkSession linkSession = (LinkSession)p.getContext();
        Role role = getRole(linkSession, p.linkSid);
        if (role != null) {
            role.setLinkOnline(false);
        }
    }

    private void process(LForward p) {
        long linkSid = p.linkSid;
        Protocol msg = ProtocolUtils.decodeFromBodyBytes(p.type, p.data, stubs);
        msg.setContext(new LinkMsgContext(linkSid, (LinkSession)p.getContext()));
        LinkManager.getDispatcher().dispatch(msg);
    }

    public final boolean sendToGs(GsSession gsSession, long mapId, Protocol p) {
        //Trace.debug("[sendToGs] {} : {}", p.getClass(), p);
        return gsSession.send(new MGMessage(mapId, ProtocolUtils.encode2Bytes(p)));
    }

    @EventHandler
    public void onGsConnected(GsSession gsSession) {
        this.gsConnected = true;
        this.gsSession = gsSession;
        syncMapInfo(gsSession);
    }

    public void syncMapInfo(GsSession gsSession) {
        MGMapInfos p = new MGMapInfos();
        for (MapId mapId : this.gMaps.keySet()) {
            p.mapInfos.add(new MapInfo(mapId.x, mapId.y));
        }
        gsSession.send(p);

    }

}