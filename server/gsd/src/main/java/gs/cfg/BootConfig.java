package gs.cfg;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mafia.serverex.gmbase.Gm;
import com.mafia.serverex.gmbase.common.Script;
import com.mafia.serverex.metrics.MetricsHelper;
import gs.net.aud.AudClient;
import gs.net.link.LinkManager;
import gs.net.serverlist.ServerListManager;
import pcore.db.Trace;
import pcore.io.DynamicMultiClientManager;

import java.io.*;

public class BootConfig {

    private static final BootConfig ins = new BootConfig();
    private final LinkManager.Conf linkConf = new LinkManager.Conf();
    private final gs.sysmodule.gm.Module.Conf gmConf = new gs.sysmodule.gm.Module.Conf();
    private final ServerListManager.ServerListConf serverListConf = new ServerListManager.ServerListConf();
    private final AudClient.Conf audConf = new AudClient.Conf();
    private final MetricsHelper.Conf metricsConf = new MetricsHelper.Conf();
    private int serverId;
    private int regionId;
    private String dataDir;
    private boolean debug = true;
    private boolean rechargeDebug = false;
    private boolean loginDebug = false;
    private String sensitiveWordsFile;
    private String timeZone;

    private String jsonFile;
    private pcore.db.DbConf dbConf = new pcore.db.DbConf();

    private int userTaskQueueNum = Math.max(16, Runtime.getRuntime().availableProcessors() * 2);
    private int loginTaskQueueNum = Math.max(16, Runtime.getRuntime().availableProcessors() * 2);

    public static BootConfig getIns() {
        return ins;
    }

    public void load(String jsonFile) throws Exception {
        this.jsonFile = jsonFile;
        var jo = readJson(jsonFile);

        serverId = jo.get("serverId").getAsInt();
        regionId = jo.get("regionId").getAsInt();
        dataDir = jo.get("dataDir").getAsString();
        sensitiveWordsFile = jo.get("sensitiveWordsFile").getAsString();
        timeZone = jo.get("timeZone").getAsString();

        if (jo.has("debug")) {
            debug = jo.get("debug").getAsBoolean();
        }

        if (jo.has("rechargeDebug")) {
            rechargeDebug = jo.get("rechargeDebug").getAsBoolean();
        }
        if (jo.has("loginDebug")) {
            loginDebug = jo.get("loginDebug").getAsBoolean();
        }

        linkConf.parse(jo.getAsJsonObject("linkClient"));
        gmConf.parse(jo.getAsJsonObject("gm"));
        dbConf.parse(jo.getAsJsonObject("db"));
        serverListConf.parse(jo.getAsJsonObject("serverList"));
        audConf.parse(jo.getAsJsonObject("aud"));

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
            return new String(gs.util.FileUtils.readBinaryFile(fileName));
        } catch (IOException ex) {
            Trace.error(String.format("read %s", fileName), ex);
        }
        return "";
    }

    private void reloadConfig(JsonObject jo) throws IOException {
        parseConfig(linkConf, jo, "linkClient");

        if (LinkManager.getIns() != null) {
            LinkManager.getIns().updateServers(linkConf.getServers());
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

    public int getServerId() {
        return serverId;
    }

    public int getRegionId() {
        return regionId;
    }

    public String getDataDir() {
        return dataDir;
    }

    public String getSensitiveWordsFile() {
        return sensitiveWordsFile;
    }

    public boolean isDebug() {
        return debug;
    }

    public boolean isRechargeDebug() {
        return rechargeDebug;
    }

    public boolean isLoginDebug() {
        return loginDebug;
    }

    public pcore.db.DbConf getDbConf() {
        return dbConf;
    }

    public LinkManager.Conf getLinkConf() {
        return linkConf;
    }


    public gs.sysmodule.gm.Module.Conf getGmConf() {
        return gmConf;
    }

    public int getUserTaskQueueNum() {
        return userTaskQueueNum;
    }

    public int getLoginTaskQueueNum() {
        return loginTaskQueueNum;
    }

    public ServerListManager.ServerListConf getServerListConf() {
        return serverListConf;
    }

    public AudClient.Conf getAudConf() {
        return audConf;
    }

    public MetricsHelper.Conf getMetricsConf() {
        return metricsConf;
    }

    public int getLocalId() {
        return dbConf.getLocalId();
    }

    public String getTimeZone() {
         return timeZone;
    }

}
