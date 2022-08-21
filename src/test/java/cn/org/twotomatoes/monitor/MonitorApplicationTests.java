package cn.org.twotomatoes.monitor;


import cn.org.twotomatoes.monitor.dto.InfoEntity;
import cn.org.twotomatoes.monitor.helper.CountUVHelper;
import cn.org.twotomatoes.monitor.helper.RegionHelper;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.lionsoul.ip2region.xdb.Searcher;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import sun.security.jgss.GSSToken;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.SimpleFormatter;

@Slf4j
@SpringBootTest(classes = MonitorApplication.class)
class MonitorApplicationTests {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Test
    void testTemplate() {
        System.out.println(stringRedisTemplate);
    }

    @Resource
    private Searcher searcher;

    @Test
    @SneakyThrows
    void testSearcher() {
        stringRedisTemplate.opsForValue().set("北京", "24124");

        String ip1 = "121.89.216.110";
        String ip2 = "198.211.10.146";
        System.out.println(RegionHelper.getCountry(ip1));
        System.out.println(RegionHelper.getProvince(ip1));

        System.out.println(RegionHelper.getCountry(ip2));
        System.out.println(RegionHelper.getProvince(ip2));

    }

    @Test
    void testHash() {
        stringRedisTemplate.opsForHash()
                .increment("testHash", "testKey", 1);
        stringRedisTemplate.opsForHash()
                .increment("testHash", "testKey2", 2);

        Map<Object, Object> testHash = stringRedisTemplate.opsForHash()
                .entries("testHash");

        System.out.println(testHash);

        List<InfoEntity> list = new LinkedList<>();
        for (Object key : testHash.keySet()) {
            InfoEntity infoEntryVO = new InfoEntity();
            infoEntryVO.setName((String) key);
            infoEntryVO.setNum((Long.parseLong((String) testHash.get(key))));
            list.add(infoEntryVO);
        }

        System.out.println(list);
    }

    @Test
    void testHyperLogLog() {
        stringRedisTemplate.opsForHyperLogLog()
                .add("key1", "1", "2", "3");
        Long key2 = stringRedisTemplate.opsForHyperLogLog()
                .add("key2", "2", "4", "5");
        System.out.println("key2: " + key2);

        Long union = stringRedisTemplate.opsForHyperLogLog()
                .union("key1", "key2");
        System.out.println(union);
    }

    @Test
    @SneakyThrows
    void testFormat() {

        String timestamp = String.valueOf(System.currentTimeMillis());
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(new Date(Long.parseLong(timestamp)));

        calendar.add(Calendar.DATE, -1);

        Date time = calendar.getTime();

        System.out.println(simpleDateFormat.format(time));
    }

    @Test
    void testCountUV() {

        String timestamp = String.valueOf(System.currentTimeMillis());
        String url = "http://yjoker.work";
        String sourceUrl = "http://baidu.com";
        String ip = "121.89.216.110";
        String uuid = "1234564365465";

        CountUVHelper.addRecord(timestamp, url, sourceUrl, ip, uuid);

    }
}

