package cn.org.twotomatoes.monitor.common;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.connection.stream.RecordId;

import java.util.List;


/**
 * @author HeYunjia
 */

@Data
@AllArgsConstructor
public class RedisMQResult<T> {

    private RecordId id;
    private T value;

    public static <T> RedisMQResult<T> convert(
            List<MapRecord<String, Object, Object>> data, Class<T> valueType) {
        if (ObjectUtil.isNull(data) || data.isEmpty()) return null;

        return RedisMQResult.convert(data.get(0), valueType);
    }

    private static <T> RedisMQResult<T> convert(
            MapRecord<String, Object, Object> record, Class<T> valueType) {
        if (ObjectUtil.isNull(record)) return null;

        Object data = record.getValue().get(MQ_MAP_KEY);
        T value;

        if (data.getClass().equals(valueType)) value = valueType.cast(data);
        else value = JSONUtil.toBean((String) data, valueType);

        return new RedisMQResult<>(record.getId(), value);
    }

    private static final String MQ_MAP_KEY = "key";
}
