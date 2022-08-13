package cn.org.twotomatoes.monitor.common;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author HeYunjia
 */

@Component
public class TemplateHolder {

    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    public StringRedisTemplate get() {
        return stringRedisTemplate;
    }
}
