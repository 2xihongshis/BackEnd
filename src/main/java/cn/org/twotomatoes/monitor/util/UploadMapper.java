package cn.org.twotomatoes.monitor.util;

import java.util.HashMap;

import static cn.org.twotomatoes.monitor.util.constant.TypeConstants.*;
import static cn.org.twotomatoes.monitor.util.constant.ForwardConstants.*;


/**
 * 上传数据类型和映射地址的对应关系
 *
 * @author HeYunjia
 */
public class UploadMapper {

    public static String getURL(String type) {
        return map.getOrDefault(type, DEFAULT);
    }

    private static final HashMap<String, String> map;
    static {
        map = new HashMap<>();
        map.put(JS_ERROR, JS_ERROR_URL);
        map.put(PROMISE_ERROR, PROMISE_ERROR_URL);
        map.put(RESOURCE_ERROR, RESOURCE_ERROR_URL);
        map.put(XHR, XHR_URL);
        map.put(FETCH, FETCH_URL);
        map.put(BLACK, BLACK_URL);
        map.put(TIMING, TIMING_URL);
        map.put(PAINT, PAINT_URL);
        map.put(LONG_TASK, LONG_TASK_URL);
        map.put(PV, PV_URL);
        map.put(UV, UV_URL);
        map.put(STAY_TIME, STAY_TIME_URL);
    }
}
