package map.mapmodule.entity.component;

import cfg.obj.EComponent;
import map.mapmodule.entity.Entity;
import map.util.PackageScanner;
import pcore.db.Trace;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;


public abstract class Component<C extends EComponent> {

    protected final C config;
    protected Entity host;

    public Component(Entity host,C config) {
        this.config = config;
        this.host = host;
    }

    public final C getConfig() {
        return config;
    }

    public void setHost(Entity host) {
        this.host = host;
    }

    public abstract msg.map.EntityComponentInfo toProtocol();

    /************************** factory ***********************************/
    private static final Map<Class<? extends EComponent>, BiFunction<Entity, EComponent, Component<?>>> factories = new HashMap<>();

    @SuppressWarnings("unchecked")
    public static void init() {
        for (Class<?> clazz : PackageScanner.scan("map.mapmodule.ecs.component", true)) {
            if (Component.class.isAssignableFrom(clazz) && (!Modifier.isAbstract(clazz.getModifiers()))) {
                try {
                    ParameterizedType genericSuper = (ParameterizedType) clazz.getGenericSuperclass();
                    Class<? extends EComponent> configType = (Class<? extends EComponent>) genericSuper.getActualTypeArguments()[0];
                    if (!Modifier.isAbstract(configType.getModifiers())) {
                        var constructor = (Constructor<Component<?>>) clazz.getDeclaredConstructor(Entity.class,configType);
                        factories.put(configType, (host,config) -> {
                            Component<?> ret = null;
                            try {
                                ret = constructor.newInstance(host,config);
                            } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
                                Trace.error(e);
                            }
                            return ret;
                        });
                    }
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static Component<?> createComponent(Entity host,EComponent config) {
        BiFunction<Entity, EComponent, Component<?>> factory = factories.get(config.getClass());
        if (factory != null) {
            return factory.apply(host,config);
        } else {
            return null;
        }
    }
}
