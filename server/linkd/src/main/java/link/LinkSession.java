package link;

import com.mafia.serverex.util.FrequencyControl;
import jdk.jfr.Frequency;
import main.BootConfig;
import pcore.collection.Int2ObjectHashMap;
import pcore.io.Connection;
import pcore.io.DynamicMultiClientManager;
import pcore.io.Protocol;
import pcore.io.Session;
import pcore.marshal.Octets;
import provider.ProviderServer;
import provider.ProviderSession;
import provider.ProviderSessionMap;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zyao on 2020/2/7 17:08
 */
public final class LinkSession extends Session {

    public enum STATE {
        NONE,
        RECV_ACCOUNT_CHECK,
        SEND_ACCOUNT_CHECK_RESULT,
        RECV_TOKEN_CHECK,
        LOGIN,
        FORWARD,
    }

    private DynamicMultiClientManager.DynamicClient authClient;
    private long userId;
    //private final ConcurrentHashMap<Integer, ProviderSession> providerMap = new ConcurrentHashMap<>(); // 为了优化CForward

    private ProviderSessionMap providerMap = new ProviderSessionMap();
    // 这里只有state使用volatile, 其他变量都用锁保护, 这里用volatile, 主要是为了优化CForward
    private volatile STATE state = STATE.NONE;
    private ProviderSession gsSession;
    private int gsServerId;
    private boolean isReconnect;
    private byte[] extraInfo = null;
    private final LinkLimiter limiter;

    public int getGsServerId() {
        return gsServerId;
    }

    public LinkLimiter getLimiter() {
        return limiter;
    }

    public void setGsServerId(int gsServerId) {
        this.gsServerId = gsServerId;
    }

    public STATE getState() {
        return state;
    }

    public void setState(STATE state) {
        this.state = state;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getUserId() {
        return this.userId;
    }

    public boolean isReconnect() {
        return isReconnect;
    }

    public void setReconnect(boolean isReconnect) {
        this.isReconnect = isReconnect;
    }

    public DynamicMultiClientManager.DynamicClient getAuthClient() {
        return authClient;
    }

    public void setAuthClient(DynamicMultiClientManager.DynamicClient authClient) {
        this.authClient = authClient;
    }

    public LinkSession(Connection connection) {
        super(connection);

        limiter = new LinkLimiter(BootConfig.getIns().getLinkConf(), this);
    }

    public void addProviderSession(int pvid, ProviderSession session) {
        providerMap.putProviderSession(pvid, session);
    }

    public void removeProviderSession(int pvid) {
        providerMap.removeProviderSession(pvid);
    }

    public ConcurrentHashMap<Integer, ProviderSession> getProviderMap() {
        return providerMap.getProviderSessionMap();
    }

    public ProviderSession getProviderSession(int pvid) {
        return providerMap.getProviderSession(pvid);
    }

    public void forwardToGs(Protocol p) {
        if (gsSession == null) {
            gsSession = ProviderServer.getInst().getServer(gsServerId);
        }
        if (gsSession != null && !gsSession.send(p)) {
            gsSession = null;
        }
    }

    public byte[] getExtraInfo() {
        return extraInfo;
    }

    void setExtraInfo(byte[] extraInfo) {
        this.extraInfo = extraInfo;
    }
}
