package cn.org.twotomatoes.monitor.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author HeYunjia
 */
public class Holder {

    public static final String IP_HOLDER = "ipHolder";

    public static void set(String key, String value) {
        map.get(key).set(value);
    }

    public static String get(String key) {
        return map.get(key).get();
    }

    private static final Map<String, ThreadLocal<String>> map;
    private static final ThreadLocal<String> ipHolder = new ThreadLocal<>();

    static {
        map = new HashMap<>();
        map.put(IP_HOLDER, ipHolder);
    }

}
