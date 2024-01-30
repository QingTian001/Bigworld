package msg.gsau;
import java.util.*;

public final class CheckState {
    public static final int FAIL = 0; 
     public static final int OK = 1; 
     

    public static final int[] enums = new int[]{ 0 ,1 };
    public static final HashSet<Integer> enumSet = new HashSet<>(Arrays.stream(enums).boxed().collect(java.util.stream.Collectors.toList()));
}