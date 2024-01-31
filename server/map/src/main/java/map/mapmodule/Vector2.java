package map.mapmodule;

/**
 * Created by zyao on 2019/11/26 20:31
 */
public final class Vector2 {

    public static final Vector2 ZERO = new Vector2();
    public final float x;
    public final float y;

    public Vector2() {
        this(0, 0);
    }

    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2(double x, double y) {
        this((float) x, (float) y);
    }

    public msg.map.Vector2 toMsg() {
        return new msg.map.Vector2(this.x, this.y);
    }

//    public static Vector2 fromCfg(cfg.map.Vector2 c) {
//        return new Vector2(c.x, c.y);
//    }

    public Vector2 add(Vector2 vec){
        return new Vector2(this.x + vec.x, this.y + vec.y);
    }

    public Vector2 subtract(Vector2 vec){
        return new Vector2(this.x - vec.x, this.y - vec.y);
    }

    public float dot(Vector2 vec) {
        return this.x * vec.x + this.y * vec.y;
    }

    public float cross(Vector2 vec) {
        return this.x * vec.y - this.y * vec.x;
    }

    public Vector2 multi(float multi) {
        return new Vector2(this.x * multi, this.y * multi);
    }

    public float distance(Vector2 vec) {
        return this.subtract(vec).length();
    }

    public float distanceSQ(Vector2 vec) {
        return this.subtract(vec).lengthSQ();
    }

    public float lengthSQ(){
        return x * x + y * y;
    }

    public float length(){
        return (float)Math.sqrt(lengthSQ());
    }

    public Vector2 normalize(){
        float length = length();
        return length > 0 ? new Vector2(this.x / length, this.y / length) : ZERO;
    }

    public Vector2 abs(){
        return new Vector2(Math.abs(x), Math.abs(y));
    }

    public void copyToProtocol(msg.map.Vector2 vec) {
        vec.x = x;
        vec.y = y;
    }

    @Override
    public int hashCode() {
        int v = Float.floatToIntBits(x);
        v = 31 * v + Float.floatToIntBits(y);
        return v;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Vector2)) {
            return false;
        }
        Vector2 v = (Vector2)obj;

        return Float.floatToIntBits(this.x) == Float.floatToIntBits(v.x) &&
                Float.floatToIntBits(this.y) == Float.floatToIntBits(v.y);
    }

    @Override
    public String toString() {
        return String.format("(%.2f, %.2f)", x, y);
    }

    //======================静态工具方法==========================

    public static Vector2 max(Vector2 a, Vector2 b){
        return new Vector2(Math.max(a.x, b.x), Math.max(a.y, b.y));
    }
}
