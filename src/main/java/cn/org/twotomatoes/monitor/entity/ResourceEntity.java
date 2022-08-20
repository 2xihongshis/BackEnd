package cn.org.twotomatoes.monitor.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 资源监听
 */
@TableName(value ="resource")
@Data
public class ResourceEntity implements Serializable {
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
    private String browser;

    /**
     * 操作系统类型
     */
    private String os;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private Double domainLookupStart;

    /**
     * 
     */
    private Double domainLookupEnd;

    /**
     * 
     */
    private Double connectStart;

    /**
     * 
     */
    private Double connectEnd;

    /**
     * 
     */
    private Double duration;

    /**
     * 
     */
    private String encodedBodySize;

    /**
     * 
     */
    private Double requestStart;

    /**
     * 
     */
    private Double responseEnd;

    /**
     * 
     */
    private Double responseStart;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}