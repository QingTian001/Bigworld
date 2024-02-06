package map.mapmodule.map;

import map.mapmodule.Vector2;

public class Util {
    public static int GRID_SIZE = 32;

    public static int BLOCK_SIZE = 128;

    public static int MAP_SIZE = 1024;


    public static MapId toMapId(Vector2 vec) {
        int x = (int)vec.x / BLOCK_SIZE;
        int y = (int)vec.y / BLOCK_SIZE;

        return new MapId(x, y);
    }
}
