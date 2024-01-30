package cfg.login;

public final class Server extends   pcore.cfg.CfgObject  {

    public static final int TYPE_ID = 25334;
    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }

    public final int id;
       
     public final String name;
       
     

    public Server(pcore.marshal.Octets os) {
        id = os.readInt(); 
          name = os.readString(); 
          
    }

    public void resolve(cfg.CfgMgr cfgMgr) {
         
          
         
    }
}
