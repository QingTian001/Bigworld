package map.mapmodule.event;

import map.mapmodule.entity.Entity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class EventManager {

    private final Map<Entity, Map<Class<? extends Event>, Set<EntityListener<?>>>> entityListeners = new HashMap<>();
    private final Map<Class<? extends Event>, Set<MapListener<?>>> mapListeners = new HashMap<>();

    public <T extends Event> void registerMapListener(Class<T> clazz, MapListener<T> listener) {
        Set<MapListener<?>> listeners = mapListeners.computeIfAbsent(clazz, k -> new HashSet<>());
        listeners.add(listener);
    }

    public <T extends Event> void unregisterMapListener(Class<T> clazz, MapListener<T> listener) {
        Set<MapListener<?>> listeners = mapListeners.get(clazz);
        if (listeners != null) {
            listeners.remove(listener);
        }
    }

    public <T extends Event> void registerEntityListener(Entity entity, Class<T> clazz, EntityListener<T> listener) {
        Map<Class<? extends Event>, Set<EntityListener<?>>> listenerMap = entityListeners.computeIfAbsent(entity, k -> new HashMap<>());
        Set<EntityListener<?>> listeners = listenerMap.computeIfAbsent(clazz, k -> new HashSet<>());
        listeners.add(listener);
    }

    public <T extends Event> void unregisterEntityListener(Entity entity, Class<T> clazz, EntityListener<T> listener) {
        Map<Class<? extends Event>, Set<EntityListener<?>>> listenerMap = entityListeners.get(entity);
        if (listenerMap != null) {
            Set<EntityListener<?>> listeners = listenerMap.get(clazz);
            if (listeners != null) {
                listeners.remove(listener);
            }
        }
    }

    public void triggerMapEvent(Event event) {
        Set<MapListener<?>> listeners = mapListeners.get(event.getClass());
        if (listeners != null) {
            for (MapListener<?> listener : listeners) {
                listener.onMapEvent(event);
            }
        }
    }

    public void triggerEntityEvent(Entity entity, Event event) {
        Map<Class<? extends Event>, Set<EntityListener<?>>> listenerMap = entityListeners.get(entity);
        if (listenerMap != null) {
            Set<EntityListener<?>> listeners = listenerMap.get(event.getClass());
            if (listeners != null) {
                for (EntityListener<?> listener : listeners) {
                    listener.onEntityEvent(entity, event);
                }
            }
        }
    }

    public void onEntityDestroyed(Entity entity) {
        entityListeners.remove(entity);
    }

    public void reset(){
        entityListeners.clear();
        mapListeners.clear();
    }
}
