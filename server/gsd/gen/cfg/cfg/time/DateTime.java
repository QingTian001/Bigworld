package cfg.time;

public final class DateTime extends Time {

    public static final int TYPE_ID = 40407;
    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }

    public final int year;
       
     public final int month;
       
     public final int day;
       
     public final int hour;
       
     public final int minute;
       
     

    public DateTime(pcore.marshal.Octets os) {
        super(os);
        year = os.readInt(); 
          month = os.readInt(); 
          day = os.readInt(); 
          hour = os.readInt(); 
          minute = os.readInt(); 
          
    }

    public void resolve(cfg.CfgMgr cfgMgr) {
        super.resolve(cfgMgr);
         
          
          
          
          
         
    }
}
