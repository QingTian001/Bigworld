package map.mapmodule.msg.handler;

import map.mapmodule.map.GMap;
import map.mapmodule.msg.GsMapMsgHandler;
import map.net.gs.GsSession;
import msg.gmap.GReLogin;
import pcore.db.Trace;

/**
 * Created by zyao on 2020/8/17 16:17
 */
public class GReLoginHandler extends GsMapMsgHandler<GReLogin> {
    @Override
    protected void doProcess(GMap map, GsSession gsSession, GReLogin p) {
        //Trace.info("ID{} reLogin roleId {} linkSid {} linkServerId {}",map.getId(),p.roleId,p.newLinkSid,p.newLinkServerId);
        //map.reLogin(gsSession, p);
    }
}
