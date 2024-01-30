package cfg.login;

public final class LoginConfig extends   pcore.cfg.CfgObject  {

    public static final int TYPE_ID = 58595;
    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }

    public final String loginTextureIndex;
       
     public final int myAreaId;
       
     public final String myAreaName;
       
     public final int randomUserAccountLen;
       
     public final int gapIntervals;
       
     public final int activeIntervals;
       
     public final int pingPongGaptime;
       
     public final int reconnectTotalCount;
       
     public final float reconnectIntervals;
       
     

    public LoginConfig(pcore.marshal.Octets os) {
        loginTextureIndex = os.readString(); 
          myAreaId = os.readInt(); 
          myAreaName = os.readString(); 
          randomUserAccountLen = os.readInt(); 
          gapIntervals = os.readInt(); 
          activeIntervals = os.readInt(); 
          pingPongGaptime = os.readInt(); 
          reconnectTotalCount = os.readInt(); 
          reconnectIntervals = os.readFloat(); 
          
    }

    public void resolve(cfg.CfgMgr cfgMgr) {
         
          
          
          
          
          
          
          
          
         
    }
}
