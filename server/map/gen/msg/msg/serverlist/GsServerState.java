package msg.serverlist;
import java.util.*;

public final class GsServerState {
    public static final int CLOSE = 0; 
     public static final int NEW = 1; 
     public static final int NORMAL = 2; 
     public static final int HOT = 3; 
     

    public static final int[] enums = new int[]{ 0 ,1 ,2 ,3 };
    public static final HashSet<Integer> enumSet = new HashSet<>(Arrays.stream(enums).boxed().collect(java.util.stream.Collectors.toList()));
}