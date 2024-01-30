package cfg.time;

public final class MultipleWeekRange extends TimeRange {

    public static final int TYPE_ID = 22479;
    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }

    public final cfg.time.DateTime baseDateTime;
       
     public final cfg.time.WeekTime beginTime;
       
     public final int deltaWeeksNum;
       
     public final cfg.time.WeekTime endTime;
       
     public final int loopWeeksNum;
       
     

    public MultipleWeekRange(pcore.marshal.Octets os) {
        super(os);
        baseDateTime = cfg.Extensions.unmarshal_cfg_time_DateTime(os); 
          beginTime = cfg.Extensions.unmarshal_cfg_time_WeekTime(os); 
          deltaWeeksNum = os.readInt(); 
          endTime = cfg.Extensions.unmarshal_cfg_time_WeekTime(os); 
          loopWeeksNum = os.readInt(); 
          
    }

    public void resolve(cfg.CfgMgr cfgMgr) {
        super.resolve(cfgMgr);
        baseDateTime.resolve(cfgMgr);  
         beginTime.resolve(cfgMgr);  
          
         endTime.resolve(cfgMgr);  
          
         
    }
}
