package cn.org.twotomatoes.monitor.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author HeYunjia
 */
@Data
@ApiModel("来源")
public class SourceUrlVO {

    @ApiModelProperty("来源网站")
    private List<InfoEntryVO> sourceList;
}
