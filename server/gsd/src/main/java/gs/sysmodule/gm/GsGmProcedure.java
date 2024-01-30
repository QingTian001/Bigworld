package gs.sysmodule.gm;

import com.mafia.serverex.gmbase.*;
import gs.util.GProcedure;

import java.lang.reflect.InvocationTargetException;

public class GsGmProcedure extends GProcedure {

    private final Meta.Cmd cmd;
    private final Object[] params;
    private GmCmdResult result;

    public GsGmProcedure(Meta.Cmd cmd, Object[] params) {
        super();
        this.cmd = cmd;
        this.params = params;
    }

    @Override
    protected boolean doProcess() {
        result = process0();
        result.setCmd(cmd);

        return result.accept(new GmCmdResultVistorR<>() {

            @Override
            public Boolean visit(GmCmdResult.Exit r) {
                return true;
            }

            @Override
            public Boolean visit(GmCmdResult.Ok r) {
                return true;
            }

            @Override
            public Boolean visit(GmCmdResult.Error r) {
                return false;
            }

            @Override
            public Boolean visit(GmCmdResult.CommonError r) {
                return false;
            }

            @Override
            public Boolean visit(GmCmdResult.Exception r) {
                return false;
            }
        });
    }

    private GmCmdResult process0() {
        if (!Gm.isTest() && GmSession.current().getAuthLevel().lessThan(cmd.getAuthLevel())) {
            return GmCmdResult.error("没有权限");
        }
        try {
            Object r = cmd.getMethod().invoke(cmd.getObj(), params);
            if (r == null) {
                return GmCmdResult.success(null);
            } else if (r instanceof GmCmdResult) {
                return (GmCmdResult) r;
            } else {
                return GmCmdResult.success(r);
            }
        } catch (InvocationTargetException e) {
            Throwable t = e.getCause();
            if (t instanceof GmException) {
                GmException g = (GmException) t;
                return GmCmdResult.error(g.getCode(), g.getMessage());
            } else {
                return GmCmdResult.exception(t);
            }
        } catch (GmException e) {
            return GmCmdResult.error(e.getCode(), e.getMessage());
        } catch (Throwable t) {
            return GmCmdResult.exception(t);
        }
    }

    public GmCmdResult getResult() {
        return result;
    }
}

