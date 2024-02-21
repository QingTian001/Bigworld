package map.mapmodule.entity;

import map.mapmodule.Vector2;
import map.mapmodule.map.GMap;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public final class Entity {
    public static final long NOT_HERO_ID = -1L;
    private final long id;

    private final Vector2 pos = new Vector2();
    private final static AtomicLong ID_GEN = new AtomicLong();

    private final GMap map;

    private final EntityAoiManager entityAoiManager = new EntityAoiManager(this);

    public Entity(GMap map, long heroUid) {
        this.map = map;
        this.id = ID_GEN.incrementAndGet();
    }

    public Entity(GMap map) {
        this(map, NOT_HERO_ID);
    }

    public long getId() {
        return id;
    }

    public GMap getMap() {
        return map;
    }


    public final Vector2 getPos() {
        return pos;
    }


}
