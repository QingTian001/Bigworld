package gs.sysmodule.combine;

import com.mafia.serverex.gmbase.PackageScanner;
import gs.util.ReflectionUtil;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zyao on 2020/12/21 18:31
 */
@SuppressWarnings("unchecked")
public final class CombineUtil {

    public static Map<Class<? extends CombineData>, Class<? extends CombineExecute>> executeFactoryMap = new HashMap<>();


    static {
        for (Class<?> clazz : PackageScanner.scan("gs", true)) {
            if (!CombineExecute.class.isAssignableFrom(clazz) || CombineExecute.class == clazz) {
                continue;
            }

            Class<? extends CombineData> dataClz = ReflectionUtil.getSuperGenericType(clazz, 1);
            executeFactoryMap.put(dataClz, (Class<? extends CombineExecute>) clazz);
        }
    }

    public static <T extends CombineExecute> T createCombineExecute(Class<? extends CombineData> clazz) {
        try {
            Constructor<T> constructor =  (Constructor<T>)executeFactoryMap.get(clazz).getDeclaredConstructor();
            constructor.setAccessible(true);
            return constructor.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
