package cn.org.twotomatoes.monitor.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.Date;

/**
 * 保存一天的流量数据
 *
 * @author HeYunjia
 */
@Data
public class FlowByDay {

    private String id;

    @Indexed
    private Date timestamp;

    private Long pvNum;

    private Long uvNum;

    private Long newVisits;

    private Long ipNum;

    private Long VisitsPerCapita;

    private Double bounce;

    private Long yesterdayVisits;
    /**
     * 用户留存率
     */
    private Long retention;
}
