package gs.util;

import com.mafia.serverex.gmbase.PackageScanner;
import pcore.db.Trace;
import pcore.event.Event;
import pcore.event.EventMgr;
import pcore.event.Listener;
import pcore.misc.IModule;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.util.*;

/**
 * Created by HuangQiang on 2019/4/15 14:26
 */

public class ReflectionUtil {

    public static class ModuleStart {
        public final MODULE_START_PRIORITY priority;
        public final Runnable r;
        public final Class<?> c;

        public ModuleStart(MODULE_START_PRIORITY priority, Class<?> c, Runnable r) {
            this.priority = priority;
            this.r = r;
            this.c = c;
        }
    }

    public static void registerAndStartModules(String topModule) throws Exception {
        Trace.info("modules start begin");
        Set<Class<?>> classSet = PackageScanner.scan(topModule, true);

        List<ModuleStart> moduleStarts = new ArrayList<>();
        for (Class<?> clazz : classSet) {
            if (Arrays.asList(clazz.getInterfaces()).contains(IModule.class)) {
                Object[] enumConstants = clazz.getEnumConstants();
                if (enumConstants != null && enumConstants.length == 1) {
                    IModule module = (IModule) enumConstants[0];
                    EventMgr.registerModule(module);

                    Method m = clazz.getMethod("start");
                    ModuleStartPriority a = m.getAnnotation(ModuleStartPriority.class);
                    ModuleStart moduleStart = new ModuleStart(a != null ? a.value() : MODULE_START_PRIORITY.MEDIUM, clazz, module::start);
                    moduleStarts.add(moduleStart);
                } else {
                    Trace.warn("module:{} NOT ENUM SINGLETON", clazz.getName());
                }
            }
        }
        moduleStarts.sort(Comparator.comparingInt(o -> o.priority.ordinal()));

        for (ModuleStart m : moduleStarts) {
            Trace.info("module [{}] start", m.c.getName());
            m.r.run();
        }
        Trace.info("modules start end");
    }

    @SuppressWarnings("unchecked")
    public static <T> Class<T> getSuperGenericType(Class<?> clazz) {
        return getSuperGenericType(clazz, 0);
    }

    @SuppressWarnings("unchecked")
    public static <T> Class<T> getSuperGenericType(Class<?> clazz, int index) {
        var superclass = clazz.getGenericSuperclass();
        if (superclass instanceof ParameterizedType) {
            var superClazz = (ParameterizedType) superclass;
            return (Class<T>) superClazz.getActualTypeArguments()[index];
        } else {
            Trace.error(String.format("%s is not instance of ParameterizedType", clazz));
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> Class<T> getGenericInterface(Class<?> clazz) {
        var genericInterface = (ParameterizedType) (clazz.getGenericInterfaces()[0]);
        return (Class<T>) genericInterface.getActualTypeArguments()[0];
    }

    public static boolean isNotAbstractSubClass(Class<?> subClass, Class<?> baseClass) {
        return baseClass.isAssignableFrom(subClass) && Modifier.isAbstract(subClass.getModifiers());
    }

    @SuppressWarnings("unchecked")
    public static void registerModule(Object module) {
        var moduleName = module.getClass().getName();
        for (var m : module.getClass().getDeclaredMethods()) {
            var methodName = m.getName();
            if (m.getParameterCount() == 1) {

                var eventType = m.getParameterTypes()[0];
                if (Event.class.isAssignableFrom(eventType)) {
                    m.setAccessible(true);
                    EventMgr.register((Class<Event>) eventType, new Listener<>() {
                        @Override
                        public void trigger(Event evt) {
                            try {
                                Trace.debug("EventMgr.trigger event:{} {}.{}", evt.getClass().getName(), moduleName, methodName);
                                m.invoke(module, evt);

                            } catch (IllegalAccessException | InvocationTargetException e) {
                                Trace.error(e);
                            }
                        }
                    });
                }
            }
        }
    }
}
