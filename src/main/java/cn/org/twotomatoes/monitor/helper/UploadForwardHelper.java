package cn.org.twotomatoes.monitor.helper;

import java.util.HashMap;

import static cn.org.twotomatoes.monitor.constant.TypeConstants.*;
import static cn.org.twotomatoes.monitor.constant.ForwardConstants.*;


/**
 * 上传数据类型和映射地址的对应关系
 *
 * @author HeYunjia
 */
public class UploadForwardHelper {

    public static String getURL(String type) {
        return map.getOrDefault(type, FAIL_URL);
    }

    private static final HashMap<String, String> map;
    static {
        map = new HashMap<>();
        map.put(JS_ERROR, JS_ERROR_URL);
        map.put(PROMISE_ERROR, PROMISE_ERROR_URL);
        map.put(RESOURCE_ERROR, RESOURCE_ERROR_URL);
        map.put(CUSTOM_ERROR, CUSTOM_ERROR_URL);

        map.put(XHR_INFO, XHR_INFO_URL);
        map.put(FETCH_INFO, FETCH_INFO_URL);

        map.put(BLACK_SCREEN, BLACK_SCREEN_URL);

        map.put(LOAD_TIME, LOAD_TIME_URL);
        map.put(PAINT_TIME, PAINT_TIME_URL);
        map.put(FIRST_INPUT_DELAY, FIRST_INPUT_DELAY_URL);
        map.put(LONG_TASK, LONG_TASK_URL);

        map.put(VISIT_INFO_PV, VISIT_INFO_URL);
        map.put(VISIT_INFO_UV, VISIT_INFO_URL);

        map.put(STAY_TIME, STAY_TIME_URL);
    }
}
