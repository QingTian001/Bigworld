package cfg.time;

public final class DateRange extends TimeRange {

    public static final int TYPE_ID = 60010;
    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }

    public final cfg.time.DateTime beginTime;
       
     public final cfg.time.DateTime endTime;
       
     

    public DateRange(pcore.marshal.Octets os) {
        super(os);
        beginTime = cfg.Extensions.unmarshal_cfg_time_DateTime(os); 
          endTime = cfg.Extensions.unmarshal_cfg_time_DateTime(os); 
          
    }

    public void resolve(cfg.CfgMgr cfgMgr) {
        super.resolve(cfgMgr);
        beginTime.resolve(cfgMgr);  
         endTime.resolve(cfgMgr);  
         
    }
}
