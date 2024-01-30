package gs.util;

import cfg.error.EErrorCode;
import gs.cfg.BootConfig;
import gs.net.link.LinkManager;
import gs.net.link.LinkUser;
import gs.sysmodule.combine.CombineData;
import gs.sysmodule.combine.CombineExecute;
import gs.sysmodule.combine.CombineKey;
import gs.sysmodule.combine.CombineUtil;
import gs.sysmodule.login.Module;
import msg.gs.SError;
import pcore.db.Procedure;
import pcore.db.Trace;
import pcore.db.Transaction;
import pcore.io.Protocol;
import pcore.misc.RefInt;
import xbean.XLong;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;


public class ProcedureUtil {

    private static final String COMBINE_EXECUTE_KEY = "COMBINE_EXECUTE_KEY";

    public static XLong newXLong(long value) {
        var x = xbean.XLong.newBean();
        x.setValue(value);
        return x;
    }

    public static boolean sendError(long roleId, int err, String... params) {
        addTransactionFailTask(() -> sendDirectly(roleId, new SError(err, Arrays.asList(params))));
        return false;
    }

    public static boolean sendError(LinkUser user, int err, String... params) {
        addTransactionFailTask(() -> user.send(new SError(err, Arrays.asList(params))));
        return false;
    }

    public static boolean sendTipsWhileSucc(LinkUser user, int err, String... params) {
        addTransactionSuccTask(() -> user.send(new SError(err, Arrays.asList(params))));
        return true;
    }

    /**
     * 直接发送协议
     * 注意：只允许在事务外和事务成功或者失败任务中调用
     *
     * @param roleId
     * @param p
     */
    public static void sendDirectly(long roleId, Protocol p) {
        LinkManager.send(roleId, p);
    }

    public static int checkWithNoCost(Supplier<Integer> checkFunc) {
        RefInt innerErr = new RefInt();
        innerErr.value = EErrorCode.INTERNAL_EXCEPTION;

        if (new GProcedure() {
            @Override
            public boolean doProcess() {
                innerErr.value = checkFunc.get();
                return false;
            }
        }.call()) {
            throw new RuntimeException("impossible");
        }

        if (innerErr.value == EErrorCode.INTERNAL_EXCEPTION) {
            throw new RuntimeException("exception happends in inner procedure");
        }

        return innerErr.value;
    }

    /**
     * 事务外方法
     *
     */
    public static void callProcedureFailThrowsException(Runnable run) {
        if (!new GProcedure() {
            @Override
            public boolean doProcess() {
                run.run();
                return true;
            }
        }.call()) {
            throw new RuntimeException("call procedure failed");
        }
    }

    public static void sendWhileSucc(long roleId, Protocol p) {
        addTransactionSuccTask(() -> LinkManager.send(roleId, p));
    }


    public static void sendWhileFail(long roleId, Protocol p) {
        addTransactionFailTask(() -> LinkManager.send(roleId, p));
    }


    public static void sendWhileSucc(LinkUser user, Protocol p) {
        addTransactionSuccTask(() -> user.send(p));
    }

    public static void triggerAsyncWhileSucc(pcore.event.Event e) {
        addTransactionSuccTask(() -> {
            ExecutorUtil.execute(e::trigger);
        });
    }

    public static void triggerAsyncWhileSuccByRole(long roleId, pcore.event.Event e) {
        addTransactionSuccTask(() -> {
            ExecutorUtil.execute(roleId, e::trigger);
        });
    }



    public static void triggerAsyncWhileSuccByGlobalStatisticsId(int id, pcore.event.Event e) {
        addTransactionSuccTask(() -> {
            ExecutorUtil.executeGlobalStatisticsTaskById(id, e::trigger);
        });
    }

    public static void executeAsyncWhileSuccByRole(long roleId, Runnable task) {
        addTransactionSuccTask(() -> {
                ExecutorUtil.execute(roleId, task);
            }
        );
    }

    public static void executeAsyncWhileFailByRole(long roleId, Runnable task) {
        addTransactionFailTask(() -> {
                    ExecutorUtil.execute(roleId, task);
                }
        );
    }


    public static void executeWhileSucc(Runnable task) {
        if (task instanceof Procedure) {
            throw new RuntimeException("Cant sync execute Procedure in success tasks");
        }
        addTransactionSuccTask(task);
    }

    /**
     * 不支持嵌套事务, 除非有需求在有执行事务的同一个线程执行另外一个事务call的时候才可使用
     * @param task
     */
    public static void  executeAfterSucc(Runnable task) {
        Objects.requireNonNull(GProcedure.current()).addSuccSyncTask(task);
    }

    //异步执行任务
    public static void executeAsyncWhileSucc(Runnable task) {
        addTransactionSuccTask(() -> ExecutorUtil.execute(task));
    }

    public static void executeWhileFail(Runnable task) {
        if (task instanceof Procedure) {
            throw new RuntimeException("Cant sync execute Procedure in success tasks");
        }
        addTransactionFailTask(task);
    }

    public static void executeAfterFail(Runnable task) {
        Objects.requireNonNull(GProcedure.current()).addFailSyncTask(task);
    }

    public static void executeAsyncWhileFail(Runnable task) {
        addTransactionFailTask(() -> ExecutorUtil.execute(task));
    }

    public static <K, T extends CombineData> void executeCombineWhileSucc(K key, T combineData) {
        GProcedure current = GProcedure.current();
        Map<CombineKey, CombineExecute<K, T>> combineDatas = current.getCacheObj(COMBINE_EXECUTE_KEY);
        if (combineDatas == null) {
            combineDatas = new HashMap<>();
            current.putCacheObj(COMBINE_EXECUTE_KEY, combineDatas);
        }

        CombineKey combineKey = combineData.getCombineKey();
        if (!combineDatas.containsKey(combineKey)) {
            CombineExecute<K, T> execute = CombineUtil.createCombineExecute(combineData.getClass());
            combineDatas.put(combineKey, execute);
            addTransactionSuccTask(execute);
        }
        combineDatas.get(combineKey).tryExecute(key, combineData);
    }

    public static void broadcast(Protocol p) {
        Module.INS.broadcast(p);
    }

    public static void broadcastWhileSucc(Protocol p) {
        addTransactionSuccTask(() -> Module.INS.broadcast(p));
    }

    private static void addTransactionFailTask(Runnable r) {
        Transaction.get().addFailTask(new GsRunnableWrapper(r));
    }

    private static void addTransactionSuccTask(Runnable r) {
        Transaction.get().addSuccTask(r);
    }

}
