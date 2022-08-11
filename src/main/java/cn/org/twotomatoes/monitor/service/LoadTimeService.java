package cn.org.twotomatoes.monitor.service;

import cn.org.twotomatoes.monitor.dto.R;
import cn.org.twotomatoes.monitor.entity.LoadTime;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *
 */
public interface LoadTimeService extends IService<LoadTime> {

    R<String> uploadLoadTime(LoadTime loadTime);
}
