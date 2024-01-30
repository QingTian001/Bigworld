package cfg.localize;

public final class LocalizeString extends   pcore.cfg.CfgObject  {

    public static final int TYPE_ID = 50810;
    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }

    public final String context;
       
     public final String[] inputParams;
       
     

    public LocalizeString(pcore.marshal.Octets os) {
        context = os.readString(); 
          inputParams = cfg.Extensions.unmarshal_array_string(os); 
          
    }

    public void resolve(cfg.CfgMgr cfgMgr) {
         
          
         
    }
}
