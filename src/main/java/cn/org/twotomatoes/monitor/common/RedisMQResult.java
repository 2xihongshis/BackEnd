package cn.org.twotomatoes.monitor.common;

import org.springframework.data.redis.connection.stream.RecordId;

/**
 * RedisMQ 的输出类
 *
 * @author HeYunjia
 */
public abstract class RedisMQResult<T> {
    protected RecordId id;
    protected T value;

    public RecordId getId() {
        return id;
    }

    public T getValue() {
        return value;
    }
}
