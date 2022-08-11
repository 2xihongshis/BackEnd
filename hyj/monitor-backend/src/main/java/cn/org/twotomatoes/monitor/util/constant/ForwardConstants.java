package cn.org.twotomatoes.monitor.util.constant;

/**
 * 保存数据的请求地址
 *
 * @author HeYunjia
 */
public class ForwardConstants {
    public static final String JS_ERROR_URL = "/js-error/upload";
    public static final String PROMISE_ERROR_URL = "/promise-error/upload";
    public static final String RESOURCE_ERROR_URL = "/resource-error/upload";

    public static final String XHR_URL = "/xhr/upload";
    public static final String FETCH_URL = "/fetch/upload";

    public static final String BLACK_URL = "/blank/upload";

    public static final String TIMING_URL = "/timing/upload";
    public static final String PAINT_URL = "/paint/upload";
    public static final String LONG_TASK_URL = "/longTask/upload";

    public static final String PV_URL = "/pv-uv/upload";
    public static final String UV_URL = "/pv-uv/upload";

    public static final String STAY_TIME_URL = "/stayTime/upload";

    public static final String DEFAULT = "/upload/fail";
}
