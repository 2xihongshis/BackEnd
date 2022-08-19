package cn.org.twotomatoes.monitor.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 返回的流量数据时使用的对象
 *
 * @author HeYunjia
 */
@Data
@ApiModel("流量数据模型")
public class FlowVO {

    @ApiModelProperty("pv 数量")
    private FlowType pv;

    @ApiModelProperty("uv 数量")
    private FlowType uv;

    @ApiModelProperty("新用户数量")
    private FlowType newVisits;

    @ApiModelProperty("ip 数量")
    private FlowType ip;

    @ApiModelProperty("人均访问次数")
    private FlowType visitsPerCapita;

    @ApiModelProperty("跳出率")
    private FlowType bounce;

    public FlowVO() {
        pv = new FlowType();
        uv = new FlowType();
        newVisits = new FlowType();
        ip = new FlowType();
        visitsPerCapita = new FlowType();
    }

    @Data
    @ApiModel("数据单元")
    private static class FlowType {

        @ApiModelProperty("当天的数据")
        private Long num;

        @ApiModelProperty("较昨日的比率")
        private Double rate;
    }
}
