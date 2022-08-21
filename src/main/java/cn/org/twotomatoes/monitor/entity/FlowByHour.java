package cn.org.twotomatoes.monitor.entity;

import lombok.Data;

/**
 * @author HeYunjia
 */
@Data
public class FlowByHour {

    private String id;

    private String pvNum;

    private String uvNum;

    private String newVisits;
}
