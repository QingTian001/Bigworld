package cfg.localize;

public final class Localization extends   pcore.cfg.CfgObject  {

    public static final int TYPE_ID = 32051;
    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }

    public final String origin;
       
     public final String indexStr;
       
     public final String localization;
       
     

    public Localization(pcore.marshal.Octets os) {
        origin = os.readString(); 
          indexStr = os.readString(); 
          localization = os.readString(); 
          
    }

    public void resolve(cfg.CfgMgr cfgMgr) {
         
          
          
         
    }
}
