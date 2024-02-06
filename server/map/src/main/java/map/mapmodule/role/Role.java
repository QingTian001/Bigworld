package map.mapmodule.role;

import com.mafia.serverex.metrics.MetricsHelper;
import map.mapmodule.Module;
import map.mapmodule.map.GMap;
import map.net.gs.GsSession;
import map.net.link.LinkManager;
import map.net.link.LinkSession;
import msg.Refs;
import msg.plink.GBind;
import msg.plink.GForward;
import msg.plink.GUnBind;
import pcore.db.Trace;
import pcore.io.Protocol;
import pcore.io.ProtocolUtils;

/**
 * Created by zyao on 2019/11/8 17:08
 */
public class Role {

    private static long sendCount = 0;

    private final long roleId;
    private GsSession gsSession;
    private LinkSession linkSession;
    private long linkSid;
    private int linkServerId;
    private final GMap map;
    private volatile boolean linkOnline;

    public Role(GMap map, long roleId) {
        this.roleId = roleId;
        this.map = map;
    }

    public synchronized void setLinkOnline(boolean linkOnline) {
        this.linkOnline = linkOnline;
    }

    public final long getRoleId() {
        return roleId;
    }

    public final GMap getMap() {
        return map;
    }

    public final boolean sendProtocol(Protocol p) {
        if (!linkOnline) {
            return false;
        }
        Trace.debug("[sendToClient] {} : {}", p.getClass(), p);
        MetricsHelper.getCounter("map_link_send_count").set(++sendCount);
        return linkSession.send(new GForward(linkSid, ProtocolUtils.encode2Bytes(p)));
    }

    public final void sendToGs(Protocol p) {
        Module.Ins.sendToGs(gsSession, map.getInstId(), p);
    }

    public synchronized void enterMap(int linkServerId, long linkSid, GsSession gsSession) {
        Trace.info("[Role] enterMap. linkServerId:{}, linkSid:{}, oldLinkServerId:{}", linkServerId, linkSid, this.linkServerId);
        this.gsSession = gsSession;
        if(roleId == 0){
            this.linkOnline = false;
            return;
        }
        LinkSession oldLinkSession = this.linkSession;
        long oldLinkSid = this.linkSid;
        this.linkSession = (LinkSession) LinkManager.getInstance().getServerByServerId(linkServerId).getSession();
        this.linkServerId = linkServerId;
        this.linkSid = linkSid;

        if (oldLinkSession != null) {
            unbind(oldLinkSession, oldLinkSid);
        }

        bind(this, this.linkSession, this.linkSid);
        this.linkOnline = true;
    }

    public synchronized void leaveMap() {
        if(roleId != 0) {
            unbind(this.linkSession, this.linkSid);
        }
        this.linkSession = null;
        this.linkSid = 0;
        this.gsSession = null;
        this.linkOnline = false;
    }

    private static void bind(Role role, LinkSession bindLinkSession, long bindLinkSid) {
        int bindLinkServerId = bindLinkSession.getServerId();
        Trace.info("[Role] bind. linkServerId:{}, linkSid:{}", bindLinkServerId, bindLinkSid);
        Module.Ins.bindRole(bindLinkServerId, bindLinkSid, role);
        GBind bind = new GBind();
        bind.pvid = Refs.mapProviderId;
        bind.lindSids.add(bindLinkSid);
        bindLinkSession.send(bind);
    }

    private static void unbind(LinkSession unBindLinkSession, long unBindLinkSid) {
        int unBindLinkServerId = unBindLinkSession.getServerId();
        Trace.info("[Role] unbind. linkServerId:{}, linkSid:{}", unBindLinkServerId, unBindLinkSid);
        Module.Ins.unBindRole(unBindLinkServerId, unBindLinkSid);
        GUnBind unBind = new GUnBind();
        unBind.pvid = Refs.mapProviderId;
        unBind.lindSids.add(unBindLinkSid);
        unBindLinkSession.send(unBind);
    }


}
