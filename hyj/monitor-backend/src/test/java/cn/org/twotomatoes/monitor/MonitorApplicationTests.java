package cn.org.twotomatoes.monitor;

import cn.org.twotomatoes.monitor.mapper.BlankScreenMapper;
import cn.org.twotomatoes.monitor.service.BlankScreenService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

@SpringBootTest
class MonitorApplicationTests {

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Resource
    BlankScreenMapper blankScreenMapper;

    @Resource
    BlankScreenService blankScreenService;

    @Test
    void contextLoads() {
        System.out.println(stringRedisTemplate);
        System.out.println(blankScreenMapper);
        System.out.println(blankScreenService);
    }

}
