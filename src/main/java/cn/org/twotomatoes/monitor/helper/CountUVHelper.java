package cn.org.twotomatoes.monitor.helper;

import cn.hutool.core.util.StrUtil;
import cn.org.twotomatoes.monitor.constant.RedisConstants;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static cn.org.twotomatoes.monitor.constant.RedisConstants.*;

/**
 * 统计 pv uv 值
 *
 * @author HeYunjia
 */

public class CountUVHelper {

    /**
     * 使用前需要注入 StringRedisTemplate
     */
    public static void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        CountUVHelper.stringRedisTemplate = stringRedisTemplate;
    }

    /**
     * 处理一条新的 visitInfo 数据
     *
     * @param timestamp 数据长传的时间戳
     * @param url url 地址
     * @param sourceUrl 来源网站
     * @param ip IP 地址
     * @param uuid 唯一标识
     */
    public static void addRecord(String timestamp, String url, String sourceUrl, String ip, String uuid) {

        List<String> keys = getKeys(timestamp);
        Object[] argv = getArgv(url, sourceUrl, ip, uuid);

        submit(keys, argv);
    }

    /**
     * 获取当前的 keys
     */
    public static List<String> getKeys(String timestamp) {
        Date date = new Date(Long.parseLong(timestamp));

        String day = DAY_FORMAT.format(date);
        String hour = HOUR_FORMAT.format(date);

        String lastDay = getLastDay(date);

        return Arrays.asList(
                URL_KEY,
                SOURCE_URL_KEY,
                REGION_COUNTRY_KEY,
                REGION_PROVINCE_KEY,

                IP_ALL_KEY,
                IP_DAY_PREFIX + day,

                PV_ALL_KEY,
                PV_DAY_PREFIX + day,
                PV_HOUR_PREFIX + hour,

                UV_ALL_KEY,
                UV_DAY_NEW_KEY + day,

                UV_DAY_NEW_KEY + lastDay,
                UV_YESTERDAY_VISITOR_KEY + day,

                UV_DAY_PREFIX + day,
                UV_HOUR_PREFIX + hour
        );
    }

    /**
     * 处理数据, 获取 argv 对象
     *
     * @return Object[]
     */
    private static Object[] getArgv(String url, String sourceUrl, String ip, String uuid) {

        String country = RegionHelper.getCountry(ip);
        String province = RegionHelper.getProvince(ip);
        if (StrUtil.isBlank(uuid)) uuid = ip;

        return new Object[] {url, sourceUrl, country, province, ip, uuid};
    }

    /**
     * 执行 lua 代码
     *
     * @param keys keys 值
     * @param argv argv 参数
     */
    private static void submit(List<String> keys, Object[] argv) {
        stringRedisTemplate.execute(COUNT_UV_LUA, keys, argv);
    }

    /**
     * 获取前一天
     *
     * @param date Date 时间
     * @return 返回前一天的时间
     */
    private static String getLastDay(Date date) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, -1);

        return DAY_FORMAT.format(cal.getTime());
    }

    private static final String URL_KEY = MONITOR + RedisConstants.URL_KEY;
    private static final String SOURCE_URL_KEY = MONITOR + RedisConstants.SOURCE_URL_KEY;
    private static final String REGION_COUNTRY_KEY = MONITOR + RedisConstants.REGION_COUNTRY_KEY;
    private static final String REGION_PROVINCE_KEY = MONITOR + RedisConstants.REGION_PROVINCE_KEY;

    private static final String IP_ALL_KEY = MONITOR + IP_PREFIX + ALL_KEY;
    private static final String IP_DAY_PREFIX = MONITOR + IP_PREFIX + DAY_KEY;

    private static final String PV_ALL_KEY = MONITOR + PV_PREFIX + ALL_KEY;
    private static final String PV_DAY_PREFIX = MONITOR + PV_PREFIX + DAY_KEY;
    private static final String PV_HOUR_PREFIX = MONITOR + PV_PREFIX + HOUR_KEY;

    private static final String UV_ALL_KEY = MONITOR + UV_PREFIX + ALL_KEY;
    private static final String UV_DAY_PREFIX = MONITOR + UV_PREFIX + DAY_KEY;
    private static final String UV_HOUR_PREFIX = MONITOR + UV_PREFIX + HOUR_KEY;

    private static final String UV_DAY_NEW_KEY = UV_DAY_PREFIX + DAY_NEW_KEY;
    private static final String UV_YESTERDAY_VISITOR_KEY = UV_DAY_PREFIX + YESTERDAY_VISITOR_KEY;

    private static final SimpleDateFormat DAY_FORMAT = new SimpleDateFormat(DAY_FORMAT_PATTERN);
    private static final SimpleDateFormat HOUR_FORMAT = new SimpleDateFormat(HOUR_FORMAT_PATTERN);

    private static StringRedisTemplate stringRedisTemplate;


    /**
     * 记录到 redis 使用的 lua 脚本
     */
    private static final DefaultRedisScript<Long> COUNT_UV_LUA;

    static {
        COUNT_UV_LUA = new DefaultRedisScript<>();
        COUNT_UV_LUA.setLocation(new ClassPathResource(COUNT_UV_LUA_PATH));
        COUNT_UV_LUA.setResultType(Long.TYPE);
    }
}
