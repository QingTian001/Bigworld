package cfg.time;

public final class OneDayRange extends TimeRange {

    public static final int TYPE_ID = 8222;
    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }

    public final cfg.time.OneDayTime beginTime;
       
     public final cfg.time.OneDayTime endTime;
       
     

    public OneDayRange(pcore.marshal.Octets os) {
        super(os);
        beginTime = cfg.Extensions.unmarshal_cfg_time_OneDayTime(os); 
          endTime = cfg.Extensions.unmarshal_cfg_time_OneDayTime(os); 
          
    }

    public void resolve(cfg.CfgMgr cfgMgr) {
        super.resolve(cfgMgr);
        beginTime.resolve(cfgMgr);  
         endTime.resolve(cfgMgr);  
         
    }
}
