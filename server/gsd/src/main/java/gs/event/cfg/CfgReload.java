package gs.event.cfg;

import pcore.event.Event;


/**
 * Created by zyao on 2020/4/23 18:17
 */
public class CfgReload extends Event {

    public final int version;
    public final boolean strict;
    public CfgReload(int version, boolean strict) {
        this.version = version;
        this.strict = strict;
    }
}
