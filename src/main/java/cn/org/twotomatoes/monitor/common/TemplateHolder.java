package cn.org.twotomatoes.monitor.common;

import cn.org.twotomatoes.monitor.helper.CountUVHelper;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author HeYunjia
 */

@Component
public class TemplateHolder {

    private static StringRedisTemplate stringRedisTemplate;

    @Resource
    private void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        TemplateHolder.stringRedisTemplate = stringRedisTemplate;
    }

    public static StringRedisTemplate get() {
        return stringRedisTemplate;
    }
}
