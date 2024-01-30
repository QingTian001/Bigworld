package cfg.time;

public final class OneDayRanges extends TimeRanges {

    public static final int TYPE_ID = 58147;
    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }

    public final cfg.time.OneDayRange[] oneDayRanges;
       
     

    public OneDayRanges(pcore.marshal.Octets os) {
        super(os);
        oneDayRanges = cfg.Extensions.unmarshal_array_cfg_time_OneDayRange(os); 
          
    }

    public void resolve(cfg.CfgMgr cfgMgr) {
        super.resolve(cfgMgr);
        cfg.Extensions.resolve_array_cfg_time_OneDayRange(cfgMgr,oneDayRanges);  
         
    }
}
