package map.mapmodule.map;

public class MapId {

    public int x;
    public int y;

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
}
