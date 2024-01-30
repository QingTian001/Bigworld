package cfg.time;

public final class CompleteDayTime extends CompleteTime {

    public static final int TYPE_ID = 22781;
    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }

    public final int[] dayOfMonth;
       
     

    public CompleteDayTime(pcore.marshal.Octets os) {
        super(os);
        dayOfMonth = cfg.Extensions.unmarshal_array_int(os); 
          
    }

    public void resolve(cfg.CfgMgr cfgMgr) {
        super.resolve(cfgMgr);
         
         
    }
}
