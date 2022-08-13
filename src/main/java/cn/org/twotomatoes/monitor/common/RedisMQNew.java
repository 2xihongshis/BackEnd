package cn.org.twotomatoes.monitor.common;

import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import lombok.SneakyThrows;
import org.springframework.data.redis.connection.stream.Consumer;
import org.springframework.data.redis.connection.stream.ReadOffset;
import org.springframework.data.redis.connection.stream.StreamOffset;
import org.springframework.data.redis.connection.stream.StreamReadOptions;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;

/**
 * @author HeYunjia
 */

public class RedisMQNew<T> {

    private final String mpKey;
    private final String typeKey;
    private final Class<T> valueType;

    private RedisMQNew(String key, Class<T> valueType) {
        this.mpKey = key + MQ_KEY_SUFFIX;
        this.typeKey = key + MQ_OBJECT_TYPE_SUFFIX;
        this.valueType = valueType;
    }

    /**
     * 创建一个 id 为 key 的消息队列
     *
     * @param key 消息队列的唯一标识
     * @param valueType 存放的数据类型
     */
    public static <T> RedisMQNew<T> create(String key, Class<T> valueType) {
        template.opsForStream().createGroup(key + MQ_KEY_SUFFIX, MQ_GROUP_NAME);
        template.opsForValue().set(key + MQ_OBJECT_TYPE_SUFFIX, valueType.getName());

        return new RedisMQNew<T>(key, valueType);
    }

    /**
     * 创建一个 id 为 key 的消息队列, 当且仅当不存在 id 为 key 的消息队列
     *
     * @param key 消息队列的唯一标识
     */
    public static <T> RedisMQNew<T> createIfAbsent(String key, Class<T> valueType) {
        if (!exist(key)) create(key, valueType);

        return new RedisMQNew<T>(key, valueType);
    }

    @SneakyThrows
    public static Class<?> getValueType(String key) {
        String valueType = template.opsForValue().get(key + MQ_OBJECT_TYPE_SUFFIX);

        return Class.forName(valueType);
    }

    /**
     * 判断 id 为 key 的队列是否存在
     *
     * @param key 消息队列的 id
     * @return 存在 true, 不存在 false
     */
    public static boolean exist(String key) {
        return BooleanUtil.isTrue(template.hasKey(key + MQ_KEY_SUFFIX));
    }

    /**
     * 往 id 为 key 的消息队列中, 添加一条消息 obj
     *
     * @param data 要存入消息队列的数据
     */
    public void offer(T data) {
        HashMap<String, String> map = new HashMap<>();
        map.put(MQ_MAP_KEY, JSONUtil.toJsonStr(data));
        template.opsForStream().add(mpKey, map);
    }

    /**
     * 从消息队列中读取一条数据
     *
     * @return 返回队列中的下一条数据, 如果为空返回 null
     */
    public RedisMQResultNew<T> poll() {
        RedisMQResultNew<T> result = readOneByPendingList();

        return ObjectUtil.isNotNull(result) ? result : readOneByBlock();
    }

    /**
     * 从消息队列的阻塞队列中, 读取一条消息
     *
     * @return 返回一条消息数据
     */
    @SuppressWarnings("unchecked")
    private RedisMQResultNew<T> readOneByBlock() {
        return RedisMQResultNew.convert(
                template.opsForStream().read(
                        Consumer.from(MQ_GROUP_NAME, MQ_CONSUMER_NAME),
                        StreamReadOptions.empty().count(1)
                                .block(Duration.ofSeconds(2)),
                        StreamOffset.create(mpKey, ReadOffset.lastConsumed())),
                valueType);
    }

    /**
     * 从消息队列的 pending-list 中读取一条数据
     *
     * @return 返回一条数据, 为空返回 Optional.empty()
     */
    @SuppressWarnings("unchecked")
    private RedisMQResultNew<T> readOneByPendingList() {
        return RedisMQResultNew.convert(
                template.opsForStream().read(
                        Consumer.from(MQ_GROUP_NAME, MQ_CONSUMER_NAME),
                        StreamReadOptions.empty().count(1),
                        StreamOffset.create(mpKey, ReadOffset.lastConsumed())),
                valueType);
    }

    /**
     * 确认消息队列中的消息 result
     *
     * @param result 从消息队列中获取到的数据
     */
    public void ack(RedisMQResultNew<T> result) {
        if (ObjectUtil.isNull(result)) throw new RuntimeException();

        template.opsForStream()
                .acknowledge(mpKey, MQ_GROUP_NAME, result.getId());
    }

    /**
     * 删除队列
     */
    public void delete() {
        template.delete(Arrays.asList(mpKey, typeKey));
    }

    private static StringRedisTemplate template;

    public static void setStringRedisTemplate(StringRedisTemplate template) {
        RedisMQNew.template = template;
    }

    public static final String MQ_GROUP_NAME = "group";
    public static final String MQ_CONSUMER_NAME = "consumer";
    public static final String MQ_MAP_KEY = "key";
    public static final String MQ_KEY_SUFFIX = ":mq";
    public static final String MQ_OBJECT_TYPE_SUFFIX = ":object-ype";
}
