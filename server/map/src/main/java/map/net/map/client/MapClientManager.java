package map.net.map.client;

import com.google.gson.JsonObject;
import io.netty.channel.nio.NioEventLoopGroup;
import map.cfg.BootConfig;
import map.net.map.util;
import msg.Refs;
import msg.gmap.MGMapInfos;
import msg.gmap.MGMessage;
import msg.gmap.MapInfo;
import msg.net.GClientAnnouceServerInfo;
import msg.net.GServerAnnouceServerInfo;
import pcore.collection.Int2ObjectHashMap;
import pcore.collection.IntList;
import pcore.io.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MapClientManager extends DynamicMultiClientManager {
    private static MapClientManager inst;
    private final Conf conf;


    public static MapClientManager getInst() {
        return inst;
    }



    public static void start(NioEventLoopGroup workGroup) {
        if (inst == null) {
            Conf conf = new Conf();
            conf.init(workGroup, new Int2ObjectHashMap<>(Refs.mmap), getDispatcher());
            inst = new MapClientManager(conf);
            //inst.updateServers(conf.getServers());
        }
    }

    public MapClientManager(Conf conf) {
        this.conf = conf;
        getDispatcher().register(GServerAnnouceServerInfo.class, this::process);
    }

    public Conf getConf() {
        return conf;
    }
    public void updateServers() {
        inst.updateServers(conf.getServers());
    }

    protected DynamicClient newClient(String name, Client.Conf config) {
        return new MapClient(this, name, config);
    }

    @Override
    protected void onAddClient(DynamicClient client) {
        super.onAddClient(client);
        GClientAnnouceServerInfo p = new GClientAnnouceServerInfo(BootConfig.getIns().getServerId());
        client.send(p);
    }

    @Override
    protected void onDelClient(DynamicClient client) {
        super.onDelClient(client);
    }

    public static ProtocolDispatcher getDispatcher() {
        return util.dispatcher;
    }


    private void process(GServerAnnouceServerInfo p) {
        register(p.serverId, (MapClient)((Session)p.getContext()).getConnection().getManager(), p.keepAliveInterval);
    }


    public static class Conf extends DynamicMultiClientManager.Conf {

        @Override
        public Client.Conf createConf() {
            return new Client.Conf();
        }
    }


}
