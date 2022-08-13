package cn.org.twotomatoes.monitor;

import cn.hutool.core.util.ObjectUtil;
import cn.org.twotomatoes.monitor.entity.PvAndUv;
import cn.org.twotomatoes.monitor.mapper.BlankScreenMapper;
import cn.org.twotomatoes.monitor.service.BlankScreenService;
import cn.org.twotomatoes.monitor.service.PvAndUvService;
import cn.org.twotomatoes.monitor.helper.CountUVHelper;
import cn.org.twotomatoes.monitor.common.RedisMQ;
import cn.org.twotomatoes.monitor.common.RedisMQResult;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

import java.text.SimpleDateFormat;

import static cn.org.twotomatoes.monitor.constant.RedisConstants.*;

@Slf4j
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

    @Resource
    PvAndUvService pvAndUvService;

    @Test
    void testRedisMQ() {
        String key = "TestKey";
        String suffix = "testTest:";
        RedisMQ.create(key);
        for (int i = 0; i < 100; i++) {
            RedisMQ.offer(key, suffix + i);
        }
        RedisMQResult message = RedisMQ.poll(key);
        while (ObjectUtil.isNotNull(message)) {
            System.out.println((String) message.getValue());
            RedisMQ.ack(key, message);
            message = RedisMQ.poll(key);
        }
        RedisMQ.delete(key);
    }

    @Test
    @SneakyThrows
    void testBackup() {
//        String time = CountUVUtil.updateTime();
        String time = "null";
        String key = URL_MQ_KEY_PREFIX + time;
        RedisMQ.createIfAbsent(key);
        RedisMQResult message = RedisMQ.poll(key);
        while (ObjectUtil.isNotNull(message)) {
            String url = (String) message.getValue();
            PvAndUv pvAndUv = new PvAndUv();
            pvAndUv.setTime(new SimpleDateFormat(PU_UV_KEY_PATTERN).parse(time));
            pvAndUv.setPvNum(CountUVHelper.countPV(time, url));
            pvAndUv.setUvNum(CountUVHelper.countUV(time, url));
            pvAndUv.setUrl(url);
            pvAndUvService.save(pvAndUv);

            RedisMQ.ack(key, message);
            message = RedisMQ.poll(key);
        }
        RedisMQ.delete(key);
    }

}
