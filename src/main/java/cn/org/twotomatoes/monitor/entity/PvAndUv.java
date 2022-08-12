package cn.org.twotomatoes.monitor.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 记录每天的 PV / UV 大小
 */
@TableName(value = "pv_and_uv")
@Data
public class PvAndUv implements Serializable {
    /**
     * 唯一标识
     */
    @TableId
    private Long id;

    /**
     * 页面 url
     */
    private String url;

    /**
     * 创建时间
     */
    private Date time;

    /**
     * PV 大小
     */
    private Long pvNum;

    /**
     * UV 大小
     */
    private Long uvNum;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}