package cn.org.twotomatoes.monitor.vo;

import cn.org.twotomatoes.monitor.dto.InfoEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author HeYunjia
 */
@Data
@ApiModel("网站点击量")
public class SiteVisitVO {

    @ApiModelProperty("网站数据")
    private List<InfoEntity> siteList;
}