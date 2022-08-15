package cn.org.twotomatoes.monitor.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 记录停留时间
 */
@TableName(value = "stay_time")
@Data
public class StayTime implements Serializable {
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
     * 停留事件
     */
    private Long stayTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}