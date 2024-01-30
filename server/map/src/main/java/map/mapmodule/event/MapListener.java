package map.mapmodule.event;

/**
 * Created by zyao on 2019/11/25 11:10
 */
public interface MapListener<T extends Event> extends Listener<T> {

    void onEvent(T mapEvent);

    @SuppressWarnings("unchecked")
    default void onMapEvent(Event e) {
        onEvent((T) e);
    }
}
