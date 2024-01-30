package cfg.time;

public final class ServerStartTimeRange extends TimeRange {

    public static final int TYPE_ID = 58463;
    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }

    public final int delayDaysNum;
       
     public final cfg.time.OneDayTime beginTime;
       
     public final int lastSeconds;
       
     

    public ServerStartTimeRange(pcore.marshal.Octets os) {
        super(os);
        delayDaysNum = os.readInt(); 
          beginTime = cfg.Extensions.unmarshal_cfg_time_OneDayTime(os); 
          lastSeconds = os.readInt(); 
          
    }

    public void resolve(cfg.CfgMgr cfgMgr) {
        super.resolve(cfgMgr);
         
         beginTime.resolve(cfgMgr);  
          
         
    }
}
