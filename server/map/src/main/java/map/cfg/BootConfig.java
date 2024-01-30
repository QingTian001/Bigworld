package map.cfg;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mafia.serverex.metrics.MetricsHelper;
import map.net.gs.GsManager;
import map.net.link.LinkManager;
import kite.node.INodeHandler;
import kite.node.NodeServer;
import pcore.io.DynamicMultiClientManager;

import java.io.*;

/**
 * Created by zyao on 2020/3/22 19:33
 */
public final class BootConfig {

    private static final BootConfig ins = new BootConfig();
    private final LinkManager.Conf linkConf = new LinkManager.Conf();
    private final GsManager.Conf gsConf = new GsManager.Conf();
    private final MetricsHelper.Conf metricsConf = new MetricsHelper.Conf();

    private int serverId;
    private String dataDir;
    private String jsonFile;
    private String log4jFile;
    private boolean debug;

    public static BootConfig getIns() {
        return ins;
    }

    public void load(String jsonFile) throws Exception {
        this.jsonFile = jsonFile;
        var jo = readJson(jsonFile);

        serverId = jo.get("serverId").getAsInt();
        dataDir = jo.get("dataDir").getAsString();
        log4jFile = jo.get("log4jFile").getAsString();

        if (jo.has("debug")) {
            debug = jo.get("debug").getAsBoolean();
        }

        linkConf.parse(jo.getAsJsonObject("linkClient"));
        gsConf.parse(jo.getAsJsonObject("gsServer"));
        metricsConf.parse(jo.getAsJsonObject("metrics"));

//        NodeServer.start(jo.getAsJsonObject("kiteServer"), new INodeHandler() {
//            @Override
//            public boolean updateConfig(JsonObject jo) throws IOException {
//                reloadConfig(jo);
//                return true;
//            }
//
//            @Override
//            public int getServerId() {
//                return serverId;
//            }
//
//            @Override
//            public int getUserOnline() {
//                return 0;
//            }
//
//            @Override
//            public JsonObject getRawJsonConfig() {
//                return readJson(jsonFile);
//            }
//        });
    }

    private JsonObject readJson(String jsonFile) {
        try {
            try (FileReader fileReader = new FileReader(jsonFile)) {
                return (JsonObject) new JsonParser().parse(fileReader);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return new JsonObject();
    }

    private void reloadConfig(JsonObject jo) throws IOException {
        parseConfig(linkConf, jo, "linkClient");

        if (LinkManager.getInstance() != null) {
            LinkManager.getInstance().updateServers(linkConf.getServers());
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
             BufferedWriter fileWriter = new BufferedWriter(osWriter)) {
            fileWriter.write(content);
            fileWriter.flush();
        }
    }

    public int getServerId() {
        return serverId;
    }

    public String getDataDir() {
        return dataDir;
    }

    public String getLog4jFile() {
        return log4jFile;
    }

    public boolean isDebug() {
        return debug;
    }

    public LinkManager.Conf getLinkConf() {
        return linkConf;
    }

    public GsManager.Conf getGsConf() {
        return gsConf;
    }

    public MetricsHelper.Conf getMetricsConf() {
        return metricsConf;
    }

}