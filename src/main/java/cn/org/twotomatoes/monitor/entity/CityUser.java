package cn.org.twotomatoes.monitor.entity;

import cn.org.twotomatoes.monitor.dto.InfoEntity;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.Date;
import java.util.List;

/**
 * 保存每天的城市用户数据
 *
 * @author HeYunjia
 */

@Data
public class CityUser {

    private String id;

    @Indexed
    private Date timestamp;

    private List<InfoEntity> province;

    private List<InfoEntity> country;
}
