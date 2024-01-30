package cfg.time;

public final class WeekRange extends TimeRange {

    public static final int TYPE_ID = 4025;
    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }

    public final cfg.time.WeekTime beginTime;
       
     public final cfg.time.WeekTime endTime;
       
     

    public WeekRange(pcore.marshal.Octets os) {
        super(os);
        beginTime = cfg.Extensions.unmarshal_cfg_time_WeekTime(os); 
          endTime = cfg.Extensions.unmarshal_cfg_time_WeekTime(os); 
          
    }

    public void resolve(cfg.CfgMgr cfgMgr) {
        super.resolve(cfgMgr);
        beginTime.resolve(cfgMgr);  
         endTime.resolve(cfgMgr);  
         
    }
}
