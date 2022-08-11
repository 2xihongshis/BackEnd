package cn.org.twotomatoes.monitor.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 监控白屏
 * @TableName blank_screen
 */
@TableName(value ="blank_screen")
@Data
public class BlankScreen implements Serializable {
    /**
     * 自增的唯一标识
     */
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
     * 空白点集
     */
    private String emptypoints;

    /**
     * 屏幕分辨率
     */
    private String screen;

    /**
     * 屏幕分辨率
     */
    private String viewpoint;

    /**
     * 选择器
     */
    private String selector;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}