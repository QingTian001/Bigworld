package msg.net;
import java.util.*;

public final class EErrorCode {
    public static final int OK = 0; 
     public static final int AUTH_FAIL = 10; 
     public static final int AUTH_NET_ERROR = 11; 
     public static final int SERVER_NOT_EXIST = 20; 
     public static final int UNKNOWN_PROTOCOL = 21; 
     public static final int MARSHAL_EXCEPTION = 22; 
     public static final int PROTOCOL_EXCEPTION = 23; 
     public static final int GS_USER_EXPIRE = 24; 
     public static final int LINK_OFFLINE = 25; 
     public static final int GS_SHUTDOWN = 26; 
     public static final int LOGIN_EXCEPTION = 27; 
     public static final int LOGIN_SAME_USER = 28; 
     public static final int GS_ROLE_BANED = 29; 
     

    public static final int[] enums = new int[]{ 0 ,10 ,11 ,20 ,21 ,22 ,23 ,24 ,25 ,26 ,27 ,28 ,29 };
    public static final HashSet<Integer> enumSet = new HashSet<>(Arrays.stream(enums).boxed().collect(java.util.stream.Collectors.toList()));
}