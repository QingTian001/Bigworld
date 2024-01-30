package link;

import com.google.gson.JsonObject;
import pcore.io.Server;

/**
 * Created by zyao on 2020/2/8 18:02
 */
public final class LinkConf extends Server.Conf {

    private int forwardSessionKeepAliveTime;

    private String publicIp;
    private String publicIpv6;
    private boolean publicIsBackup;

    private int oneLimitNum;
    private long oneLimitMillis;
    private int allLimitNum;
    private long allLimitMillis;

    public int getForwardSessionKeepAliveTime() {
        return forwardSessionKeepAliveTime;
    }

    public String getPublicIp() {
        return publicIp;
    }
    public String getPublicIpv6() {
        return publicIpv6;
    }
    public boolean getPublicIsBackup() {
        return publicIsBackup;
    }

    public int getOneLimitNum() { return oneLimitNum; }
    public long getOneLimitMillis() { return oneLimitMillis; }
    public int getAllLimitNum() { return allLimitNum; }
    public long getAllLimitMillis() { return allLimitMillis; }

    @Override
    public void parse(JsonObject jo) {
        super.parse(jo);
        forwardSessionKeepAliveTime = jo.get("forwardSessionKeepAliveTime").getAsInt();
        publicIp = getOrDefault(jo, "publicIp", "");
        publicIpv6 = getOrDefault(jo, "publicIpv6", "");
        publicIsBackup = getOrDefault(jo, "publicIsBackup", false);

        JsonObject limiter = jo.getAsJsonObject("limiter");
        oneLimitNum = limiter.get("oneLimitNum").getAsInt();
        oneLimitMillis = limiter.get("oneLimitSecs").getAsInt() * 1000;
        allLimitNum = limiter.get("allLimitNum").getAsInt();
        allLimitMillis = limiter.get("allLimitSecs").getAsInt() * 1000;
    }

    private String getOrDefault(JsonObject jo, String key, String defaultValue) {
        return jo.has(key) ? jo.get(key).getAsString() : defaultValue;
    }

    private boolean getOrDefault(JsonObject jo, String key, boolean defaultValue) {
        return jo.has(key) ? jo.get(key).getAsBoolean() : defaultValue;
    }
}
