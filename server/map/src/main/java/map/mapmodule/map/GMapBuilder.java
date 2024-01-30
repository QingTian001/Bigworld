package map.mapmodule.map;

import map.net.gs.GsSession;
import msg.gmap.GCreateStage;
import pcore.misc.TaskQueue;

import java.util.concurrent.ScheduledExecutorService;

public class GMapBuilder {

    public GMapBuilder(TaskQueue taskQueue, cfg.map.SceneConfig sceneCfg, ScheduledExecutorService service,
                       long mapId, GCreateStage protocol, GsSession gsSession, ScheduledExecutorService timeoutExecutor) {
        this.taskQueue = taskQueue;
        this.sceneCfg = sceneCfg;
        this.scheduledExecutor = service;
        this.timeoutExecutor = timeoutExecutor;
        this.mapId = mapId;
        this.randomSeed = protocol.randomSeed;
        this.speedGrade = protocol.speedGrade;
        this.isAuto = protocol.isAuto;
        this.protocol = protocol;
        this.gsSession = gsSession;
    }

    final TaskQueue taskQueue;
    final cfg.map.SceneConfig sceneCfg;
    final ScheduledExecutorService scheduledExecutor;
    final ScheduledExecutorService timeoutExecutor;
    public final long mapId;
    final long randomSeed;
    final int speedGrade;
    final boolean isAuto;
    public final GCreateStage protocol;
    public final GsSession gsSession;
}
