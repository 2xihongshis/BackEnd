package cn.org.twotomatoes.monitor.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 监控渲染时间
 * @TableName paint_time
 */
@TableName(value ="paint_time")
@Data
public class PaintTime implements Serializable {
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
     * 请求页面到开始渲染耗时
     */
    private String firstPaint;

    /**
     * 请求页面到渲染首页完整内容耗时
     */
    private String firstContentPaint;

    /**
     * 请求页面到渲染绘制有意义内容的时间
     */
    private String firstMeaningfulPaint;

    /**
     * 请求页面到开始渲染网页可视区内最大的元素时间
     */
    private String largestContentfulPaint;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}