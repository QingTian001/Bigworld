package map.mapmodule.shape;

import map.mapmodule.Vector2;

public class Circle implements IShapeIntersect {
    private final Vector2 c;
    private final float r;

    public Circle(Vector2 c, float r){
        this.c = c;
        this.r = r;
    }

    @Override
    public boolean intersect(Vector2 p, float radius) {
        return p.distanceSQ(c) <= (r + radius) * (r + radius);
    }
}
