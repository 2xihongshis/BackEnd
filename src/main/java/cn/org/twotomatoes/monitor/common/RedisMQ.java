package cn.org.twotomatoes.monitor.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.connection.stream.*;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import java.util.Collections;
import java.util.List;


/**
 * 基于 RedisTemplate 实现的消息队列
 *
 * @author HeYunjia
 */
public class RedisMQ<T> {

    /**
     * 使用类需要先注入 StringRedisTemplate
     *
     * @param template StringRedisTemplate
     */
    public static void setStringRedisTemplate(StringRedisTemplate template) {
        RedisMQ.template = template;
    }

    /**
     * 创建一个 id 为 key 的消息队列
     *
     * @param key 消息队列的 redis 标识
     * @param valueType 存放的数据类型
     */
    public static <T> RedisMQ<T> create(String key, Class<T> valueType) {
        if (template == null) throw new RuntimeException("need to call setStringRedisTemplate()");

        createMQ(key);
        return new RedisMQ<>(key, valueType);
    }

    /**
     * 获取一个 id 为 key, 数据类型为 valueType 的消息队列, 不存在时创建
     *
     * @param key 消息队列的 redis 标识
     * @param valueType 存放的数据类型
     */
    public static <T> RedisMQ<T> getOrCreate(String key, Class<T> valueType) {
        if (!exist(key)) return create(key, valueType);

        return new RedisMQ<>(key, valueType);
    }

    /**
     * 往 id 为 key 的消息队列中, 添加一条消息 obj
     *
     * @param data 要存入消息队列的数据
     */
    public void offer(T data) {
        template.opsForStream()
                .add(mpKey, Collections.singletonMap(MQ_MAP_KEY, toString(data)));
    }

    /**
     * 从消息队列中读取一条数据
     *
     * @return 返回队列中的下一条数据, 如果为空返回 null
     */
    public RedisMQResult<T> poll() {
        return poll(true);
    }

    /**
     * 确认消息队列中的消息 message
     *
     * @param message 从消息队列中获取到的数据
     */
    public void ack(RedisMQResult<T> message) {
        if (message == null) throw new RuntimeException("parameter is null");

        template.opsForStream().acknowledge(mpKey, MQ_GROUP_NAME, message.getId());
    }

    /**
     * 清空消息队列
     */
    public void clear() {
        destroy();
        createMQ(mpKey);
    }

    /**
     * 删除队列
     */
    public void destroy() {
        template.delete(Collections.singletonList(mpKey));
    }

    /**
     * 判断 id 为 key 的队列是否存在
     *
     * @param key 消息队列的 id
     * @return 存在 true, 不存在 false
     */
    public static boolean exist(String key) {
        return Boolean.TRUE.equals(template.hasKey(key));
    }

    /**
     * 创建一个 id 为 key 的消息队列
     *
     * @param key 消息队列的 redis 标识
     */
    private static void createMQ(String key) {
        template.opsForStream().createGroup(key, MQ_GROUP_NAME);
    }

    /**
     * 从 pending-list 中读取一条数据存到阻塞队列
     */
    private void transferPendingList() {
        template.execute(TRANSFER_SCRIPT, Collections.singletonList(mpKey));
    }

    /**
     * 读取队列中的一条数据, 如果不存在, 尝试看 pending-list 中是否存在
     * 如果存在, 将其放在阻塞队列中再次执行 poll, 重复调用最多一次.
     *
     * @param isFirst 是否时第一次
     * @return 返回队列中的下一条数据, 不存在返回 null
     */
    @SuppressWarnings("unchecked")
    private RedisMQResult<T> poll(boolean isFirst) {
        RedisMQResult<T> convert = convert(
                template.opsForStream().read(
                        Consumer.from(MQ_GROUP_NAME, MQ_CONSUMER_NAME),
                        StreamReadOptions.empty().count(1),
                        StreamOffset.create(mpKey, ReadOffset.lastConsumed())));
        if (convert != null) return convert;

        transferPendingList();
        return isFirst ? poll(false) : null;
    }

    /**
     * 将查询 Redis 得到的对象转换为需要的结果对象
     *
     * @param data 查询结果
     * @return MQ 结果对象
     */
    private RedisMQResult<T> convert(List<MapRecord<String, Object, Object>> data) {
        if (data == null || data.isEmpty() || data.get(0) == null) return null;

        return new Result<>(data.get(0).getId(),
                toBean(data.get(0).getValue().get(MQ_MAP_KEY)));
    }

    /**
     * 将 Java 对象序列化为 Json 字符串
     *
     * @param data Java 对象
     * @return 序列化后的字符串
     */
    private String toString(T data) {
        String result;
        try {
            result = MAPPER.writeValueAsString(data);
        } catch (Exception e) {
            throw new ClassCastException();
        }
        return result;
    }

    /**
     * 将 Json 字符串解码为 Java 对象
     *
     * @param value Json 字符串
     * @return Java 对象
     */
    private T toBean(Object value) {
        T result;
        try {
            result = MAPPER.readValue((String) value, valueType);
        } catch (Exception e) {
            throw new ClassCastException();
        }
        return result;
    }

    /**
     * 返回使用的结果类
     *
     * @param <T> 数据类型
     */
    private static class Result<T> extends RedisMQResult<T> {
        private Result(RecordId id, T value) {
            this.id = id;
            this.value = value;
        }
    }

    /**
     * 私有构造函数
     *
     * @param key 队列名字
     * @param valueType 存放的数据类型
     */
    private RedisMQ(String key, Class<T> valueType) {
        this.mpKey = key;
        this.valueType = valueType;
    }

    /**
     * 队列名字
     */
    private final String mpKey;

    /**
     * 存放的数据类型
     */
    private final Class<T> valueType;

    private static StringRedisTemplate template;

    private static final String TRANSFER_SCRIPT_TEXT =
            "local result=redis.call('XREADGROUP'," +
                    "'GROUP','group','consumer','COUNT',1," +
                    "'STREAMS',KEYS[1],'0');" +
                    "if type(result[1][2][1])~='table' then return 0;end;" +
                    "redis.call('XADD',KEYS[1],'*','key',result[1][2][1][2][2]);" +
                    "redis.call('XACK',KEYS[1],'group',result[1][2][1][1]);" +
                    "return 1;";

    private static final DefaultRedisScript<Long> TRANSFER_SCRIPT =
            new DefaultRedisScript<>(TRANSFER_SCRIPT_TEXT, Long.class);

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private static final String MQ_GROUP_NAME = "group";
    private static final String MQ_CONSUMER_NAME = "consumer";
    private static final String MQ_MAP_KEY = "key";
}
