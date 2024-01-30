package map.util;

import it.unimi.dsi.fastutil.HashCommon;
import map.mapmodule.Vector2;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class MathUtil {

    public static int clamp(int value, int min, int max) {
        return value < min ? min : (Math.min(value, max));
    }

    public static Vector2 lerp(Vector2 a, Vector2 b, float t) {
        float x = a.x + (b.x - a.x) * t;
        float y = a.y + (b.y - a.y) * t;
        return new Vector2(x, y);
    }

    public static int nextPowerOfTwo(int num) {
        return HashCommon.nextPowerOfTwo(num);
    }

    /**
     * 求一个点在另一直角坐标系下的坐标
     * @param origin 所求点在标准直角坐标系中的坐标
     * @param center 另一坐标系的原点在标准直角坐标系中的坐标
     * @param dir 另一坐标系的y轴方向向量在标准直角坐标系下的表示
     */
    public static Vector2 convertCoordinate(Vector2 origin, Vector2 center, Vector2 dir) {
        Vector2 h = origin.subtract(center);
        Vector2 yAxis = dir.normalize();
        Vector2 xAxis = new Vector2(yAxis.y, -yAxis.x);
        float D = xAxis.x * yAxis.y - yAxis.x * xAxis.y;
        if (D == 0) {
            return origin;
        }
        float b11 = yAxis.y / D;
        float b21 = -xAxis.y / D;
        float b12 = -yAxis.x / D;
        float b22 = xAxis.x / D;
        float x = b11 * h.x + b12 * h.y;
        float y = b21 * h.x + b22 * h.y;
        return new Vector2(x, y);
    }

    /**
     * 求本地坐标系中的偏移量在世界坐标系中的坐标
     * @param origin 本地坐标系中的偏移量坐标
     * @param pos 本地坐标系原点在世界坐标系中的坐标
     * @param dir 本地坐标系y轴方向向量在世界坐标系中的表示
     * @return 世界坐标
     * https://www.cnblogs.com/softhal/p/564846
     *   float cos = yAxis.y; //负角度值不变
     *   float sin = -yAxis.x;//负角度值取反
     *   float x = origin.x * cos - origin.y * sin + center.x;
     *   float y = origin.y * cos + origin.x * sin + center.y;
     */
    public static Vector2 localToWorld(Vector2 origin, Vector2 pos, Vector2 dir) {
        Vector2 yAxis = dir.normalize();
        float cos = yAxis.y;
        float sin = yAxis.x;
        float x = origin.x * cos + origin.y * sin + pos.x;
        float y = origin.y * cos - origin.x * sin + pos.y;
        return new Vector2(x, y);
    }
}
