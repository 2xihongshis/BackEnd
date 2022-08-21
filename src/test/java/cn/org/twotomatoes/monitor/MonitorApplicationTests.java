package cn.org.twotomatoes.monitor;


import cn.org.twotomatoes.monitor.dto.InfoEntity;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.lionsoul.ip2region.xdb.Searcher;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import sun.security.jgss.GSSToken;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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

        String ip1 = searcher.search("121.89.216.110");
        String ip2 = searcher.search("196.211.10.146");
        System.out.println(getCountry(ip1));
        System.out.println(getCountry(ip2));

        System.out.println(getProvince(ip1));
        System.out.println(getProvince(ip2));

    }

    public static String getCountry(String ipRegion) {
        return ipRegion.substring(0, ipRegion.indexOf('|'));
    }

    public static String getProvince(String ipRegion) {
        int start = 0, end = 0;
        for (int i = 0; i < ipRegion.length(); i++) {
            if (ipRegion.charAt(i) != '|') continue;

            end += 1;
            if (end == 2) start = i + 1;
            if (end == 3) {
                end = i;
                break;
            }
        }
        return ipRegion.substring(start, end);
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
}

