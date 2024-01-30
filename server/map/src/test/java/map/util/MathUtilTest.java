package map.util;

import map.mapmodule.Vector2;
import org.junit.Test;

public class MathUtilTest {
    @Test
    public void convertCoordinate2() {
        Vector2 pos = new Vector2(0f,-2f);
        Vector2 dir = new Vector2(0.8,0.5);
        var newPos = MathUtil.localToWorld(pos,Vector2.ZERO,dir);
        System.out.println(String.format("%s %s -> %s",dir,pos,newPos));

        dir = new Vector2(-0.8,0.5);
        newPos = MathUtil.localToWorld(pos,Vector2.ZERO,dir);
        System.out.println(String.format("%s %s -> %s",dir,pos,newPos));

        dir = new Vector2(-0.8,-0.5);
        newPos = MathUtil.localToWorld(pos,Vector2.ZERO,dir);
        System.out.println(String.format("%s %s -> %s",dir,pos,newPos));

        dir = new Vector2(0.8,-0.5);
        newPos = MathUtil.localToWorld(pos,Vector2.ZERO,dir);
        System.out.println(String.format("%s %s -> %s",dir,pos,newPos));
    }
}
