package cfg.error;
import java.util.*;

public final class EErrorCode {
    public static final int INTERNAL_EXCEPTION = -1; 
     public static final int OK = 0; 
     public static final int LOGIN_FAIL = 10; 
     

    public static final int[] enums = new int[]{ -1 ,0 ,10 };
    public static final HashSet<Integer> enumSet = new HashSet<>(Arrays.stream(enums).boxed().collect(java.util.stream.Collectors.toList()));
}