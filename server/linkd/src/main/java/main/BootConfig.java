package main;

import auth.AuthConf;
import auth.AuthManager;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mafia.serverex.metrics.MetricsHelper;
import gm.GmModule;
import kite.node.INodeHandler;
import kite.node.NodeServer;
import link.LinkConf;
import link.LinkServer;
import pcore.db.Trace;
import pcore.io.DynamicMultiClientManager;
import provider.ProviderConf;

import java.io.*;

/**
 * Created by zyao on 2020/2/9 11:40
 */
public final class BootConfig {
    private static final BootConfig ins = new BootConfig();

    private LinkConf linkConf = new LinkConf();
    private AuthConf authConf = new AuthConf();
    private ProviderConf providerConf = new ProviderConf();
    private GmModule.Conf gmConf = new GmModule.Conf();
    private MetricsHelper.Conf metricsConf = new MetricsHelper.Conf();
    private int serverId = 0;
    private String jsonFile;

    public static BootConfig getIns() {
        return ins;
    }
    
    public void load(String jsonFile) throws Exception {
        this.jsonFile = jsonFile;
        var jo = readJson(jsonFile);

        serverId = jo.get("serverId").getAsInt();
        linkConf.parse(jo.getAsJsonObject("linkServer"));
        authConf.parse(jo.getAsJsonObject("authClient"));
        providerConf.parse(jo.getAsJsonObject("providerServer"));
        gmConf.parse(jo.getAsJsonObject("gm"));

        metricsConf.parse(jo.getAsJsonObject("metrics"));
    }

    private JsonObject readJson(String jsonFile) {
        try {
            try (FileReader fileReader = new FileReader(jsonFile)) {
                return (JsonObject) new JsonParser().parse(fileReader);
            }
        } catch (IOException ex) {
            Trace.error(String.format("read %s", jsonFile), ex);
        }
        return new JsonObject();
    }

    private String readContent(String fileName) {
        try {
            return new String(util.FileUtils.readBinaryFile(fileName));
        } catch (IOException ex) {
            Trace.error(String.format("read %s", fileName), ex);
        }
        return "";
    }

    private void reloadConfig(JsonObject jo) throws IOException {
        parseConfig(authConf, jo, "authClient");

        if (AuthManager.getInst() != null) {
            AuthManager.getInst().updateServers(authConf.getServers());
        }

        String content = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create().toJson(jo);
        writeFile(jsonFile, content, "UTF-8");
    }

    private void parseConfig(DynamicMultiClientManager.Conf conf, JsonObject jo, String key) {
        conf.clear();
        conf.parse(jo.getAsJsonObject(key));
        for (var c : conf.getServers().values()) {
            conf.initConf(c);
        }
    }

    public void writeFile(String filename, String content, String charset) throws IOException {
        try (FileOutputStream stream = new FileOutputStream (filename);
             OutputStreamWriter osWriter = new OutputStreamWriter (stream, charset);
             BufferedWriter fileWriter = new BufferedWriter (osWriter)) {
            fileWriter.write(content);
            fileWriter.flush();
        }
    }

    public GmModule.Conf getGmConf() {
        return gmConf;
    }

    public MetricsHelper.Conf getMetricsConf() {
        return metricsConf;
    }

    public LinkConf getLinkConf() {
        return linkConf;
    }

    public AuthConf getAuthConf() {
        return authConf;
    }

    public ProviderConf getProviderConf() {
        return providerConf;
    }

    public int getServerId() {
        return serverId;
    }
}
