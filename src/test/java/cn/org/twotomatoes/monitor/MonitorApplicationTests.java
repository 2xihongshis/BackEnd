package cn.org.twotomatoes.monitor;


import cn.org.twotomatoes.monitor.common.TemplateHolder;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

@Slf4j
@SpringBootTest
class MonitorApplicationTests {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Test
    void testTemplate() {
        System.out.println(stringRedisTemplate);
    }
}

