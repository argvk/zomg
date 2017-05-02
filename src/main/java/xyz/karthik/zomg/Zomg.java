package xyz.karthik.zomg;

import xyz.karthik.zomg.hash.Hasher;
import xyz.karthik.zomg.hash.Rendezvous;

/**
 * Created by vk on 4/30/2017.
 */
public class Zomg {

    // hasher to user
    private static Hasher hasher = new Rendezvous();

    public static void put(String key, String value) {
        String memberForKey = getMemberForKey(key);
        if (memberForKey == Discovery.thisNodeName()) {
            Store.store(key.getBytes(), value.getBytes());
        }
    }

    public static String get(String key) {
        String memberForKey = getMemberForKey(key);
        byte[] valBytes = null;
        if (memberForKey == Discovery.thisNodeName()) {
            valBytes = Store.get(key.getBytes());
        }
        if (valBytes != null) return new String(valBytes);
        return null;
    }

    public static String getMemberForKey(String key) {
        return Discovery.thisNodeName();
//        return hasher.withMembers(Discovery.getNodes()).getMemberForKey(key);
    }
}
