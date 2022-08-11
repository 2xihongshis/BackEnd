package cn.org.twotomatoes.monitor.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import lombok.Data;

/**
 * 监控 promiseError
 */
@TableName(value = "promise_error")
@Data
public class PromiseError implements Serializable {
    /**
     * 自增的唯一标识
     */
    @TableId
    private Long id;

    /**
     * 请求的 ip 地址
     */
    private String ip;

    /**
     * 页面标题
     */
    private String title;

    /**
     * 页面 url
     */
    private String url;

    /**
     * 访问时间戳
     */
    private String timestamp;

    /**
     * 用户浏览器类型
     */
    private String userAgent;

    /**
     * 错误描述
     */
    private String message;

    /**
     * 堆栈信息
     */
    private String stack;

    /**
     * 选择器
     */
    private String selector;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}