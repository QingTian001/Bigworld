package map.mapmodule.msg;

import pcore.io.Protocol;

/**
 * Created by zyao on 2020/3/28 9:53
 */
public abstract class MsgHandler<T extends Protocol> implements Runnable {
    protected T p;
}
