package cn.org.twotomatoes.monitor.helper;

import cn.hutool.core.util.StrUtil;
import cn.org.twotomatoes.monitor.common.RedisMQ;
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
        mq = RedisMQ.create(URL_MQ_KEY_PREFIX + nowTime, String.class);
        return oldTime;
    }

    /**
     * 页面被访问, 记录 url 和 ip 地址
     *
     * @param url 页面 url
     * @param ip 客户端 ip
     */
    public static void addRecord(String url, String ip) {
        String pvKey = PV_NUM_KEY_PREFIX + nowTime + url;
        String uvKey = UV_NUM_KEY_PREFIX + nowTime + url;

        if (set.add(url)) mq.offer(url);
        stringRedisTemplate.execute(COUNT_UV, Arrays.asList(pvKey, uvKey), ip);
    }

    /**
     * 计算 time 时间, 地址为 url 的 pv 值
     *
     * @param time 时间
     * @param url 页面地址
     * @return 返回数量
     */
    public static Long countPV(String time, String url) {
        String s = stringRedisTemplate
                .opsForValue()
                .get(PV_NUM_KEY_PREFIX + time + url);

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
        return stringRedisTemplate
                .opsForHyperLogLog()
                .size(UV_NUM_KEY_PREFIX + time + url);
    }

    /**
     * 删除时间为 time 时间, 地址为 url 的 pv 和 uv 记录
     *
     * @param time 时间
     * @param url 页面地址
     */
    public static void deleteKey(String time, String url) {
        stringRedisTemplate.delete(
                Arrays.asList(PV_NUM_KEY_PREFIX + time + url,
                        UV_NUM_KEY_PREFIX + time + url));
    }

    /**
     * 记录到 redis 使用的 lua 脚本
     */
    private static final DefaultRedisScript<Long> COUNT_UV;

    /**
     * 记录到 redis 使用的 key
     */
    private static String nowTime;

    /**
     * 去重 url 的 set 集合
     */
    private static HashSet<String> set;

    /**
     * 保存 url 的队列
     */
    private static RedisMQ<String> mq;

    private static StringRedisTemplate stringRedisTemplate;

    public static void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        CountUVHelper.stringRedisTemplate = stringRedisTemplate;
    }

    static {
        COUNT_UV = new DefaultRedisScript<>();
        COUNT_UV.setLocation(new ClassPathResource(COUNT_UV_LUA_PATH));
        COUNT_UV.setResultType(Long.TYPE);

        nowTime = new SimpleDateFormat(PU_UV_KEY_PATTERN).format(new Date());
        set = new HashSet<>();
        mq = RedisMQ.create(URL_MQ_KEY_PREFIX + nowTime, String.class);
    }
}
