package cfg.time;

public final class WeekRanges extends TimeRanges {

    public static final int TYPE_ID = 6188;
    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }

    public final cfg.time.WeekRange[] weekRanges;
       
     

    public WeekRanges(pcore.marshal.Octets os) {
        super(os);
        weekRanges = cfg.Extensions.unmarshal_array_cfg_time_WeekRange(os); 
          
    }

    public void resolve(cfg.CfgMgr cfgMgr) {
        super.resolve(cfgMgr);
        cfg.Extensions.resolve_array_cfg_time_WeekRange(cfgMgr,weekRanges);  
         
    }
}
