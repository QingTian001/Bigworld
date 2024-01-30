package cfg.time;

public final class DayRanges extends TimeRanges {

    public static final int TYPE_ID = 28687;
    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }

    public final cfg.time.DayRange[] dayRanges;
       
     

    public DayRanges(pcore.marshal.Octets os) {
        super(os);
        dayRanges = cfg.Extensions.unmarshal_array_cfg_time_DayRange(os); 
          
    }

    public void resolve(cfg.CfgMgr cfgMgr) {
        super.resolve(cfgMgr);
        cfg.Extensions.resolve_array_cfg_time_DayRange(cfgMgr,dayRanges);  
         
    }
}
