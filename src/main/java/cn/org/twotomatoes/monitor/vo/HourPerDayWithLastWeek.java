package cn.org.twotomatoes.monitor.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

/**
 * @author HeYunjia
 */
@Data
@ApiModel("每天及其前一周的每小时数据")
public class HourPerDayWithLastWeek {

    @ApiModelProperty("当天没每小时数据")
    private List<Long> nowNum;

    @ApiModelProperty("上周数据")
    private List<Long> lastWeek;

    public HourPerDayWithLastWeek() {
        nowNum = new LinkedList<>();
        lastWeek = new LinkedList<>();
    }
}
