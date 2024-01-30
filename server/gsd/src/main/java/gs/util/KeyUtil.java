package gs.util;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by zhanghuaizheng on 2020/2/14 15:17
 */
public class KeyUtil {
    private static char[] defaultDic = new char[62];
    static{
        for (int i = 0; i < 10; i++) {
            defaultDic[i] = (char) ('0' + i);
        }
        for (int i = 0; i < 26; i++) {
            defaultDic[i + 10] = (char) ('A' + i);
        }
        for (int i = 0; i < 26; i++) {
            defaultDic[i + 36] = (char) ('a' + i);
        }
    }

    public static String genKey(int size){
        StringBuilder sb = new StringBuilder();
        int i = 0;
        Random r  =  ThreadLocalRandom.current();
        while (i++ < size) {
            sb.append(defaultDic[r.nextInt(defaultDic.length)]);
        }
        return sb.toString();
    }
}
