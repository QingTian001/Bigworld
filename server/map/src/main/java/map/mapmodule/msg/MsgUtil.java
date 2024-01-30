package map.mapmodule.msg;

import map.mapmodule.Module;
import map.mapmodule.role.Role;
import map.net.gs.GsManager;
import map.net.gs.GsSession;
import map.net.link.LinkManager;
import map.net.link.LinkSession;
import map.util.PackageScanner;
import map.util.ReflectionUtil;
import msg.Refs;
import pcore.db.Trace;
import pcore.io.IProcessor;
import pcore.io.Protocol;
import pcore.io.ProtocolDispatcher;

import java.lang.reflect.Modifier;

/**
 * Created by zyao on 2019/11/6 17:57
 */
public final class MsgUtil {

    @SuppressWarnings("unchecked")
    public static void init() {
        for (Class<?> clazz : PackageScanner.scan("map.mapmodule.msg", true)) {
            if (MsgHandler.class.isAssignableFrom(clazz) && (!Modifier.isAbstract(clazz.getModifiers()))) {

                Class<? extends Protocol> c = ReflectionUtil.getSuperGenericType(clazz);

                ProtocolDispatcher dispatcher;
                try {
                    int typeId = (int)c.getField("TYPE_ID").get(null);
                    if (Refs.gmap.containsKey(typeId)) {
                        dispatcher = GsManager.getDispatcher();

                        dispatcher.register(c, (IProcessor) p -> {
                            GsMsgContext context = (GsMsgContext) p.getContext();
                            long mapId = context.getMapId();
                            GsSession gsSession = context.getGsSession();
                            GsMsgHandler<? extends Protocol> handler;
                            try {
                                handler = (GsMsgHandler<? extends Protocol>)clazz.getDeclaredConstructor().newInstance();
                            } catch (Exception e) {
                                throw new RuntimeException("GsMsgHandler construct failed. class:" + c.getName());
                            }
                            handler.setContext(mapId, gsSession, p);
                            Module.Ins.getTaskQueue(mapId).add(handler);
                        });
                    }
                    else if (Refs.map.containsKey(typeId)) {
                        dispatcher = LinkManager.getDispatcher();

                        dispatcher.register(c, (IProcessor) p -> {
                            LinkMsgContext context = (LinkMsgContext) p.getContext();
                            long linkSid = context.getLinkSid();
                            LinkSession linkSession = context.getLinkSession();
                            LinkMsgHandler<? extends Protocol> handler;
                            try {
                                handler = (LinkMsgHandler<? extends Protocol>)clazz.getDeclaredConstructor().newInstance();
                            } catch (Exception e) {
                                throw new RuntimeException("GsMsgHandler construct failed. class:" + c.getName());
                            }
                            Role role = Module.Ins.getRole(linkSession, linkSid);
                            if (role == null) {
                                Trace.warn("role is null. linkServerId:{}, linkSid:{}", linkSession.getServerId(), linkSid);
                                return;
                            }
                            handler.setContext(role, p);
                            role.getMap().safeExecute(handler);
                        });
                    }
                    else {
                        throw new RuntimeException("error protocol typeId:" + typeId);
                    }
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
            // else continue
        }
    }
}
