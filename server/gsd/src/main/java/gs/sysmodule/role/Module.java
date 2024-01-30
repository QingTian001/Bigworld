package gs.sysmodule.role;


import gs.event.sysmodule.*;
import pcore.event.EventHandler;
import pcore.misc.IModule;

public enum Module implements IModule {
    Ins;

    @Override
    public void start() {
    }

    @EventHandler
    private void onEvent(RoleLoginInProcedure e) {

    }

    @EventHandler
    private void onEvent(RoleLoginFinishInProcedure e) {
    }

    @EventHandler
    private void onEvent(RoleLogoutInProcedure e) {
    }

    @EventHandler
    void onEvent(RoleLoginBeforeInProcedure e) {

    }
}
