package provider;

import pcore.collection.LongConcurrentHashMap;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zyao on 2020/10/20 14:26
 */
public class ProviderSessionMap {

    private static final ConcurrentHashMap<Integer, ProviderSession> defaultProviderSessionMap = new ConcurrentHashMap<>();

    private final ConcurrentHashMap<Integer, ProviderSession> providerSessionMap = new ConcurrentHashMap<>();

    public final ConcurrentHashMap<Integer, ProviderSession> getProviderSessionMap() {
        return providerSessionMap;
    }

    public void putProviderSession(int pvid, ProviderSession providerSession) {
        providerSessionMap.put(pvid, providerSession);
    }

    public void removeProviderSession(int pvid) {
        providerSessionMap.remove(pvid);
    }

    public ProviderSession getProviderSession(int pvid) {
        ProviderSession providerSession = providerSessionMap.get(pvid);
        return providerSession == null? defaultProviderSessionMap.get(pvid) : providerSession;
    }

    public static void putDefaultProviderSession(int pvid, ProviderSession providerSession) {
        defaultProviderSessionMap.put(pvid, providerSession);
    }

    public static void removeDefaultProviderSession(int pvid) {
        defaultProviderSessionMap.remove(pvid);
    }
}
