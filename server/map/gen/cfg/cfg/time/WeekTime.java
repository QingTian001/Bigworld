package cfg.time;

public final class WeekTime extends Time {

    public static final int TYPE_ID = 3941;
    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }

    public final int weekDay;
       
     public final int hour;
       
     public final int minute;
       
     

    public WeekTime(pcore.marshal.Octets os) {
        super(os);
        weekDay = os.readInt(); 
          hour = os.readInt(); 
          minute = os.readInt(); 
          
    }

    public void resolve(cfg.CfgMgr cfgMgr) {
        super.resolve(cfgMgr);
         
          
          
         
    }
}
