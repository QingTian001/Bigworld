package msg;

public final class Refs {
    public static final java.util.HashMap<Integer, pcore.io.IProtocolFactory> gsau = new java.util.HashMap<>(); 
    public static final java.util.HashMap<Integer, pcore.io.IProtocolFactory> serverlist = new java.util.HashMap<>(); 
    public static final java.util.HashMap<Integer, pcore.io.IProtocolFactory> plink = new java.util.HashMap<>(); 
    public static final java.util.HashMap<Integer, pcore.io.IProtocolFactory> gs = new java.util.HashMap<>(); 
    public static final java.util.HashMap<Integer, pcore.io.IProtocolFactory> map = new java.util.HashMap<>(); 
    public static final java.util.HashMap<Integer, pcore.io.IProtocolFactory> gsServer = new java.util.HashMap<>(); 

    public static final int gsProviderId = 11;
    public static final int mapProviderId = 12;

    static {
      gsau.put(msg.gsau.GUserLogin.TYPE_ID, msg.gsau.GUserLogin::new); 
          gsau.put(msg.gsau.GSwitchAvatarNotify.TYPE_ID, msg.gsau.GSwitchAvatarNotify::new); 
          gsau.put(msg.gsau.GPullRecharge.TYPE_ID, msg.gsau.GPullRecharge::new); 
          gsau.put(msg.net.GClientAnnouceServerInfo.TYPE_ID, msg.net.GClientAnnouceServerInfo::new); 
          gsau.put(msg.gsau.GUserUpgradeNotify.TYPE_ID, msg.gsau.GUserUpgradeNotify::new); 
          gsau.put(msg.gsau.GRechargeOrder.TYPE_ID, msg.gsau.GRechargeOrder::new); 
          gsau.put(msg.gsau.APullRecharge.TYPE_ID, msg.gsau.APullRecharge::new); 
          gsau.put(msg.net.GServerAnnouceServerInfo.TYPE_ID, msg.net.GServerAnnouceServerInfo::new); 
          gsau.put(msg.gsau.GPullRechargeSuccess.TYPE_ID, msg.gsau.GPullRechargeSuccess::new); 
          gsau.put(msg.gsau.ARechargeOrder.TYPE_ID, msg.gsau.ARechargeOrder::new); 
          
       serverlist.put(msg.serverlist.G2SShowMarquee.TYPE_ID, msg.serverlist.G2SShowMarquee::new); 
          serverlist.put(msg.serverlist.GCloseStatistic.TYPE_ID, msg.serverlist.GCloseStatistic::new); 
          serverlist.put(msg.serverlist.S2GTest.TYPE_ID, msg.serverlist.S2GTest::new); 
          serverlist.put(msg.serverlist.GAnnouceGsServerStart.TYPE_ID, msg.serverlist.GAnnouceGsServerStart::new); 
          serverlist.put(msg.serverlist.GAnnouceGsServerlocalId.TYPE_ID, msg.serverlist.GAnnouceGsServerlocalId::new); 
          serverlist.put(msg.serverlist.G2SCreateAccount.TYPE_ID, msg.serverlist.G2SCreateAccount::new); 
          serverlist.put(msg.serverlist.GAnnouceGsServerInfo.TYPE_ID, msg.serverlist.GAnnouceGsServerInfo::new); 
          serverlist.put(msg.serverlist.S2GCancelSealAccount.TYPE_ID, msg.serverlist.S2GCancelSealAccount::new); 
          serverlist.put(msg.serverlist.S2GPushGifts.TYPE_ID, msg.serverlist.S2GPushGifts::new); 
          serverlist.put(msg.serverlist.G2SMailSendRoles.TYPE_ID, msg.serverlist.G2SMailSendRoles::new); 
          serverlist.put(msg.serverlist.G2SQueryRoleMail.TYPE_ID, msg.serverlist.G2SQueryRoleMail::new); 
          serverlist.put(msg.serverlist.G2SMailSendServer.TYPE_ID, msg.serverlist.G2SMailSendServer::new); 
          serverlist.put(msg.serverlist.S2GGsMailDel.TYPE_ID, msg.serverlist.S2GGsMailDel::new); 
          serverlist.put(msg.serverlist.G2SPushGifts.TYPE_ID, msg.serverlist.G2SPushGifts::new); 
          serverlist.put(msg.serverlist.S2GGameOperate.TYPE_ID, msg.serverlist.S2GGameOperate::new); 
          serverlist.put(msg.serverlist.G2SSealAcount.TYPE_ID, msg.serverlist.G2SSealAcount::new); 
          serverlist.put(msg.serverlist.S2GShowMarquee.TYPE_ID, msg.serverlist.S2GShowMarquee::new); 
          serverlist.put(msg.serverlist.S2GSealAcount.TYPE_ID, msg.serverlist.S2GSealAcount::new); 
          serverlist.put(msg.serverlist.G2SGsMailDel.TYPE_ID, msg.serverlist.G2SGsMailDel::new); 
          serverlist.put(msg.serverlist.G2SCancelSealAccount.TYPE_ID, msg.serverlist.G2SCancelSealAccount::new); 
          serverlist.put(msg.serverlist.S2GCustomServiceForward.TYPE_ID, msg.serverlist.S2GCustomServiceForward::new); 
          serverlist.put(msg.serverlist.GAddGsServerLinkInfo.TYPE_ID, msg.serverlist.GAddGsServerLinkInfo::new); 
          serverlist.put(msg.serverlist.S2GQueryRoleMail.TYPE_ID, msg.serverlist.S2GQueryRoleMail::new); 
          serverlist.put(msg.net.GServerAnnouceServerInfo.TYPE_ID, msg.net.GServerAnnouceServerInfo::new); 
          serverlist.put(msg.serverlist.GAnnouceGsServerState.TYPE_ID, msg.serverlist.GAnnouceGsServerState::new); 
          serverlist.put(msg.serverlist.S2GMailSendServer.TYPE_ID, msg.serverlist.S2GMailSendServer::new); 
          serverlist.put(msg.serverlist.G2SGameOperate.TYPE_ID, msg.serverlist.G2SGameOperate::new); 
          serverlist.put(msg.serverlist.GReload.TYPE_ID, msg.serverlist.GReload::new); 
          serverlist.put(msg.serverlist.G2STest.TYPE_ID, msg.serverlist.G2STest::new); 
          serverlist.put(msg.serverlist.G2SCustomMailDel.TYPE_ID, msg.serverlist.G2SCustomMailDel::new); 
          serverlist.put(msg.serverlist.S2GMailSendRoles.TYPE_ID, msg.serverlist.S2GMailSendRoles::new); 
          serverlist.put(msg.serverlist.GRmvGsServerLinkInfo.TYPE_ID, msg.serverlist.GRmvGsServerLinkInfo::new); 
          serverlist.put(msg.net.GClientAnnouceServerInfo.TYPE_ID, msg.net.GClientAnnouceServerInfo::new); 
          serverlist.put(msg.serverlist.GOpenStatistic.TYPE_ID, msg.serverlist.GOpenStatistic::new); 
          serverlist.put(msg.serverlist.S2GCustomMailDel.TYPE_ID, msg.serverlist.S2GCustomMailDel::new); 
          serverlist.put(msg.serverlist.G2SCustomServiceForward.TYPE_ID, msg.serverlist.G2SCustomServiceForward::new); 
          
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
          
       gs.put(msg.gs.login.SUserLoginSuccess.TYPE_ID, msg.gs.login.SUserLoginSuccess::new); 
          gs.put(msg.gs.gm.SGetModuleList.TYPE_ID, msg.gs.gm.SGetModuleList::new); 
          gs.put(msg.gs.SPong.TYPE_ID, msg.gs.SPong::new); 
          gs.put(msg.gs.gm.CExecuteCommand.TYPE_ID, msg.gs.gm.CExecuteCommand::new); 
          gs.put(msg.gs.gm.SExecuteCommand.TYPE_ID, msg.gs.gm.SExecuteCommand::new); 
          gs.put(msg.gs.CPing.TYPE_ID, msg.gs.CPing::new); 
          gs.put(msg.gs.gm.CGetModuleList.TYPE_ID, msg.gs.gm.CGetModuleList::new); 
          gs.put(msg.gs.SError.TYPE_ID, msg.gs.SError::new); 
          gs.put(msg.gs.login.SUserLogin.TYPE_ID, msg.gs.login.SUserLogin::new); 
          
       
       gsServer.put(msg.serverlist.GCloseStatistic.TYPE_ID, msg.serverlist.GCloseStatistic::new); 
          gsServer.put(msg.gs.gm.CExecuteCommand.TYPE_ID, msg.gs.gm.CExecuteCommand::new); 
          gsServer.put(msg.serverlist.G2SCreateAccount.TYPE_ID, msg.serverlist.G2SCreateAccount::new); 
          gsServer.put(msg.serverlist.GAnnouceGsServerInfo.TYPE_ID, msg.serverlist.GAnnouceGsServerInfo::new); 
          gsServer.put(msg.serverlist.S2GCancelSealAccount.TYPE_ID, msg.serverlist.S2GCancelSealAccount::new); 
          gsServer.put(msg.serverlist.G2SMailSendRoles.TYPE_ID, msg.serverlist.G2SMailSendRoles::new); 
          gsServer.put(msg.gsau.GPullRechargeSuccess.TYPE_ID, msg.gsau.GPullRechargeSuccess::new); 
          gsServer.put(msg.plink.LAnnounceOuterNetAddress.TYPE_ID, msg.plink.LAnnounceOuterNetAddress::new); 
          gsServer.put(msg.serverlist.G2SQueryRoleMail.TYPE_ID, msg.serverlist.G2SQueryRoleMail::new); 
          gsServer.put(msg.serverlist.G2SMailSendServer.TYPE_ID, msg.serverlist.G2SMailSendServer::new); 
          gsServer.put(msg.serverlist.S2GGsMailDel.TYPE_ID, msg.serverlist.S2GGsMailDel::new); 
          gsServer.put(msg.serverlist.G2SSealAcount.TYPE_ID, msg.serverlist.G2SSealAcount::new); 
          gsServer.put(msg.gsau.GUserUpgradeNotify.TYPE_ID, msg.gsau.GUserUpgradeNotify::new); 
          gsServer.put(msg.gs.CPing.TYPE_ID, msg.gs.CPing::new); 
          gsServer.put(msg.gs.login.SUserLogin.TYPE_ID, msg.gs.login.SUserLogin::new); 
          gsServer.put(msg.gs.SPong.TYPE_ID, msg.gs.SPong::new); 
          gsServer.put(msg.plink.LForward.TYPE_ID, msg.plink.LForward::new); 
          gsServer.put(msg.serverlist.G2SCancelSealAccount.TYPE_ID, msg.serverlist.G2SCancelSealAccount::new); 
          gsServer.put(msg.serverlist.S2GCustomServiceForward.TYPE_ID, msg.serverlist.S2GCustomServiceForward::new); 
          gsServer.put(msg.serverlist.S2GQueryRoleMail.TYPE_ID, msg.serverlist.S2GQueryRoleMail::new); 
          gsServer.put(msg.serverlist.G2SGameOperate.TYPE_ID, msg.serverlist.G2SGameOperate::new); 
          gsServer.put(msg.serverlist.GReload.TYPE_ID, msg.serverlist.GReload::new); 
          gsServer.put(msg.serverlist.G2STest.TYPE_ID, msg.serverlist.G2STest::new); 
          gsServer.put(msg.plink.LUserOnline.TYPE_ID, msg.plink.LUserOnline::new); 
          gsServer.put(msg.serverlist.S2GMailSendRoles.TYPE_ID, msg.serverlist.S2GMailSendRoles::new); 
          gsServer.put(msg.serverlist.GRmvGsServerLinkInfo.TYPE_ID, msg.serverlist.GRmvGsServerLinkInfo::new); 
          gsServer.put(msg.plink.LLinkBroken.TYPE_ID, msg.plink.LLinkBroken::new); 
          gsServer.put(msg.gsau.GRechargeOrder.TYPE_ID, msg.gsau.GRechargeOrder::new); 
          gsServer.put(msg.serverlist.GOpenStatistic.TYPE_ID, msg.serverlist.GOpenStatistic::new); 
          gsServer.put(msg.serverlist.S2GCustomMailDel.TYPE_ID, msg.serverlist.S2GCustomMailDel::new); 
          gsServer.put(msg.gs.gm.CGetModuleList.TYPE_ID, msg.gs.gm.CGetModuleList::new); 
          gsServer.put(msg.gsau.ARechargeOrder.TYPE_ID, msg.gsau.ARechargeOrder::new); 
          gsServer.put(msg.serverlist.G2SCustomServiceForward.TYPE_ID, msg.serverlist.G2SCustomServiceForward::new); 
          gsServer.put(msg.serverlist.G2SShowMarquee.TYPE_ID, msg.serverlist.G2SShowMarquee::new); 
          gsServer.put(msg.gsau.GUserLogin.TYPE_ID, msg.gsau.GUserLogin::new); 
          gsServer.put(msg.gsau.GSwitchAvatarNotify.TYPE_ID, msg.gsau.GSwitchAvatarNotify::new); 
          gsServer.put(msg.gs.gm.SGetModuleList.TYPE_ID, msg.gs.gm.SGetModuleList::new); 
          gsServer.put(msg.serverlist.S2GTest.TYPE_ID, msg.serverlist.S2GTest::new); 
          gsServer.put(msg.serverlist.GAnnouceGsServerStart.TYPE_ID, msg.serverlist.GAnnouceGsServerStart::new); 
          gsServer.put(msg.serverlist.GAnnouceGsServerlocalId.TYPE_ID, msg.serverlist.GAnnouceGsServerlocalId::new); 
          gsServer.put(msg.serverlist.S2GPushGifts.TYPE_ID, msg.serverlist.S2GPushGifts::new); 
          gsServer.put(msg.gsau.APullRecharge.TYPE_ID, msg.gsau.APullRecharge::new); 
          gsServer.put(msg.plink.GUserOnline.TYPE_ID, msg.plink.GUserOnline::new); 
          gsServer.put(msg.gs.login.SUserLoginSuccess.TYPE_ID, msg.gs.login.SUserLoginSuccess::new); 
          gsServer.put(msg.gs.gm.SExecuteCommand.TYPE_ID, msg.gs.gm.SExecuteCommand::new); 
          gsServer.put(msg.serverlist.G2SPushGifts.TYPE_ID, msg.serverlist.G2SPushGifts::new); 
          gsServer.put(msg.plink.GUnBind.TYPE_ID, msg.plink.GUnBind::new); 
          gsServer.put(msg.serverlist.S2GGameOperate.TYPE_ID, msg.serverlist.S2GGameOperate::new); 
          gsServer.put(msg.serverlist.S2GShowMarquee.TYPE_ID, msg.serverlist.S2GShowMarquee::new); 
          gsServer.put(msg.serverlist.S2GSealAcount.TYPE_ID, msg.serverlist.S2GSealAcount::new); 
          gsServer.put(msg.serverlist.G2SGsMailDel.TYPE_ID, msg.serverlist.G2SGsMailDel::new); 
          gsServer.put(msg.plink.GForward.TYPE_ID, msg.plink.GForward::new); 
          gsServer.put(msg.plink.GMulticast.TYPE_ID, msg.plink.GMulticast::new); 
          gsServer.put(msg.plink.GKickUser.TYPE_ID, msg.plink.GKickUser::new); 
          gsServer.put(msg.serverlist.GAddGsServerLinkInfo.TYPE_ID, msg.serverlist.GAddGsServerLinkInfo::new); 
          gsServer.put(msg.net.GServerAnnouceServerInfo.TYPE_ID, msg.net.GServerAnnouceServerInfo::new); 
          gsServer.put(msg.serverlist.GAnnouceGsServerState.TYPE_ID, msg.serverlist.GAnnouceGsServerState::new); 
          gsServer.put(msg.serverlist.S2GMailSendServer.TYPE_ID, msg.serverlist.S2GMailSendServer::new); 
          gsServer.put(msg.gsau.GPullRecharge.TYPE_ID, msg.gsau.GPullRecharge::new); 
          gsServer.put(msg.serverlist.G2SCustomMailDel.TYPE_ID, msg.serverlist.G2SCustomMailDel::new); 
          gsServer.put(msg.net.GClientAnnouceServerInfo.TYPE_ID, msg.net.GClientAnnouceServerInfo::new); 
          gsServer.put(msg.plink.GBroadcast.TYPE_ID, msg.plink.GBroadcast::new); 
          gsServer.put(msg.plink.LUserKeepAlive.TYPE_ID, msg.plink.LUserKeepAlive::new); 
          gsServer.put(msg.gs.SError.TYPE_ID, msg.gs.SError::new); 
          gsServer.put(msg.plink.GBind.TYPE_ID, msg.plink.GBind::new); 
          
     
    }
}