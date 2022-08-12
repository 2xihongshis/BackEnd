package cn.org.twotomatoes.monitor;

import cn.hutool.core.util.ObjectUtil;
import cn.org.twotomatoes.monitor.entity.PvAndUv;
import cn.org.twotomatoes.monitor.mapper.BlankScreenMapper;
import cn.org.twotomatoes.monitor.service.BlankScreenService;
import cn.org.twotomatoes.monitor.service.PvAndUvService;
import cn.org.twotomatoes.monitor.util.CountUVUtil;
import cn.org.twotomatoes.monitor.util.RedisMQ;
import cn.org.twotomatoes.monitor.util.RedisMQResult;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.stream.*;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static cn.org.twotomatoes.monitor.util.constant.RedisConstants.PU_UV_KEY_PATTERN;

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
    void testOffer() {
        stringRedisTemplate
                .opsForStream()
                .createGroup("testKey", "group");
        Map<String, String> map = new HashMap<>();
        map.put("url", "http://test.com/");
        stringRedisTemplate
                .opsForStream()
                .add("testKey",map);
//        stringRedisTemplate
//                .opsForStream()
//                .consumers("testKey", "group");
    }

    @Test
    void testPoll() {
        @SuppressWarnings("unchecked")
        List<MapRecord<String, Object, Object>> read = stringRedisTemplate.opsForStream().read(
                Consumer.from("group", "consumer"),
                StreamReadOptions.empty().count(1).block(Duration.ofSeconds(2)),
                StreamOffset.create("testKey", ReadOffset.lastConsumed())
        );
        if (read == null || read.isEmpty()) {
            System.out.println("read is null");
            stringRedisTemplate.delete("testKey");
            return;
        }
        System.out.print("获取的数据为: ");
        System.out.println(read.get(0));
        String url = (String) read.get(0).getValue().get("url");
        System.out.println("url: " + url);
        stringRedisTemplate.opsForStream().acknowledge("testKey", "group", read.get(0).getId());
    }

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

}
