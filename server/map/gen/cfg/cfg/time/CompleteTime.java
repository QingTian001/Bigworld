package cfg.time;

public abstract class CompleteTime extends Time {

    public final int[] year;
       
     public final int[] month;
       
     public final int[] hour;
       
     public final int[] minute;
       
     

    public CompleteTime(pcore.marshal.Octets os) {
        super(os);
        year = cfg.Extensions.unmarshal_array_int(os); 
          month = cfg.Extensions.unmarshal_array_int(os); 
          hour = cfg.Extensions.unmarshal_array_int(os); 
          minute = cfg.Extensions.unmarshal_array_int(os); 
          
    }

    public void resolve(cfg.CfgMgr cfgMgr) {
        super.resolve(cfgMgr);
         
          
          
          
         
    }
}
