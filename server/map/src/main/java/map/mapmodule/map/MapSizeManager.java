package map.mapmodule.map;

public class MapSizeManager {

    private final int leftDownX;
    private final int leftDownY;


    public MapSizeManager(MapId mapId) {
        leftDownX = mapId.x * 128;
        leftDownY = mapId.y * 128;
    }

    public int getLeftDownY() {
        return leftDownY;
    }

    public int getLeftDownX() {
        return leftDownX;
    }

    public int getRightTopX() {
        return leftDownX + Util.BLOCK_SIZE;
    }

    public int getRightTopY() {
        return leftDownY + Util.BLOCK_SIZE;
    }

    public int getAoiLeftDownX() {
        if (leftDownX == 0) {
            return leftDownX;
        } else {
            return leftDownX - Util.GRID_SIZE;
        }
    }

    public int getAoiLeftDownY() {
        if (leftDownY == 0) {
            return leftDownY;
        } else {
            return leftDownY - Util.GRID_SIZE;
        }
    }

    public int getAoiRightTopX() {
        if (getRightTopX() == Util.MAP_SIZE) {
            return getRightTopX();
        } else {
            return getRightTopX() + Util.GRID_SIZE;
        }
    }

    public int getAoiRightTopY() {
        if (getRightTopY() == Util.MAP_SIZE) {
            return getRightTopY();
        } else {
            return getRightTopY() + Util.GRID_SIZE;
        }
    }

    public boolean isAoiLeftMore() {
        return leftDownX != 0;
    }

    public boolean isAoiRightMore() {
        return getRightTopX() != Util.MAP_SIZE;
    }

    public boolean isAoiDownMore() {
        return leftDownY != 0;
    }

    public boolean isAoiUpMore() {
        return getRightTopY() != Util.MAP_SIZE;
    }

}
