package cfg.login;

public final class LoginGroup extends   pcore.cfg.CfgObject  {

    public static final int TYPE_ID = 32141;
    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }

    public final int id;
       
     public final String name;
       
     

    public LoginGroup(pcore.marshal.Octets os) {
        id = os.readInt(); 
          name = os.readString(); 
          
    }

    public void resolve(cfg.CfgMgr cfgMgr) {
         
          
         
    }
}
