package map.mapmodule.map.aoi;

import map.mapmodule.map.GMap;
import map.mapmodule.map.MapId;
import map.mapmodule.map.MapSizeManager;
import map.mapmodule.map.Util;

public class Factory {

    public static Grid createGrid(GMap map, float x, float y) {

        MapSizeManager mapSizeManager = map.getMapSizeManager();

        int gridX = (int)(x / Util.GRID_SIZE);
        int gridY = (int)(y / Util.GRID_SIZE);

        if (gridX < mapSizeManager.getAoiLeftDownGridX() || gridX >= mapSizeManager.getAoiRightTopGridX()
            || gridY < mapSizeManager.getAoiLeftDownGridY() || gridY >= mapSizeManager.getAoiRightTopGridY()) {
            throw new RuntimeException();
        }

        if (gridX >= mapSizeManager.getLeftDownGridX() && gridY >= mapSizeManager.getAoiLeftDownGridY()
        && gridX < mapSizeManager.getRightTopGridX() && gridY < mapSizeManager.getRightTopGridY()) {
            return new GridReal(map.getMapId(), gridX, gridY);
        } else {
            int mapIdX = gridX < mapSizeManager.getLeftDownGridX() ? map.getMapId().x - 1 : map.getMapId().x + 1;
            int mapIDY = gridY < mapSizeManager.getLeftDownGridY() ? map.getMapId().y - 1 : map.getMapId().y + 1;

            return new GridFake(map.getMapId(), new MapId(mapIdX, mapIDY), gridX, gridY);
        }

    }
}
