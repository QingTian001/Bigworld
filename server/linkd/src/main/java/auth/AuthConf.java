package auth;

import com.google.gson.JsonObject;
import pcore.io.Client;
import pcore.io.DynamicMultiClientManager;

/**
 * Created by zyao on 2020/2/9 11:53
 */
public final class AuthConf extends DynamicMultiClientManager.Conf {
    private boolean reportLinkPublicAddr;

    public boolean getReportLinkPublicAddr() {
        return reportLinkPublicAddr;
    }
    @Override
    public void parse(JsonObject jo) {
        super.parse(jo);
        reportLinkPublicAddr = jo.get("reportLinkPublicAddr").getAsBoolean();
    }
}
