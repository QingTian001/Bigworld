package gs.event.cfg;

import pcore.event.Event;


/**
 * Created by zyao on 2020/6/12 18:54
 */
public class CfgReloadAfter extends Event {

    public final int version;
    public final boolean strict;
    public CfgReloadAfter(int version, boolean strict) {
        this.version = version;
        this.strict = strict;
    }
}
