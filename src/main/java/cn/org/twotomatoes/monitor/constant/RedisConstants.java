package cn.org.twotomatoes.monitor.constant;

/**
 * @author HeYunjia
 */
public class RedisConstants {

    public static final String PV_NUM_KEY_PREFIX = "count:pv:";
    public static final String UV_NUM_KEY_PREFIX = "count:uv:";

    public static final String PU_UV_KEY_PATTERN = "yyyy-MM-dd-HH:";
    public static final String COUNT_UV_LUA_PATH = "lua/CountUV.lua";

    public static final String URL_MQ_KEY_PREFIX = "url:";

}
