package cn.org.twotomatoes.monitor.helper;

import cn.org.twotomatoes.monitor.common.RedisMQ;
import org.lionsoul.ip2region.xdb.Searcher;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author HeYunjia
 */

@Component
public class ResourceInitHelper {

    @PostConstruct
    private static void init() {
        RedisMQ.setStringRedisTemplate(stringRedisTemplate);
        CountUVHelper.setStringRedisTemplate(stringRedisTemplate);
        RegionHelper.setSearcher(searcher);
    }

    private static StringRedisTemplate stringRedisTemplate;

    @Resource
    private void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        ResourceInitHelper.stringRedisTemplate = stringRedisTemplate;
    }

    private static Searcher searcher;

    @Resource
    private void setSearcher(Searcher searcher) {
        ResourceInitHelper.searcher = searcher;
    }
}
