package map.util;

import cfg.CfgMgr;
import pcore.db.Trace;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by zyao on 2020/4/22 14:21
 */
public final class CfgReload {

    private static final CfgReload instance = new CfgReload();
    private int version;

    public static CfgReload getInstance() {
        return instance;
    }

    private final ReentrantReadWriteLock reloadLock = new ReentrantReadWriteLock();
    private final ReentrantReadWriteLock.ReadLock  readLock = reloadLock.readLock();
    private final ReentrantReadWriteLock.WriteLock writeLock = reloadLock.writeLock();

    public ReentrantReadWriteLock.ReadLock getReadLock() {
        return readLock;
    }

    public void reload(int version) {
        writeLock.lock();
        try {
            if (this.version >= version) {
                Trace.warn("version:{} lower or equal than last reload version:{}", version, this.version);
                return;
            }
            CfgMgr.load();
            AbTestState.loadCfg();
            this.version = version;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            writeLock.unlock();
        }
        Trace.info("cfg reload success. version:{}", version);
    }
}
