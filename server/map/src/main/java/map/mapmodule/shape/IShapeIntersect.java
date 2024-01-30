package map.mapmodule.shape;

import map.mapmodule.Vector2;

public interface IShapeIntersect {
    boolean intersect(Vector2 p, float radius);
}
