package cfg.time;

public final class OneDayTime extends Time {

    public static final int TYPE_ID = 47942;
    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }

    public final int hour;
       
     public final int minute;
       
     

    public OneDayTime(pcore.marshal.Octets os) {
        super(os);
        hour = os.readInt(); 
          minute = os.readInt(); 
          
    }

    public void resolve(cfg.CfgMgr cfgMgr) {
        super.resolve(cfgMgr);
         
          
         
    }
}
