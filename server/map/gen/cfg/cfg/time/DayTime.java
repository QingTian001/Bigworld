package cfg.time;

public final class DayTime extends Time {

    public static final int TYPE_ID = 11888;
    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }

    public final int dayOfMonth;
       
     public final int hour;
       
     public final int minute;
       
     

    public DayTime(pcore.marshal.Octets os) {
        super(os);
        dayOfMonth = os.readInt(); 
          hour = os.readInt(); 
          minute = os.readInt(); 
          
    }

    public void resolve(cfg.CfgMgr cfgMgr) {
        super.resolve(cfgMgr);
         
          
          
         
    }
}
