package map.util;

import pcore.db.Trace;
import pcore.event.Event;
import pcore.event.EventMgr;
import pcore.event.Listener;
import pcore.misc.IModule;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.Set;

public class ReflectionUtil {
    public static void registerAndStartModules(String topModule) {
        Trace.info("modules start begin");
        Set<Class<?>> classSet = PackageScanner.scan(topModule, true);
        for (Class<?> clazz : classSet) {
            if (Arrays.asList(clazz.getInterfaces()).contains(IModule.class)) {
                Object[] enumConstants = clazz.getEnumConstants();
                if (enumConstants != null && enumConstants.length == 1) {
                    IModule module = (IModule) enumConstants[0];
                    EventMgr.registerModule(module);
                    Trace.info("module [{}] start", clazz.getName());
                    module.start();
                } else {
                    Trace.warn("module:{} NOT ENUM SINGLETON", clazz.getName());
                }
            }
        }
        Trace.info("modules start end");
    }

    @SuppressWarnings("unchecked")
    public static <T> Class<T> getSuperGenericType(Class<?> clazz) {
        var superClazz = (ParameterizedType) clazz.getGenericSuperclass();
        return (Class<T>) superClazz.getActualTypeArguments()[0];
    }

    @SuppressWarnings("unchecked")
    public static <T> Class<T> getGenericInterface(Class<?> clazz) {
        var genericInterface = (ParameterizedType)(clazz.getGenericInterfaces()[0]);
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
            if (m.getParameterCount() == 1)
            {

                var eventType = m.getParameterTypes()[0];
                if (Event.class.isAssignableFrom(eventType))
                {
                    m.setAccessible(true);
                    EventMgr.register((Class<Event>)eventType, new Listener<>() {
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
