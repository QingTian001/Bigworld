package gm;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mafia.serverex.gmbase.GmLauncher;
import com.mafia.serverex.gmbase.server.GmHttpServer;
import main.BootConfig;

import java.io.IOException;

public class GmModule {
    public static void start() {
        // TODO 添加GM账号和密码
        Conf conf = BootConfig.getIns().getGmConf();
        try {
            new GmLauncher()
                    .test(conf.isTest) //是否在测试模式下启动GM攻击，false表示正式环境，需要登陆GM账号才能执行GM命令，登录方式[admin login account password]
                    .gmport(conf.gmPort) // gs.gmbase socket端口，telnet控制台，!=0 启动socket监听
                    .httpport(conf.httpPort) // http端口 !=0
                    .httpContext("gm")
                    .httpExecutor(GmHttpServer.defaultExecutor())
                    .autoScanGmPackage(GmModule.class.getPackage().getName()) //扫描package下带有gm.annotation.Module注解的class并自动注册到GM命令中
                    .start(); //启动GM
        } catch (IOException e) {
            throw new RuntimeException("Gm start failed", e);
        }
    }

    public static class Conf {
        int gmPort = 0;
        int httpPort = 0;
        String gmPasswd = "gmbase";
        boolean isTest = false;

        public void parse(JsonObject jo) {
            JsonElement je;
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
