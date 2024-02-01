package map.mapmodule.map;

public class MapId {

    public int x;
    public int y;

    public MapId (int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int hashCode() {
        return x * 31 + y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != MapId.class) {
            return false;
        }
        MapId mapId = (MapId) obj;
        return mapId.x == x && mapId.y == y;
    }

    public long toLongMapId() {
        return (long)x << 32 + y;
    }

    public static MapId toMapId(long longMapId) {
        return new MapId((int)(longMapId >> 32), (int)(longMapId & 0xffffffff));
    }
}
