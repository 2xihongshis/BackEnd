package cn.org.twotomatoes.monitor.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 监控 fetch 接口
 */
@TableName(value = "fetch_info")
@Data
public class FetchInfo implements Serializable {
    /**
     * 自增的唯一标识
     */
    @TableId
    private Long id;

    /**
     * 请求的 ip 地址
     */
    @TableField(fill = FieldFill.INSERT)
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
    private String browser;

    /**
     * 用户操作系统类型
     */
    @JsonProperty("OS")
    private String os;

    /**
     * 路径
     */
    private String pathname;

    /**
     * 状态码
     */
    private String status;

    /**
     * 持续事件
     */
    private String duration;

    /**
     * 请求参数
     */
    private String params;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}