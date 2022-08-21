package cn.org.twotomatoes.monitor.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author HeYunjia
 */
@Data
@ApiModel("数据对单元")
public class InfoEntity {

    @ApiModelProperty("字段名称")
    private String name;

    @ApiModelProperty("数量")
    private Long num;
}
