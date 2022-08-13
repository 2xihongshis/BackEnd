package cn.org.twotomatoes.monitor.helper;

import cn.hutool.core.util.StrUtil;
import cn.org.twotomatoes.monitor.common.RedisMQ;
import cn.org.twotomatoes.monitor.util.URLUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

import static cn.org.twotomatoes.monitor.constant.RedisConstants.*;

/**
 * 统计 pv uv 值
 *
 * @author HeYunjia
 */

public class CountUVHelper {

    /**
     * 更新时间 key, 并返回旧 key
     *
     * @return 返回 oldTime
     */
    public static String updateTime() {
        String oldTime = nowTime;
        nowTime = new SimpleDateFormat(PU_UV_KEY_PATTERN).format(new Date());
        set = new HashSet<>();

        pvKeyPrefix = COUNT_UV_KEY_PREFIX + nowTime + PV_NUM_KEY_PREFIX;
        uvKeyPrefix = COUNT_UV_KEY_PREFIX + nowTime + UV_NUM_KEY_PREFIX;
        mqKey = COUNT_UV_KEY_PREFIX + nowTime + URL_MQ_KEY_PREFIX;

        mq = RedisMQ.buildIfAbsent(mqKey, String.class);
        return oldTime;
    }

    /**
     * 页面被访问, 记录 url 和 ip 地址
     *
     * @param url 页面 url
     * @param ip 客户端 ip
     */
    public static void addRecord(String url, String ip) {
        url = URLUtils.convert(url);
        String pvKey = pvKeyPrefix + url;
        String uvKey = uvKeyPrefix + url;

        if (set.add(url)) mq.offer(url);
        stringRedisTemplate.execute(COUNT_UV_LUA, Arrays.asList(pvKey, uvKey), ip);
    }

    /**
     * 计算 time 时间, 地址为 url 的 pv 值
     *
     * @param time 时间
     * @param url 页面地址
     * @return 返回数量
     */
    public static Long countPV(String time, String url) {
        String pvKey = COUNT_UV_KEY_PREFIX + time + PV_NUM_KEY_PREFIX + url;
        String s = stringRedisTemplate.opsForValue().get(pvKey);

        if (StrUtil.isBlank(s)) return 0L;
        return Long.parseLong(s);
    }

    /**
     * 计算 time 时间, 地址为 url 的 uv 值
     *
     * @param time 时间
     * @param url 页面地址
     * @return 返回数量
     */
    public static Long countUV(String time, String url) {
        String uvKey = COUNT_UV_KEY_PREFIX + time + UV_NUM_KEY_PREFIX + url;

        return stringRedisTemplate.opsForHyperLogLog().size(uvKey);
    }

    /**
     * 删除时间为 time 时间, 地址为 url 的 pv 和 uv 记录
     *
     * @param time 时间
     * @param url 页面地址
     */
    public static void deleteKey(String time, String url) {
        String pvKey = COUNT_UV_KEY_PREFIX + time + PV_NUM_KEY_PREFIX + url;
        String uvKey = COUNT_UV_KEY_PREFIX + time + UV_NUM_KEY_PREFIX + url;

        stringRedisTemplate.delete(Arrays.asList(pvKey, uvKey));
    }

    /**
     * 记录到 redis 使用的 lua 脚本
     */
    private static final DefaultRedisScript<Long> COUNT_UV_LUA;


    /**
     * 去重 url 的 set 集合
     */
    private static HashSet<String> set;

    /**
     * 保存 url 的队列
     */
    private static RedisMQ<String> mq;

    /**
     * 记录到 redis 使用的时间标识
     */
    private static String nowTime;

    /**
     * pv 标识的前缀
     */
    private static String pvKeyPrefix;

    /**
     * uv 标识的前缀
     */
    private static String uvKeyPrefix;

    /**
     * url 队列的标识
     */
    private static String mqKey;

    private static StringRedisTemplate stringRedisTemplate;

    public static void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        CountUVHelper.stringRedisTemplate = stringRedisTemplate;
    }

    static {
        COUNT_UV_LUA = new DefaultRedisScript<>();
        COUNT_UV_LUA.setLocation(new ClassPathResource(COUNT_UV_LUA_PATH));
        COUNT_UV_LUA.setResultType(Long.TYPE);

        nowTime = new SimpleDateFormat(PU_UV_KEY_PATTERN).format(new Date());
        set = new HashSet<>();

        pvKeyPrefix = COUNT_UV_KEY_PREFIX + nowTime + PV_NUM_KEY_PREFIX;
        uvKeyPrefix = COUNT_UV_KEY_PREFIX + nowTime + UV_NUM_KEY_PREFIX;
        mqKey = COUNT_UV_KEY_PREFIX + nowTime + URL_MQ_KEY_PREFIX;

        mq = RedisMQ.buildIfAbsent(mqKey, String.class);
    }
}
