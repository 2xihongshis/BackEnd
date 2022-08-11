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

    public static final String XHR_URL = "/xhr-info/upload";
    public static final String FETCH_URL = "/fetch-info/upload";

    public static final String BLACK_URL = "/blank-screen/upload";

    public static final String TIMING_URL = "/load-time/upload";
    public static final String PAINT_URL = "/paint-time/upload";
    public static final String FIRST_INPUT_DELAY_URL = "/first-input-delay/upload";
    public static final String LONG_TASK_URL = "/long-task/upload";

    public static final String PV_URL = "/visit-info/upload";
    public static final String UV_URL = "/visit-info/upload";

    public static final String STAY_TIME_URL = "/stay-time/upload";

    public static final String DEFAULT = "/upload/fail";
}
