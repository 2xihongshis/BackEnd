package cn.org.twotomatoes.monitor.dto;

import lombok.Data;


import static cn.org.twotomatoes.monitor.util.constant.RConstants.*;

/**
 * 通用的返回结果类
 *
 * @param <T>
 */
@Data
public class R<T> {

    /**
     * 编码：1 成功, 0 失败, -1 异常
     */
    private Integer code;

    /**
     * 错误信息
     */
    private String msg;

    /**
     * 数据
     */
    private T data;

    public static <T> R<T> success(T object) {
        R<T> r = new R<>();
        r.data = object;
        r.msg = DEFAULT_SUCCESS;
        r.code = SUCCESS_CODE;
        return r;
    }

    public static R<String> success() {
        return R.success(DEFAULT_SUCCESS);
    }

    public static <T> R<T> fail(String msg) {
        R<T> r = new R<>();
        r.msg = msg;
        r.code = FAIL_CODE;
        return r;
    }

    public static <T> R<T> fail() {
        return R.fail(DEFAULT_FAIL);
    }

    public static <T> R<T> error(String msg) {
        R<T> r = new R<>();
        r.msg = msg;
        r.code = ERROR_CODE;
        return r;
    }

    public static <T> R<T> error() {
        return R.error(DEFAULT_ERROR);
    }


}
