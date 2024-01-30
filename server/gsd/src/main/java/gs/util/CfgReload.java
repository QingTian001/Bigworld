package gs.util;

import cfg.CfgMgr;
import pcore.db.DbExecutor;
import pcore.db.Trace;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by zyao on 2020/4/22 14:21
 */
public final class CfgReload {

    private static CfgReload instance = new CfgReload();
    private int version;

    public static CfgReload getInstance() {
        return instance;
    }

    private ReentrantReadWriteLock reloadLock = new ReentrantReadWriteLock();
    private ReentrantReadWriteLock.ReadLock  readLock = reloadLock.readLock();
    private ReentrantReadWriteLock.WriteLock writeLock = reloadLock.writeLock();

    public ReentrantReadWriteLock.ReadLock getReadLock() {
        return readLock;
    }

    public synchronized void reload(int version) {
        this.reload(version, false);
    }

    public synchronized void reload(int version, boolean strict) {

        Class<?> cfgMgrCls = CfgMgr.class;
        CfgMgr cfgMgr = null;
        try {
            Constructor constructor = cfgMgrCls.getDeclaredConstructor();
            constructor.setAccessible(true);
            cfgMgr = (CfgMgr) constructor.newInstance();

        } catch (Exception e) {
            throw new RuntimeException("CfgMgr construct failed", e);
        }

        long curMs = System.currentTimeMillis();
        writeLock.lock();
        try {
            if (this.version >= version) {
                throw new RuntimeException(String.format("version:%d lower than last reload version:%d", version, this.version));
            }
            CfgMgr.ins = cfgMgr; 
            this.version = version;
            new gs.event.cfg.CfgReload(version, strict).trigger();
            // 还是在锁里执行After的事件的触发
            new gs.event.cfg.CfgReloadAfter(version, strict).trigger();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            writeLock.unlock();
        }

        Trace.info("cfg reload success. version:{}, cost nanos:{}", version, System.currentTimeMillis() - curMs);
    }
}
