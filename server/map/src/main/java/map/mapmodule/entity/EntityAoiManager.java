package map.mapmodule.entity;

import map.mapmodule.map.GMap;
import map.mapmodule.map.MapSizeManager;
import map.mapmodule.map.aoi.Factory;
import map.mapmodule.map.aoi.Grid;

public class EntityAoiManager {

    private Grid currentGrid;

    private Entity entity;

    public EntityAoiManager(Entity entity) {
        this.entity = entity;
    }

    public void updateAoi() {
        GMap map = entity.getMap();
        MapSizeManager mapSizeManager = map.getMapSizeManager();
        if (entity.getPos().x >= mapSizeManager.getAoiLeftDownGridX() && entity.getPos().x < mapSizeManager.getAoiRightTopGridX()
                && entity.getPos().y >= mapSizeManager.getAoiLeftDownY() && entity.getPos().y < mapSizeManager.getAoiRightTopY()) {

            Grid grid = Factory.createGrid(map, entity.getPos().x, entity.getPos().y);

            if (grid.equals(currentGrid)) { // 没移动格子
                return;
            }

            // currentGrid == null时
            map.getAoiManager().updateEntity(entity, currentGrid, grid);
        } else {

            if (currentGrid == null) {
                throw  new RuntimeException();
            }

            // TODO transfer去其他服务器或者本服的Gmap
        }
    }
}
