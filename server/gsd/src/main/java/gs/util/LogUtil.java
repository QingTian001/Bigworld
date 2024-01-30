package gs.util;

import gs.cfg.BootConfig;
import pcore.db.Trace;
import pcore.io.Protocol;

import java.util.Arrays;
import java.util.List;

/**
 * Created by HuangQiang on 2018/12/5 15:12
 */

public class LogUtil {
    public static void debugPrintProtocol(String fmt, Protocol Protocol) {
        if (!filterProt(Protocol)) Trace.debug(fmt, Protocol);
    }

    public static void debugPrintRoleProtocol(String fmt, long roleid, Protocol Protocol) {
        if (!filterProt(Protocol)) Trace.debug(fmt, roleid, Protocol);
    }

    public static void infoPrintLForwardProtocol(long sid, long userId, long roleId, Protocol p) {
        String msg = filterProt(p) ? p.getClass().getName() : p.toString();
        Trace.info("lforward recv linkSid:{} userId:{} roleId:{} msg: {}", sid, userId, roleId, msg);
    }

    public static void infoPrintGForwardProtocol(long userId, long roleId, Protocol p) {
        String msg = filterProt(p) ? p.getClass().getName() : p.toString();
        Trace.info("gforward send userId:{} roleId:{} {}", userId, roleId, msg);
    }

    private static boolean filterProt(Protocol p) {
        return !BootConfig.getIns().isDebug() && filterProtocols.contains(p.getClass());
    }

    private final static List<Class<?>> filterProtocols = Arrays.asList (

    );
}
