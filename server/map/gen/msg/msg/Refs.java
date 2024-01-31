package msg;

public final class Refs {
    public static final java.util.HashMap<Integer, pcore.io.IProtocolFactory> gmap = new java.util.HashMap<>(); 
    public static final java.util.HashMap<Integer, pcore.io.IProtocolFactory> plink = new java.util.HashMap<>(); 
    public static final java.util.HashMap<Integer, pcore.io.IProtocolFactory> map = new java.util.HashMap<>(); 
    public static final java.util.HashMap<Integer, pcore.io.IProtocolFactory> mapServer = new java.util.HashMap<>(); 

    public static final int mapProviderId = 12;

    static {
      gmap.put(msg.net.GServerAnnouceServerInfo.TYPE_ID, msg.net.GServerAnnouceServerInfo::new); 
          gmap.put(msg.gmap.MGMessage.TYPE_ID, msg.gmap.MGMessage::new); 
          gmap.put(msg.gmap.GMMessage.TYPE_ID, msg.gmap.GMMessage::new); 
          gmap.put(msg.gmap.GCfgReload.TYPE_ID, msg.gmap.GCfgReload::new); 
          gmap.put(msg.net.GClientAnnouceServerInfo.TYPE_ID, msg.net.GClientAnnouceServerInfo::new); 
          
       plink.put(msg.plink.LAnnounceOuterNetAddress.TYPE_ID, msg.plink.LAnnounceOuterNetAddress::new); 
          plink.put(msg.plink.LUserOnline.TYPE_ID, msg.plink.LUserOnline::new); 
          plink.put(msg.plink.LForward.TYPE_ID, msg.plink.LForward::new); 
          plink.put(msg.plink.GForward.TYPE_ID, msg.plink.GForward::new); 
          plink.put(msg.plink.GMulticast.TYPE_ID, msg.plink.GMulticast::new); 
          plink.put(msg.plink.LLinkBroken.TYPE_ID, msg.plink.LLinkBroken::new); 
          plink.put(msg.plink.GKickUser.TYPE_ID, msg.plink.GKickUser::new); 
          plink.put(msg.plink.GUnBind.TYPE_ID, msg.plink.GUnBind::new); 
          plink.put(msg.net.GClientAnnouceServerInfo.TYPE_ID, msg.net.GClientAnnouceServerInfo::new); 
          plink.put(msg.plink.GBroadcast.TYPE_ID, msg.plink.GBroadcast::new); 
          plink.put(msg.plink.GUserOnline.TYPE_ID, msg.plink.GUserOnline::new); 
          plink.put(msg.plink.LUserKeepAlive.TYPE_ID, msg.plink.LUserKeepAlive::new); 
          plink.put(msg.net.GServerAnnouceServerInfo.TYPE_ID, msg.net.GServerAnnouceServerInfo::new); 
          plink.put(msg.plink.GBind.TYPE_ID, msg.plink.GBind::new); 
          
       map.put(msg.map.SMapDebugInfo.TYPE_ID, msg.map.SMapDebugInfo::new); 
          map.put(msg.map.SDebugException.TYPE_ID, msg.map.SDebugException::new); 
          
       mapServer.put(msg.plink.LAnnounceOuterNetAddress.TYPE_ID, msg.plink.LAnnounceOuterNetAddress::new); 
          mapServer.put(msg.map.SMapDebugInfo.TYPE_ID, msg.map.SMapDebugInfo::new); 
          mapServer.put(msg.plink.LUserOnline.TYPE_ID, msg.plink.LUserOnline::new); 
          mapServer.put(msg.plink.LForward.TYPE_ID, msg.plink.LForward::new); 
          mapServer.put(msg.plink.GForward.TYPE_ID, msg.plink.GForward::new); 
          mapServer.put(msg.gmap.MGMessage.TYPE_ID, msg.gmap.MGMessage::new); 
          mapServer.put(msg.gmap.GMMessage.TYPE_ID, msg.gmap.GMMessage::new); 
          mapServer.put(msg.plink.GMulticast.TYPE_ID, msg.plink.GMulticast::new); 
          mapServer.put(msg.plink.LLinkBroken.TYPE_ID, msg.plink.LLinkBroken::new); 
          mapServer.put(msg.plink.GKickUser.TYPE_ID, msg.plink.GKickUser::new); 
          mapServer.put(msg.plink.GUnBind.TYPE_ID, msg.plink.GUnBind::new); 
          mapServer.put(msg.map.SDebugException.TYPE_ID, msg.map.SDebugException::new); 
          mapServer.put(msg.net.GClientAnnouceServerInfo.TYPE_ID, msg.net.GClientAnnouceServerInfo::new); 
          mapServer.put(msg.plink.GBroadcast.TYPE_ID, msg.plink.GBroadcast::new); 
          mapServer.put(msg.plink.GUserOnline.TYPE_ID, msg.plink.GUserOnline::new); 
          mapServer.put(msg.plink.LUserKeepAlive.TYPE_ID, msg.plink.LUserKeepAlive::new); 
          mapServer.put(msg.net.GServerAnnouceServerInfo.TYPE_ID, msg.net.GServerAnnouceServerInfo::new); 
          mapServer.put(msg.plink.GBind.TYPE_ID, msg.plink.GBind::new); 
          mapServer.put(msg.gmap.GCfgReload.TYPE_ID, msg.gmap.GCfgReload::new); 
          
     
    }
}