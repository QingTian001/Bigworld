package map.mapmodule.ecs;

import cfg.obj.*;
import map.mapmodule.ecs.component.Component;
import map.mapmodule.map.GMap;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public final class Entity {
    public static final long NOT_HERO_ID = -1L;
    private final long id;
    private final static AtomicLong ID_GEN = new AtomicLong();

    private final cfg.obj.Entity config;
    private final GMap map;
    private final Map<Class<? extends Component>, Component<?>> allComponents = new HashMap<>();


    public Entity(GMap map, cfg.obj.Entity config, long heroUid) {
        this.map = map;
        this.config = config;
        this.id = ID_GEN.incrementAndGet();
    }

    public Entity(GMap map, cfg.obj.Entity config) {
        this(map, config, NOT_HERO_ID);
    }

    public long getId() {
        return id;
    }

    public GMap getMap() {
        return map;
    }

    public cfg.obj.Entity getConfig() {
        return config;
    }



}
