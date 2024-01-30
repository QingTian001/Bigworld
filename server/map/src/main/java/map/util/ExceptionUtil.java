package map.util;

import msg.map.SDebugException;
import pcore.db.Trace;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

/**
 * Created by zyao on 2020/5/4 21:28
 */
public final class ExceptionUtil {

    public static SDebugException toProtocol(Throwable t) {
        try (ByteArrayOutputStream bao = new ByteArrayOutputStream(512)) {
            PrintStream ps = new PrintStream(bao, true, StandardCharsets.UTF_8);
            t.printStackTrace(ps);
            var stackTrace = new String(bao.toByteArray(), StandardCharsets.UTF_8);
            var message = t.getMessage() == null? "null" : t.getMessage();
            Trace.error("Handle msg Exception {} {}", message, stackTrace);
            return new SDebugException(message, stackTrace);
        }catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
