package cn.org.twotomatoes.monitor.service;

import cn.org.twotomatoes.monitor.dto.R;
import cn.org.twotomatoes.monitor.entity.ResourceError;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *
 */
public interface ResourceErrorService extends IService<ResourceError> {

    R<String> uploadResourceError(ResourceError resourceError);
}
