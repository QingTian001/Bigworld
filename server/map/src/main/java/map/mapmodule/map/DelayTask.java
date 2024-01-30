package map.mapmodule.map;

import map.mapmodule.ecs.Entity;

/**
 * Created by zyao on 2019/12/6 19:17
 */
public class DelayTask {

    private final Entity owner;
    private final long taskExecuteMills;
    private final Runnable task;

    public DelayTask(Entity owner, long taskExecuteMills, Runnable task) {
        this.taskExecuteMills = taskExecuteMills;
        this.task = task;
        this.owner = owner;
    }

    public final long getTaskExecuteMills() {
        return taskExecuteMills;
    }

    public final Runnable getTask() {
        return task;
    }

    public final Entity getOwner() {
        return owner;
    }
}
