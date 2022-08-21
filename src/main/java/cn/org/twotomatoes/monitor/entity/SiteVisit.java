package cn.org.twotomatoes.monitor.entity;

import cn.org.twotomatoes.monitor.dto.InfoEntity;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.Date;
import java.util.List;

/**
 * @author HeYunjia
 */
@Data
public class SiteVisit {

    private String id;

    @Indexed
    private Date timestamp;

    private List<InfoEntity> site;
}
