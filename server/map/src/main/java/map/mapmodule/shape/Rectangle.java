package map.mapmodule.shape;

import map.mapmodule.Vector2;
import map.util.MathUtil;

public class Rectangle implements IShapeIntersect {
    private final Vector2 c;
    private final Vector2 h;
    private final Vector2 dir;

    public Rectangle(Vector2 c, Vector2 h, Vector2 dir){
        this.c = c;
        this.h = h;
        this.dir = dir;
    }

    @Override
    public boolean intersect(Vector2 p, float radius) {
        //算法参见：https://www.zhihu.com/question/24251545
        //更简单的算法见：https://zhuanlan.zhihu.com/p/23903445
        Vector2 newP = MathUtil.convertCoordinate(p, c, dir);
        Vector2 v = newP.abs();
        Vector2 u = Vector2.max(v.subtract(h), Vector2.ZERO);
        return u.lengthSQ() <= radius * radius;
    }

    @Override
    public String toString() {
        return String.format("Rectangle{center: %s, h: %s, dir: %s}", c, h, dir);
    }
}
