package map.mapmodule.map;

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
    private TaskQueue taskQueue;
    protected ScheduledExecutorService scheduledExecutor;
    private ScheduledFuture<?> future;
    private ScheduledFuture<?> timeOutFuture;
    private final EventManager eventManager = new EventManager();
    private final HashSet<DelayTask> delayTasks = new HashSet<>();


    public GMap() {
        id = ID_GEN.incrementAndGet();
    }

    public long getId() {
        return id;
    }

    private void scheduleUpdate(int delay) {
        if (future != null) {
            future.cancel(false);
        }
        future = scheduledExecutor.scheduleAtFixedRate(this::safeUpdate, delay, delay, TimeUnit.MILLISECONDS);
    }

    protected void update() {


    }


    public final void safeExecute(Runnable runnable) {
        taskQueue.add(runnable);
    }

    private void start() {

    }

    protected final void safeStart() {
        safeExecute(this::start);
    }

    public final void safeStop() {
        safeExecute(this::stop);
    }


    public final void stop() {

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
