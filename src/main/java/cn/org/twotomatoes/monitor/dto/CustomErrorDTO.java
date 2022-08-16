package cn.org.twotomatoes.monitor.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @author HeYunjia
 */
@Data
public class CustomErrorDTO {
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
    private String browser;

    /**
     * 操作系统类型
     */
    @JsonProperty("OS")
    private String os;

    /**
     * message 列表
     */
    private List<String> message;
}
