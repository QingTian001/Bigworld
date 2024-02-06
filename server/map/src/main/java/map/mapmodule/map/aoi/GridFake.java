package map.mapmodule.map.aoi;

import map.mapmodule.map.MapId;

public class GridFake extends Grid{

    public GridFake(MapId mapId, int gridX, int gridY) {
        super(mapId, gridX, gridY);
    }

    @Override
    public boolean isRealGrid() {
        return false;
    }


}
