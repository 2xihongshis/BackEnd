package cn.org.twotomatoes.monitor.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import lombok.Data;

/**
 * 监控加载时间
 */
@TableName(value = "load_time")
@Data
public class LoadTime implements Serializable {
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
     * TCP 连接耗时
     */
    private String connectTime;

    /**
     * TCP 连接耗时
     */
    private String ttfbTime;

    /**
     * 响应时间
     */
    private String responseTime;

    /**
     * DOM 解析渲染耗时
     */
    private String parseDomTime;

    /**
     * DOMContentLoaded 回调耗时
     */
    private String domContentLoadedTime;

    /**
     * 首次交互耗时
     */
    private String timeToInteractive;

    /**
     * 完整的加载时间
     */
    private String loadTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}