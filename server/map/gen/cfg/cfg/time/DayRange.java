package cfg.time;

public final class DayRange extends TimeRange {

    public static final int TYPE_ID = 11763;
    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }

    public final cfg.time.DayTime beginTime;
       
     public final cfg.time.DayTime endTime;
       
     

    public DayRange(pcore.marshal.Octets os) {
        super(os);
        beginTime = cfg.Extensions.unmarshal_cfg_time_DayTime(os); 
          endTime = cfg.Extensions.unmarshal_cfg_time_DayTime(os); 
          
    }

    public void resolve(cfg.CfgMgr cfgMgr) {
        super.resolve(cfgMgr);
        beginTime.resolve(cfgMgr);  
         endTime.resolve(cfgMgr);  
         
    }
}
