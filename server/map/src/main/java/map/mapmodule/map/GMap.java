package map.mapmodule.map;

import map.mapmodule.Module;
import map.mapmodule.event.EntityListener;
import map.mapmodule.event.Event;
import map.mapmodule.event.EventManager;
import map.mapmodule.event.MapListener;
import pcore.io.Protocol;
import pcore.misc.TaskQueue;

import java.util.*;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public abstract class GMap {
    private final static int frameInterval = 40;

    protected long id;

    protected static final AtomicLong ID_GEN = new AtomicLong();
    private TaskQueue taskQueue = new TaskQueue(Module.Ins.jobExecutor);
    protected ScheduledExecutorService scheduledExecutor = Module.Ins.scheduledExecutor;
    private ScheduledFuture<?> future;
    private final EventManager eventManager = new EventManager();
    private final HashSet<DelayTask> delayTasks = new HashSet<>();

    private final MapSizeManager mapSizeManager;

    private final MapId mapId;

    public GMap(MapBuilder builder) {
        id = ID_GEN.incrementAndGet();
        this.mapId = builder.mapId;
        mapSizeManager = new MapSizeManager(mapId);
    }

    public long getInstId() {
        return id;
    }

    public MapId getMapId() {
        return mapId;
    }

    private void scheduleUpdate() {
        if (future != null) {
            future.cancel(false);
        }
        future = scheduledExecutor.scheduleAtFixedRate(this::safeUpdate, GMap.frameInterval, GMap.frameInterval, TimeUnit.MILLISECONDS);
    }

    protected void update() {


    }

    public final MapSizeManager getMapSizeManager() {
        return mapSizeManager;
    }

    public TaskQueue getTaskQueue() {
        return taskQueue;
    }

    public final void safeExecute(Runnable runnable) {
        taskQueue.add(runnable);
    }

    private void start() {
        scheduleUpdate();
    }

    protected final void safeStart() {
        safeExecute(this::start);
    }

    public final void safeStop() {
        safeExecute(this::stop);
    }


    public final void stop() {
        future.cancel(true);
    }

    private void safeUpdate() {
        taskQueue.add(this::update);
    }

    public final <T extends Event> void registerMapListener(Class<T> clazz, MapListener<T> listener) {
        eventManager.registerMapListener(clazz, listener);
    }

    public final <T extends Event> void unregisterMapListener(Class<T> clazz, MapListener<T> listener) {
        eventManager.unregisterMapListener(clazz, listener);
    }

    public final <T extends Event> void registerEntityListener(map.mapmodule.entity.Entity entity, Class<T> clazz, EntityListener<T> listener) {
        eventManager.registerEntityListener(entity, clazz, listener);
    }

    public final <T extends Event> void unregisterEntityListener(map.mapmodule.entity.Entity entity, Class<T> clazz, EntityListener<T> listener) {
        eventManager.unregisterEntityListener(entity, clazz, listener);
    }

    public final void triggerMapEvent(Event event) {
        eventManager.triggerMapEvent(event);
    }

    public final void triggerEntityEvent(map.mapmodule.entity.Entity entity, Event event) {
        eventManager.triggerEntityEvent(entity, event);
    }

    public final void addDelayTask(DelayTask task) {
        delayTasks.add(task);
    }

    public final void removeDelayTask(DelayTask task) {
        delayTasks.remove(task);
    }

    public void broadcast(Protocol p) {
    }


}
