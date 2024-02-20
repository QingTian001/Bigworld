package map.mapmodule.map.aoi;

import map.mapmodule.entity.Entity;
import map.mapmodule.map.GMap;
import map.mapmodule.map.MapId;

import java.util.HashMap;
import java.util.Map;

public class AoiManager {
    public GMap gMap;

    private final Map<Grid, GridEntities> gridEntitiesMap = new HashMap<>();

    public AoiManager(GMap gMap) {
        this.gMap = gMap;
    }

    void init() {
        MapId mapId = this.gMap.getMapId();
    }

    public void updateEntity(Entity entity,  Grid oldGrid, Grid newGrid) {

    }
}
