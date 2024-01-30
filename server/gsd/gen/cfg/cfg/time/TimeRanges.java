package cfg.time;

public abstract class TimeRanges extends TimeRange {

    public final int[] year;
       
     public final int[] month;
       
     

    public TimeRanges(pcore.marshal.Octets os) {
        super(os);
        year = cfg.Extensions.unmarshal_array_int(os); 
          month = cfg.Extensions.unmarshal_array_int(os); 
          
    }

    public void resolve(cfg.CfgMgr cfgMgr) {
        super.resolve(cfgMgr);
         
          
         
    }
}
