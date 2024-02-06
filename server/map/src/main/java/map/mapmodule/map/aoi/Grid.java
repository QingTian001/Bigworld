package map.mapmodule.map.aoi;

import map.mapmodule.entity.Entity;
import map.mapmodule.map.MapId;

import java.util.*;

public abstract class Grid {


    public final int gridX;
    public final int gridY;

    public final MapId mapId;



    public Grid(MapId mapId, int gridX, int gridY) {
        this.gridX = gridX;
        this.gridY = gridY;
        this.mapId = mapId;
    }



    public abstract boolean isRealGrid();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grid grid = (Grid) o;
        return gridX == grid.gridX && gridY == grid.gridY;
    }

    @Override
    public int hashCode() {
        return Objects.hash(gridX, gridY);
    }
}
