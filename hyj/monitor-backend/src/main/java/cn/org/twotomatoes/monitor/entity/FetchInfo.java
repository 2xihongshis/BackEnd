package cn.org.twotomatoes.monitor.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 监控 fetch 接口
 * @TableName fetch_info
 */
@TableName(value ="fetch_info")
@Data
public class FetchInfo implements Serializable {
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