package cn.org.twotomatoes.monitor;


import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.lionsoul.ip2region.xdb.Searcher;
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

    @Resource
    private Searcher searcher;

    @Test
    @SneakyThrows
    void testSearcher() {

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
}

