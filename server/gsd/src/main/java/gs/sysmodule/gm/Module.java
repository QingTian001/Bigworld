package gs.sysmodule.gm;

import com.google.gson.JsonElement;
import gs.cfg.BootConfig;
import com.mafia.serverex.gmbase.*;
import gs.util.ExecutorUtil;
import pcore.io.Protocol;
import pcore.misc.IModule;
import com.google.gson.JsonObject;

import gs.net.link.LinkManager;
import gs.net.link.LinkUser;
import msg.gs.gm.*;

import java.io.IOException;
import java.util.Comparator;

public enum Module implements IModule {
    Ins;

    interface IGmProtocolHandler<T extends Protocol>  {
        void handle(T p, LinkUser user);
    }

    public final void gmLogin(long roleId) {
        GmSession.current().setRoleId(roleId);
    }

    public final GmCmdResult error(String fmt, Object... params) {
        return GmCmdResult.error(String.format(fmt, params));
    }

    @Override
    public final void start() {
        // TODO 添加GM账号和密码

        Conf conf = BootConfig.getIns().getGmConf();
        try {
            new GmLauncher()
                    .test(conf.isTest) //是否在测试模式下启动GM攻击，false表示正式环境，需要登陆GM账号才能执行GM命令，登录方式[admin login account password]
                    .gmport(conf.gmPort) // gs.gmbase socket端口，telnet控制台，!=0 启动socket监听
                    .optional(() -> true,
                            launcher -> {
//                                launcher.httpport(1080) // gs.gmbase http端口，!=0 提供以http的方式执行GM命令
//                                        .httpContext("gs/gmbase")
//                                        .httpExecutor(GmHttpServer.defaultExecutor());
                            })
                    .autoScanGmPackage(Module.class.getPackage().getName()) //扫描package下带有gm.annotation.Module注解的class并自动注册到GM命令中
                    //启动GM
                    .start();
        } catch (IOException e) {
            throw new RuntimeException("Gm start failed", e);
        }

        if (conf.isTest) {
            var p = LinkManager.getDispatcher();
            p.register(CGetModuleList.class, this::process);
            p.register(CExecuteCommand.class, this::process);
        }
    }

    private <T extends Protocol> void execute(IGmProtocolHandler<T> processor, T p) {
        ExecutorUtil.execute(() -> {
            if (GmSession.current() == null) {
                GmSession.init();
            }
            LinkUser linkUser = (LinkUser) p.getContext();
            try {
                gs.sysmodule.gm.Module.Ins.gmLogin(linkUser.getRoleId());
                processor.handle(p, linkUser);
            }
            finally {
                GmSession.destroy();
            }
        });
    }

    private void process(CGetModuleList proto) {
        execute((p, user) -> {
            SGetModuleList re = new SGetModuleList();
            Metas.INSTANCE.allModules().forEach(m -> {
                if (!m.showInClient()) {
                    return;
                }
                ModuleInfo module = new ModuleInfo();
                module.name = m.getName();
                module.desc = m.getComment();

                m.getCmds().forEach(c -> {
                    if (c.disableShowInClient()) {
                        return;
                    }
                    CommandInfo cmd = new CommandInfo();
                    cmd.name = c.getName();
                    cmd.desc = c.getComment();
                    c.getParams().forEach(param -> {
                        cmd.parameters.add(new ParamInfo(param.getName(), param.getComment(), param.getType().getSimpleName()));
                    });
                    module.commands.add(cmd);
                });
                module.commands.sort(Comparator.comparing(c -> c.name));
                re.modules.add(module);
            });
            re.modules.sort(Comparator.comparing(m -> m.name));
            user.send(re);
        }, proto);
    }

    public final GmCmdResult func(Meta.Cmd cmdMeta, Object[] params){
        GsGmProcedure p = new GsGmProcedure(cmdMeta, params);
        if (cmdMeta.isAutoTransaction()) {
            p.call();
        } else {
            p.process();
        }
        return p.getResult();
    }

    private void process(CExecuteCommand proto) {
        execute((p, user) -> {
            String commandFullName = p.module + "." + p.command;
            Meta.Cmd cmdMeta = Metas.INSTANCE.getCmd(commandFullName);

            GmCmdResult result = GmCmd.execute(cmdMeta, p.parameters.toArray(new String[0]), false,this::func);
            result.accept(GmCmdResultVistor.LOGGER.andThen(new GmCmdResultVistor() {
                @Override
                public void visit(GmCmdResult.Ok r) {
                    String retMsg = null;
                    if (r.result != null) {
                        if (r.result instanceof String) {
                            retMsg = (String) r.result;
                        } else {
                            retMsg = r.result.toString();
                        }
                    }
                    if (retMsg == null || retMsg.isEmpty()) {
                        retMsg = "执行成功";
                    }
                    write(retMsg);
                }

                @Override
                public void visit(GmCmdResult.Exception r) {
                    write(Util.getExceptionStackTrace(r.exception));
                }

                @Override
                public void visit(GmCmdResult.Error r) {
                    write(r.msg);
                }

                @Override
                public void visit(GmCmdResult.CommonError r) {
                    write(r.msg != null ? r.msg : r.err.msg);
                }

                @Override
                public void visit(GmCmdResult.Exit r) {
                    write("bye");
                }

                private void write(String result) {
                    user.send(new SExecuteCommand(result));
                }
            }));
        }, proto);
    }

    public static class Conf {
        int gmPort = 0;
        int httpPort = 0;
        String gmPasswd = "gmbase";
        boolean isTest = false;

        public void parse(JsonObject jo) {
            JsonElement je = null;
            if ((je = jo.get("gmPort")) != null) {
                gmPort = je.getAsInt();
            }
            if ((je = jo.get("httpPort")) != null) {
                httpPort = je.getAsInt();
            }
            if ((je = jo.get("gmPasswd")) != null) {
                gmPasswd = je.getAsString();
            }
            if ((je = jo.get("isTest")) != null) {
                isTest = je.getAsBoolean();
            }
        }
    }
}
