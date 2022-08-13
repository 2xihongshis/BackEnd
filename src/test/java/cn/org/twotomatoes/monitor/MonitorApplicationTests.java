package cn.org.twotomatoes.monitor;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import cn.org.twotomatoes.monitor.common.*;
import cn.org.twotomatoes.monitor.dto.TestEntity;
import cn.org.twotomatoes.monitor.mapper.BlankScreenMapper;
import cn.org.twotomatoes.monitor.service.BlankScreenService;
import cn.org.twotomatoes.monitor.service.PvAndUvService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.omg.CORBA.OBJ_ADAPTER;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import sun.swing.StringUIClientPropertyKey;

import javax.annotation.Resource;


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
//        String key = "TestKey";
//        String suffix = "testTest:";
//        RedisMQ.create(key);
//        for (int i = 0; i < 100; i++) {
//            RedisMQ.offer(key, suffix + i);
//        }
//        RedisMQResult message = RedisMQ.poll(key);
//        while (ObjectUtil.isNotNull(message)) {
//            System.out.println((String) message.getValue());
//            RedisMQ.ack(key, message);
//            message = RedisMQ.poll(key);
//        }
//        RedisMQ.delete(key);
    }

    @Test
    @SneakyThrows
    void testBackup() {
//        String time = CountUVUtil.updateTime();
//        String time = "null";
//        String key = URL_MQ_KEY_PREFIX + time;
//        RedisMQ.createIfAbsent(key);
//        RedisMQResult message = RedisMQ.poll(key);
//        while (ObjectUtil.isNotNull(message)) {
//            String url = (String) message.getValue();
//            PvAndUv pvAndUv = new PvAndUv();
//            pvAndUv.setTime(new SimpleDateFormat(PU_UV_KEY_PATTERN).parse(time));
//            pvAndUv.setPvNum(CountUVHelper.countPV(time, url));
//            pvAndUv.setUvNum(CountUVHelper.countUV(time, url));
//            pvAndUv.setUrl(url);
//            pvAndUvService.save(pvAndUv);
//
//            RedisMQ.ack(key, message);
//            message = RedisMQ.poll(key);
//        }
//        RedisMQ.delete(key);
    }


    @Test
    void testRedisMQNew() {
        String key = "TestKey:";
        RedisMQ.setStringRedisTemplate(TemplateHolder.get());
        RedisMQ<String> mq = RedisMQ.create(key, String.class);

        Class<?> valueType = RedisMQ.getValueType(key);

        mq.offer("string1");
        mq.offer("string2");

        RedisMQResult<String> message = mq.poll();
        while (ObjectUtil.isNotNull(message)) {
            System.out.println(message);
            mq.ack(message);
            message = mq.poll();
        }
        mq.delete();
    }

    @Test
    @SneakyThrows
    void testClass() {
        Class<String> stringClass = String.class;
        System.out.println(stringClass.getName());
        System.out.println(stringClass.getName().equals("java.lang.String"));
        System.out.println(JSONUtil.toJsonStr("123123"));
        Object a = "qwreqr";
    }
}

