package cn.org.twotomatoes.monitor.util;

import cn.hutool.core.util.StrUtil;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

import static cn.org.twotomatoes.monitor.util.constant.RedisConstants.*;

/**
 * @author HeYunjia
 */

@Component
public class CountUVUtil {

    private static StringRedisTemplate stringRedisTemplate;

    @Resource
    public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        CountUVUtil.stringRedisTemplate = stringRedisTemplate;
    }

    /**
     * 记录的全部 url
     */
    private static final HashSet<String> set = new HashSet<>();
    public static HashSet<String> getURLSet() {
        return set;
    }

    /**
     * 记录到 redis 使用的 lua 脚本
     */
    private static final DefaultRedisScript<Long> COUNT_UV;

    /**
     * 记录到 redis 使用的 key
     */
    private static String nowTime;
    static {
        COUNT_UV = new DefaultRedisScript<>();
        COUNT_UV.setLocation(new ClassPathResource(COUNT_UV_LUA_PATH));
        COUNT_UV.setResultType(Long.TYPE);

        nowTime = new SimpleDateFormat(PU_UV_KEY_PATTERN).format(new Date());
    }

    /**
     * 更新时间 key, 并返回旧 key
     *
     * @return 返回 oldTime
     */
    public static String updateTime() {
        String oldTime = nowTime;
        nowTime = new SimpleDateFormat(PU_UV_KEY_PATTERN).format(new Date());
        return oldTime;
    }

    /**
     * 页面被访问, 记录 url 和 ip 地址
     *
     * @param url 页面 url
     * @param ip 客户端 ip
     */
    public static void addRecord(String url, String ip) {
        set.add(url);
        String pvKey = PV_KEY + nowTime + url;
        String uvKey = UV_KEY + nowTime + url;
        stringRedisTemplate.execute(COUNT_UV, Arrays.asList(pvKey, uvKey), ip);
    }

    public static Long countPV(String time, String url) {
        String s = stringRedisTemplate
                .opsForValue()
                .get(PV_KEY + time + url);

        if (StrUtil.isBlank(s)) return 0L;
        return Long.parseLong(s);
    }

    public static Long countUV(String time, String url) {
        return stringRedisTemplate
                .opsForHyperLogLog()
                .size(UV_KEY + time + url);
    }

    public static void deleteKey(String url, String time) {
        stringRedisTemplate.delete(
                Arrays.asList(PV_KEY + url + time,
                              UV_KEY + url + time));
    }
}
