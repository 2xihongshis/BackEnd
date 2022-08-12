package cn.org.twotomatoes.monitor.util;

import cn.hutool.core.util.ObjectUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.connection.stream.RecordId;

import java.util.List;

import static cn.org.twotomatoes.monitor.util.constant.RedisConstants.MQ_MAP_KEY;

/**
 * 统一队列的返回结果, 包含其内容和标识其内容的唯一 id
 *
 * @author HeYunjia
 */

@Data
@AllArgsConstructor
public class RedisMQResult {
    private RecordId id;
    private Object value;

    public static RedisMQResult convert(
            List<MapRecord<String, Object, Object>> data) {
        if (ObjectUtil.isNull(data) || data.isEmpty()) return null;

        return RedisMQResult.convert(data.get(0));
    }

    public static RedisMQResult convert(
            MapRecord<String, Object, Object> record) {
        if (ObjectUtil.isNull(record)) return null;

        return new RedisMQResult(record.getId(), record.getValue().get(MQ_MAP_KEY));
    }
}
