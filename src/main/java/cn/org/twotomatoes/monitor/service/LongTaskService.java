package cn.org.twotomatoes.monitor.service;

import cn.org.twotomatoes.monitor.dto.R;
import cn.org.twotomatoes.monitor.entity.LongTask;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *
 */
public interface LongTaskService extends IService<LongTask> {

    R<String> uploadLongTask(LongTask longTask);
}
