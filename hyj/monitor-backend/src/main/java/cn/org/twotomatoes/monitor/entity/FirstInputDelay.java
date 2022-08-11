package cn.org.twotomatoes.monitor.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import lombok.Data;

/**
 * 监控首次输入响应延迟
 */
@TableName(value = "first_input_delay")
@Data
public class FirstInputDelay implements Serializable {
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
     * 延迟时间
     */
    private String inputDelay;

    /**
     * 处理的耗时,
     */
    private String duration;

    /**
     * 开始处理的事件
     */
    private String startTime;

    /**
     * 选择器
     */
    private String selector;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}