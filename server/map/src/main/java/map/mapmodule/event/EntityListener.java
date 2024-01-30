package map.mapmodule.event;


import map.mapmodule.ecs.Entity;

/**
 * Created by zyao on 2019/11/25 11:00
 */
public interface EntityListener<T extends Event> extends Listener<T> {

    @SuppressWarnings("unchecked")
    default void onEntityEvent(Entity entity, Event event) {
        onEvent(entity, (T) event);
    }

    void onEvent(Entity entity, T entityEvent);
}
