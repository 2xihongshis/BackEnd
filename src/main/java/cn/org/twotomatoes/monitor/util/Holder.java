package cn.org.twotomatoes.monitor.util;

import java.util.HashMap;

/**
 * @author HeYunjia
 */
public class Holder {

    public static final String IP_HOLDER = "ipHolder";
    public static final String UUID_HOLDER = "uuidHolder";

    public static void set(String key, String value) {
        map.putIfAbsent(key, new ThreadLocal<>());
        map.get(key).set(value);
    }

    public static String get(String key) {
        return map.getOrDefault(key, defaultHolder).get();
    }

    private static final HashMap<String, ThreadLocal<String>> map = new HashMap<>();
    private static final ThreadLocal<String> defaultHolder = new ThreadLocal<>();
}
