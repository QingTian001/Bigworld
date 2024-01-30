package map.util;

import map.mapmodule.Vector2;

public class ConvertUtil {
    public static void copy(Vector2 from, msg.map.Vector2 to) {
        to.x = (int)(from.x * 100);
        to.y = (int)(from.y * 100);
    }
}
