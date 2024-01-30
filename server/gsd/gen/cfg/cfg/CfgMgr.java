package cfg;

public final class CfgMgr {
    private static String _dir = "config";

    public static void setDir(String dir) {
        _dir = dir;
    }

    public static volatile CfgMgr ins;

    private pcore.marshal.Octets createOctets(String file) {
        try {
            return pcore.marshal.Octets.wrap(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(_dir + "/" + file)));
        } catch (java.io.IOException e) {
            throw new RuntimeException(e);
        }
    }

    private cfg.login.LoginConfig loginconfig;
    public cfg.login.LoginConfig getLoginConfig() { return loginconfig; }
     private java.util.List<cfg.localize.Localization> localizationList;
    public java.util.List<cfg.localize.Localization> getLocalizationList() { return localizationList; }
     

    private CfgMgr() {
        loginconfig = cfg.Extensions.unmarshal_cfg_login_LoginConfig(createOctets("loginconfig.data")); 
        localizationList = cfg.Extensions.unmarshal_list_cfg_localize_Localization(createOctets("localization.data")); 

        resolve();
    }

    public static void load() {
        ins = new CfgMgr();
    }

    private void resolve() {
        loginconfig.resolve(this); 
        cfg.Extensions.resolve_list_cfg_localize_Localization(this,localizationList); 

    }
}
