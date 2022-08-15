package cn.org.twotomatoes.monitor.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import lombok.Data;

/**
 * custom_error 的 message 数据
 */
@TableName(value = "custom_message")
@Data
public class CustomMessage implements Serializable {
    /**
     * 自增的唯一标识
     */
    @TableId
    private Long id;

    /**
     * custom 的唯一标识
     */
    private Long customId;

    /**
     * 消息内容
     */
    private String message;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}