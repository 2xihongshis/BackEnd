package cn.org.twotomatoes.monitor.service;

import cn.org.twotomatoes.monitor.dto.R;
import cn.org.twotomatoes.monitor.entity.StayTime;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *
 */
public interface StayTimeService extends IService<StayTime> {

    R<String> uploadStayTime(StayTime stayTime);
}
