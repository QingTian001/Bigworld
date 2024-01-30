package gs.net.link;

import com.mafia.serverex.metrics.MetricsHelper;
import msg.net.EErrorCode;
import pcore.db.Trace;
import pcore.io.Protocol;
import pcore.io.ProtocolDispatcher;
import pcore.io.Session;

/**
 * Created by zyao on 2020/2/26 15:23
 */
public class GProtocolDispatcher extends ProtocolDispatcher {

    @Override
    public void dispatch(Protocol protocol) {
        Class<? extends Protocol> clz = protocol.getClass();
        Trace.debug("[GProtocolDispatcher] == recv {} : {}", clz.getName(), protocol);
        var processor = this.getProcessors().get(clz);
        if (processor != null) {
            var timer = MetricsHelper.getTimer("gs_protocol_duration");
            timer.start();
            try {
                processor.handle(protocol);
            } finally {
                timer.end();
            }

        } else {
            Trace.error("[GProtocolDispatcher] recv:{} can't find handler", clz.getName());

            Object context = protocol.getContext();
            if (context instanceof LinkUser) {
                LinkUser linkUser = (LinkUser)context;
                LinkManager.getIns().kickUser(linkUser, EErrorCode.PROTOCOL_EXCEPTION, true);
            }
            else if (context instanceof Session) {
                Session session = (Session) protocol.getContext();
                session.close();
            }
            else {
                throw new RuntimeException("error protocol context");
            }
        }
    }
}
