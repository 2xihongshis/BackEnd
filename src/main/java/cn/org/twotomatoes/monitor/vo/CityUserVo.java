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
@ApiModel("城市用户数量")
public class CityUserVo {

    @ApiModelProperty("城市信息")
    private List<InfoEntity> cityList;
}
