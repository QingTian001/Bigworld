package map.mapmodule.map.aoi;

import map.mapmodule.map.MapId;

public class GridReal extends Grid{

    public GridReal(MapId mapId, int gridX, int gridY) {
        super(mapId, gridX, gridY);
    }

    @Override
    public boolean isRealGrid() {
        return true;
    }


}
