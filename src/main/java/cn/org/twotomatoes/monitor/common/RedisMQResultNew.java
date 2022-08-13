package cn.org.twotomatoes.monitor.common;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.connection.stream.RecordId;

import java.util.List;


/**
 * @author HeYunjia
 */

@Data
@AllArgsConstructor
public class RedisMQResultNew <T> {

    private RecordId id;
    private T value;

    public static <T> RedisMQResultNew<T> convert(
            List<MapRecord<String, Object, Object>> data, Class<T> valueType) {
        if (ObjectUtil.isNull(data) || data.isEmpty()) return null;

        return RedisMQResultNew.convert(data.get(0), valueType);
    }

    private static <T> RedisMQResultNew<T> convert(
            MapRecord<String, Object, Object> record, Class<T> valueType) {
        if (ObjectUtil.isNull(record)) return null;

        return new RedisMQResultNew<>(record.getId(),
                JSONUtil.toBean((String) record.getValue().get((MQ_MAP_KEY)),
                                valueType));
    }

    private static final String MQ_MAP_KEY = "key";
}
