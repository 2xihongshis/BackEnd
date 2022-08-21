package cn.org.twotomatoes.monitor.constant;

/**
 * @author HeYunjia
 */
public class RedisConstants {

    public static final String MONITOR = "monitor:";

    public static final String URL_KEY = "urlVisits";

    public static final String SOURCE_URL_KEY = "sourceUrl";

    public static final String REGION_COUNTRY_KEY = "region:country";
    public static final String REGION_PROVINCE_KEY = "region:province";

    public static final String IP_PREFIX = "ip:";
    public static final String PV_PREFIX = "pv:";
    public static final String UV_PREFIX = "uv:";

    public static final String ALL_KEY = "all";
    public static final String DAY_KEY = "day:";
    public static final String HOUR_KEY = "hour:";

    public static final String DAY_NEW_KEY = "new:";
    public static final String YESTERDAY_VISITOR_KEY = "yesterdayVisitor:";


    public static final String DAY_FORMAT_PATTERN = "yyyy-MM-dd";
    public static final String HOUR_FORMAT_PATTERN = "yyyy-MM-dd-HH";

    public static final String COUNT_UV_LUA_PATH = "lua/CountUV.lua";
}
