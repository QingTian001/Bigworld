package gs.net.aud;

import com.google.gson.JsonObject;
import gs.cfg.BootConfig;
import io.netty.channel.nio.NioEventLoopGroup;
import msg.gsau.APullRecharge;
import msg.gsau.ARechargeOrder;
import msg.net.GClientAnnouceServerInfo;
import msg.net.GServerAnnouceServerInfo;
import pcore.collection.Int2ObjectHashMap;
import pcore.db.Trace;
import pcore.io.*;

/**
 * Created by zhanghuaizheng on 2020/1/7 10:38
 */
public class AudClient extends Client<Session>{
    private static AudClient instance;
    private static final ProtocolDispatcher dispatcher = new pcore.io.ProtocolDispatcher();

    private AudClient(Client.Conf conf) {
        super(conf);
        dispatcher.register(GServerAnnouceServerInfo.class, this::process);
        dispatcher.register(APullRecharge.class, this::process);
        dispatcher.register(ARechargeOrder.class, this::process);

    }

    public static ProtocolDispatcher getDispatcher() {
        return dispatcher;
    }

    public static AudClient getInstance() {
        return instance;
    }

    public static void start(Conf conf) {
        if (instance == null) {
            instance = new AudClient(conf);
            instance.open();
        }
    }

    private void process(GServerAnnouceServerInfo msg) {
        Trace.info("Connect Aud serverId: {} success!", msg.serverId);
        startKeepAlive(msg.keepAliveInterval);
    }

    private void process(APullRecharge msg)  {
        if (!msg.recharges.isEmpty()) {
//            RechargeHandler.handlePull(msg.recharges);
        }
    }

    private void process(ARechargeOrder msg)  {

        //new AuGenRechargeOrderFinish(msg).call();
    }

    @Override
    protected Session newSession(Connection connection) {
        return new Session(connection);
    }

    @Override
    protected void onAddSession(Session session) {
        GClientAnnouceServerInfo annouceServerInfo = new GClientAnnouceServerInfo();
        annouceServerInfo.serverId = BootConfig.getIns().getServerId();
        session.send(annouceServerInfo);

        //RechargeHandler.startPull();
    }

    @Override
    protected void onDelSession(Session session) {
        Trace.info(" aud onDelSession");
        //RechargeHandler.endPull();
    }

    public static class Conf extends Client.Conf{

        public void parse(JsonObject jo) {
            super.parse(jo.getAsJsonObject("server"));
        }

        public void init(NioEventLoopGroup workGroup, ProtocolDispatcher dispatcher, Int2ObjectHashMap<IProtocolFactory> stubs) {
            super.workGroup = workGroup;
            super.dispatcher = dispatcher;
            super.stubs = stubs;
        }
    }
}
