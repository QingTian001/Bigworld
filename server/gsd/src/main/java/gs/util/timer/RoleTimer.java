package gs.util.timer;

/**
 * Created by zyao on 2020/2/17 18:47
 */
public class RoleTimer implements ITimer{
    private final long roleId;
    private Timer timer;
    private RoleTimerHandler handler;

    public abstract static class RoleTimerHandler {
        private RoleTimer roleTimer;
        public final void cancel() {
            this.roleTimer.cancel();
        }
        public abstract void onTimer(long roleId);
    }

    public final long getRoleId() {
        return roleId;
    }

    public RoleTimer(long roleId) {
        this.roleId = roleId;
    }

    private void setTimer(Timer timer) {
        this.timer = timer;
    }

    private void setHandler(RoleTimerHandler handler) {
        this.handler = handler;
    }

    public final RoleTimerHandler getHandler() {
        return handler;
    }

    private static void bind(RoleTimer roleTimer, RoleTimerHandler handler, Timer timer) {
        roleTimer.setTimer(timer);
        handler.roleTimer = roleTimer;
        roleTimer.setHandler(handler);
    }

    public static synchronized RoleTimer scheduleOnce(long roleId, long delayInMills, RoleTimerHandler handler) {
        RoleTimer roleTimer = new RoleTimer(roleId);
        Timer timer = Timer.scheduleOnce(delayInMills, new Timer.TimerHandler() {
            @Override
            public void onTimer() {
                handler.onTimer(roleId);
            }
        });
        bind(roleTimer, handler, timer);
        return roleTimer;
    }

    public static synchronized RoleTimer schedulePeriod(long roleId, long delayInMills, long periodInMills, RoleTimerHandler handler) {
        RoleTimer roleTimer = new RoleTimer(roleId);
        Timer timer = Timer.schedulePeriod(delayInMills, periodInMills, new Timer.TimerHandler() {
            @Override
            public void onTimer() {
                handler.onTimer(roleId);
            }
        });
        bind(roleTimer, handler, timer);
        return roleTimer;
    }

    public synchronized RoleTimer schedulePerDay(long roleId, int hour, int minute, int seconds, RoleTimerHandler handler) {
        RoleTimer roleTimer = new RoleTimer(roleId);
        Timer timer = Timer.schedulePerDay(hour, minute, seconds, new Timer.TimerHandler() {
            @Override
            public void onTimer() {
                handler.onTimer(roleId);
            }
        });
        bind(roleTimer, handler, timer);
        return roleTimer;
    }

    @Override
    public void cancel() {
        timer.cancel();
    }

    @Override
    public boolean isStopped() {
        return timer.isStopped();
    }
}
