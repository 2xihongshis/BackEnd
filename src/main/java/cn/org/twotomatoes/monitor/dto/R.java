package cn.org.twotomatoes.monitor.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;


import static cn.org.twotomatoes.monitor.constant.RConstants.*;

/**
 * 通用的返回结果类
 *
 * @param <T> data 的类型
 */

@Getter
@ApiModel("统一返回类")
public class R<T> {

    @ApiModelProperty("编码: 2 成功但没有数据, 1 成功, 0 失败, -1 异常")
    private Integer code;

    @ApiModelProperty("错误信息")
    private String msg;

    @ApiModelProperty("数据")
    private T data;

    private R() {}

    public static R<String> success() {
        return R.success(EMPTY)
                .code(SUCCESS_NOT_DATA_CODE);
    }

    public static <T> R<T> success(T object) {
        return new R<T>()
                .code(SUCCESS_CODE)
                .msg(DEFAULT_SUCCESS)
                .data(object);
    }

    public static R<String> fail() {
        return R.fail(DEFAULT_FAIL);
    }

    public static R<String> fail(String msg) {
        return new R<String>()
                .code(FAIL_CODE)
                .msg(msg)
                .data(EMPTY);
    }

    public static R<String> error() {
        return R.error(DEFAULT_ERROR);
    }

    public static R<String> error(String msg) {
        return new R<String>()
                .code(ERROR_CODE)
                .msg(msg)
                .data(EMPTY);
    }

    public R<T> data(T data) {
        this.data = data;
        return this;
    }

    public R<T> msg(String msg) {
        this.msg = msg;
        return this;
    }

    private R<T> code(Integer code) {
        this.code = code;
        return this;
    }
}
