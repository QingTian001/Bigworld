package map.mapmodule.map.aoi;

import map.mapmodule.map.MapId;

public class GridFake extends Grid{


    private final MapId realMapId;

    /**
     * @param realMapId 该格对应GridReal所属的MapId
     * @param mapId 该格应该所属的mapId
     * @param gridX
     * @param gridY
     */
    public GridFake(MapId realMapId, MapId mapId, int gridX, int gridY) {
        super(mapId, gridX, gridY);
        this.realMapId = realMapId;
    }

    @Override
    public boolean isRealGrid() {
        return false;
    }

    public final MapId getRealMapId() {
        return realMapId;
    }

}
