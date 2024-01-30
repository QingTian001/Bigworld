package cfg.time;

public final class MultipleDayRange extends TimeRange {

    public static final int TYPE_ID = 14202;
    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }

    public final cfg.time.DateTime baseDateTime;
       
     public final cfg.time.OneDayTime beginTime;
       
     public final int deltaDaysNum;
       
     public final cfg.time.OneDayTime endTime;
       
     public final int loopDaysNum;
       
     

    public MultipleDayRange(pcore.marshal.Octets os) {
        super(os);
        baseDateTime = cfg.Extensions.unmarshal_cfg_time_DateTime(os); 
          beginTime = cfg.Extensions.unmarshal_cfg_time_OneDayTime(os); 
          deltaDaysNum = os.readInt(); 
          endTime = cfg.Extensions.unmarshal_cfg_time_OneDayTime(os); 
          loopDaysNum = os.readInt(); 
          
    }

    public void resolve(cfg.CfgMgr cfgMgr) {
        super.resolve(cfgMgr);
        baseDateTime.resolve(cfgMgr);  
         beginTime.resolve(cfgMgr);  
          
         endTime.resolve(cfgMgr);  
          
         
    }
}
