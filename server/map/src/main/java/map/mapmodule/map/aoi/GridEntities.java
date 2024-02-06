package map.mapmodule.map.aoi;

import map.mapmodule.entity.Entity;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class GridEntities {

    public Map<Long, Entity> entityMap = new HashMap<>();

    public void addEntity(Entity entity) {
        entityMap.put(entity.getId(), entity);
    }

    public void removeEntity(Entity entity) {
        entityMap.remove(entity.getId());
    }

    public final Collection<Entity> getEntities() {
        return Collections.unmodifiableCollection(entityMap.values());
    }
}
