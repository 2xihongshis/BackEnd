package cn.org.twotomatoes.monitor.helper;

import cn.org.twotomatoes.monitor.common.RedisMQ;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author HeYunjia
 */

@Component
public class RedisTemplateHelper {

    private static StringRedisTemplate stringRedisTemplate;

    @Resource
    private void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        RedisTemplateHelper.stringRedisTemplate = stringRedisTemplate;
    }

    @PostConstruct
    private static void init() {
        RedisMQ.setStringRedisTemplate(stringRedisTemplate);
        CountUVHelper.setStringRedisTemplate(stringRedisTemplate);
    }
}
