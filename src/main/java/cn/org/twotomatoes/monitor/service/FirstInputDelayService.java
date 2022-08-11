package cn.org.twotomatoes.monitor.service;

import cn.org.twotomatoes.monitor.dto.R;
import cn.org.twotomatoes.monitor.entity.FirstInputDelay;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *
 */
public interface FirstInputDelayService extends IService<FirstInputDelay> {

    R<String> uploadFirstInputDelay(FirstInputDelay firstInputDelay);
}
