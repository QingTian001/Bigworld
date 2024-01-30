package cfg.time;

public final class CompleteWeekTime extends CompleteTime {

    public static final int TYPE_ID = 13939;
    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }

    public final int[] weekDay;
       
     

    public CompleteWeekTime(pcore.marshal.Octets os) {
        super(os);
        weekDay = cfg.Extensions.unmarshal_array_int(os); 
          
    }

    public void resolve(cfg.CfgMgr cfgMgr) {
        super.resolve(cfgMgr);
         
         
    }
}
