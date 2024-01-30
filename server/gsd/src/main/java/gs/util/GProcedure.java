package gs.util;

import cfg.CfgMgr;
import com.mafia.serverex.metrics.MetricsHelper;
import gs.cfg.BootConfig;
import gs.cfg.GameConfigValidator;
import gs.net.link.LinkUser;
import gs.sysmodule.login.Module;
import gs.sysmodule.login.OnlineRole;
import gs.util.statistic.StatisticUtil;
import org.apache.log4j.PropertyConfigurator;
import pcore.collection.Int2ObjectHashMap;
import pcore.db.PerfectDb;
import pcore.db.Procedure;
import pcore.db.Trace;
import pcore.db.Transaction;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by zyao on 2020/2/21 10:29
 */
public abstract class GProcedure extends Procedure {

    private static class SyncTask {
        private final Runnable task;
        private final boolean whenSucc;

        public SyncTask(Runnable task, boolean whenSucc) {
            this.task = task;
            this.whenSucc = whenSucc;
        }
    }

    private static AtomicLong totalCallNum = new AtomicLong();
    private static AtomicLong totalExecuteNum = new AtomicLong();

    private static ThreadLocal<Stack<GProcedure>> currentProcedure = ThreadLocal.withInitial(Stack::new);
    private Map<String, Object> cacheObjs = null;
    private final List<SyncTask> syncTasks = new ArrayList<>();

    /******************** for debug *************************/
    private LinkUser debugExceptionUser; // 通过构造函数传入有点意义不明确, 改成接口传入
    private Throwable t;
    /********************************************************/

    public GProcedure() {
    }

    @Override
    public final boolean process() {
        // 忽略中间的Throwable，只发送最后一次
        this.t = null;
        try {
            return doProcess();
        }
        catch (Throwable throwable) {
            this.t = throwable;
            throw throwable;
        }
    }

    protected abstract boolean doProcess();

    @Override
    public void run() {
        super.run();
    }
    @Override
    protected void onPrepare() {
        if (cacheObjs != null) {
            cacheObjs.clear();
        }
    }

    public final GProcedure setDebugExceptionRoleId(long roleId) {
        OnlineRole onlineRole = Module.INS.getOnlineRole(roleId);
        if (onlineRole != null) {
            debugExceptionUser = onlineRole.getLinkUser();
        }
        return this;
    }

    public final GProcedure setDebugExceptionUser(LinkUser linkUser) {
        debugExceptionUser = linkUser;
        return this;
    }

    @Override
    public boolean call() {
        totalCallNum.incrementAndGet();
        long startNanos = System.nanoTime();
        var timer = MetricsHelper.getTimerWithLabel("gs_procedure_duration");
        timer.startWithLabel(this.getClass().getName());
        Stack<GProcedure> procedureStack = currentProcedure.get();
        procedureStack.push(this);
        ReentrantReadWriteLock.ReadLock cfgReloadReadLock = CfgReload.getInstance().getReadLock();
        cfgReloadReadLock.lock();
        boolean ret = false;
        try {
            try {
                ret = super.call();
            } catch (Throwable e) {
                Trace.error("[GProcedure] throws exception", e);
                ret = false;
            } finally {
                procedureStack.pop();
                timer.endWithLabel(this.getClass().getName());
                StatisticUtil.statistic(this, System.nanoTime() - startNanos);
            }
        }
        finally {
            cfgReloadReadLock.unlock();
            if (BootConfig.getIns().isDebug() && t != null && debugExceptionUser != null) {
                try {
                    try (ByteArrayOutputStream baos = new ByteArrayOutputStream(512)) {
                        PrintStream ps = new PrintStream(baos, true, StandardCharsets.UTF_8);
                        t.printStackTrace(ps);
                        //debugExceptionUser.send(new SDebugException(t.getMessage() == null ? "null" : t.getMessage(), new String(baos.toByteArray(), StandardCharsets.UTF_8)));
                    }
                } catch (IOException e) {
                    Trace.error(e);
                }
            }
        }

        if (procedureStack.size() == 0) {
            runSyncTasks(ret);
        }
        else {
            GProcedure outerProcedure = procedureStack.peek();
            if (outerProcedure == null) {
                throw new RuntimeException("outerProcedure is null.");
            }
            if (ret) {
                outerProcedure.syncTasks.addAll(this.syncTasks);
            }
            else {
                runSyncTasks(false);
            }
        }

        return ret;
    }

    private void runSyncTasks(boolean ret) {
        for (SyncTask syncTask : syncTasks) {
            if (syncTask.whenSucc == ret) {
                try {
                    syncTask.task.run();
                }
                catch (Exception ex) {
                    Trace.error("syncTask run failed", ex);
                }
            }
        }
    }

    public static GProcedure current() {
        Stack<GProcedure> s = currentProcedure.get();
        return s.size() == 0 ? null : s.peek();
    }

    @Override
    public void execute() {
        totalExecuteNum.incrementAndGet();
        super.execute();
    }

    private Map<String, Object> getCache() {
        if (cacheObjs == null) {
            cacheObjs = new HashMap<>();
        }
        return cacheObjs;
    }

    public final <T> void putCacheObj(String k, T obj) {
        if (Transaction.get() == null) {
            throw new RuntimeException("not in Transaction");
        }
        getCache().put(k, obj);
    }

    @SuppressWarnings("unchecked")
    public final <T> T getCacheObj(String k) {
        if (Transaction.get() == null) {
            throw new RuntimeException("not in Transaction");
        }
        return (T)getCache().get(k);
    }

    private void addSyncTask(Runnable task, boolean whenSucc) {
        if (Transaction.get() == null) {
            throw new RuntimeException("not in Transaction");
        }

        // 这里虽然按照最外层事务成功实现来实现, 但是因为syncTask大概率情况
        // 是希望在当前事务成功的时候执行, 所以暂时先禁止syncTask在有外层事务的
        // 情况下使用
//        if (currentProcedure.get().size() > 1) {
//            throw new RuntimeException("must addSyncTask not in nest procedure");
//        }
        syncTasks.add(new SyncTask(task, whenSucc));
    }

    public final void addSuccSyncTask(Runnable task) {
        addSyncTask(task, true);
    }

    public final void addFailSyncTask(Runnable task) {
        addSyncTask(task, false);
    }

    public static void main(String[] args) throws Exception {
        var conf = BootConfig.getIns();
        conf.load("gs.json");
        PropertyConfigurator.configure("log4j.properties");
        PerfectDb.getInstance().start(conf.getDbConf());

//        new GProcedure() {
//            @Override
//            protected boolean doProcess() {

                new GProcedure() {
                    @Override
                    protected boolean doProcess() {
                        ProcedureUtil.executeWhileSucc(() -> {
                            System.out.println("call procedure");
                        });

                        ProcedureUtil.executeAfterSucc(() -> {
                            System.out.println("inner succ");

                            new GProcedure() {
                                @Override
                                protected boolean doProcess() {
                                    System.out.println("call inner succ procedure");


                                    ProcedureUtil.executeAfterSucc(() -> {
                                        System.out.println("inner inner success");

                                        new GProcedure() {
                                            @Override
                                            protected boolean doProcess() {
                                                ProcedureUtil.executeWhileSucc(() -> {
                                                    System.out.println("call inner inner procedure");
                                                });
                                                new GProcedure() {
                                                    @Override
                                                    protected boolean doProcess() {
                                                        ProcedureUtil.executeWhileSucc(() -> {
                                                            System.out.println("call new inner inner inner procedure");
                                                        });

                                                        return true;
                                                    }
                                                }.call();

                                                ProcedureUtil.executeWhileSucc(() -> {
                                                    System.out.println("call inner inner procedure finish");
                                                });
                                                return true;
                                            }
                                        }.call();
                                    });
                                    ProcedureUtil.executeWhileSucc(() -> {
                                        System.out.println("call inner succ procedure finish");
                                    });
                                    return true;
                                }
                            }.call();
                        });


                        ProcedureUtil.executeWhileSucc(() -> {
                            System.out.println("call procedure finish");
                        });
                        return true;
                    }
                }.call();
//                return true;
//            }
//        }.call();
    }

}
