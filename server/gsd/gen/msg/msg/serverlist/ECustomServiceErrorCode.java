package msg.serverlist;
import java.util.*;

public final class ECustomServiceErrorCode {
    public static final int TIMESTAMP_ERROR = 1; 
     public static final int TIMESTAMP_SMALL_THAN_NOW = 2; 
     public static final int REWARD_NOT_EXIST = 3; 
     public static final int MAIL_NOT_EXIST = 4; 
     public static final int GMMAIL_ID_DUPLICATED = 5; 
     public static final int ROLE_ID_NOT_EXIST = 6; 
     public static final int SUCCESS = 7; 
     public static final int FAIL = 8; 
     public static final int DEAFAULT_LAN_CONTENT_EMPTY = 14; 
     public static final int UNSUPPORT_ZONE = 15; 
     

    public static final int[] enums = new int[]{ 1 ,2 ,3 ,4 ,5 ,6 ,7 ,8 ,14 ,15 };
    public static final HashSet<Integer> enumSet = new HashSet<>(Arrays.stream(enums).boxed().collect(java.util.stream.Collectors.toList()));
}