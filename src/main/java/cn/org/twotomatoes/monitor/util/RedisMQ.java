package cn.org.twotomatoes.monitor.util;

import cn.hutool.core.util.ObjectUtil;
import org.springframework.data.redis.connection.stream.*;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.HashMap;

import static cn.org.twotomatoes.monitor.util.constant.RedisConstants.*;

/**
 * @author HeYunjia
 */

@Component
public class RedisMQ {

    private static StringRedisTemplate stringRedisTemplate;

    @Resource
    public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        RedisMQ.stringRedisTemplate = stringRedisTemplate;
    }

    /**
     * 创建一个 id 为 key 的消息队列
     *
     * @param key 消息队列的唯一标识
     */
    public static void create(String key) {
        stringRedisTemplate.opsForStream().createGroup(key, MQ_GROUP_NAME);
    }

    /**
     * 往 id 为 key 的消息队列中, 添加一条消息 obj
     *
     * @param key 消息队列的 id
     * @param obj 要存入消息队列的数据
     */
    public static void offer(String key, Object obj) {
        HashMap<String, Object> map = new HashMap<>();
        map.put(MQ_MAP_KEY, obj);
        stringRedisTemplate.opsForStream().add(key, map);
    }

    /**
     * 从消息队列中读取一条数据
     *
     * @param key 消息队列的值
     * @return 返回队列中的下一条数据, 如果为空返回 null
     */
    public static RedisMQResult poll(String key) {
        RedisMQResult result = readOneByPendingList(key);

        return ObjectUtil.isNotNull(result) ? result : readOneByBlock(key);
    }

    /**
     * 从 id 为 key 的消息队列中, 读取一条消息
     *
     * @param key 消息队列的 id
     * @return 返回一条消息数据
     */
    @SuppressWarnings("unchecked")
    private static RedisMQResult readOneByBlock(String key) {
        return RedisMQResult.convert(
                stringRedisTemplate.opsForStream().read(
                        Consumer.from(MQ_GROUP_NAME, MQ_CONSUMER_NAME),
                        StreamReadOptions.empty().count(1)
                                .block(Duration.ofSeconds(2)),
                        StreamOffset.create(key, ReadOffset.lastConsumed())));
    }

    /**
     * 从 id 为 key 的消息队列的 pending-list 中读取一条数据
     *
     * @param key 消息队列的 id
     * @return 返回一条数据, 为空返回 Optional.empty()
     */
    @SuppressWarnings("unchecked")
    private static RedisMQResult readOneByPendingList(String key) {
        return RedisMQResult.convert(
                stringRedisTemplate.opsForStream().read(
                        Consumer.from(MQ_GROUP_NAME, MQ_CONSUMER_NAME),
                        StreamReadOptions.empty().count(1),
                        StreamOffset.create(key, ReadOffset.lastConsumed())));
    }

    /**
     * 确认接受 id 为 key 的消息队列中的消息 result
     *
     * @param key 消息队列的 id
     * @param result 从消息队列中获取到的数据
     */
    public static void ack(String key, RedisMQResult result) {
        if (ObjectUtil.isNull(result)) throw new RuntimeException();

        stringRedisTemplate.opsForStream()
                .acknowledge(key, MQ_GROUP_NAME, result.getId());
    }

    /**
     * 删除 id 为 key 的队列
     *
     * @param key 消息队列的 id
     */
    public static void delete(String key) {
        stringRedisTemplate.delete(key);
    }

}
