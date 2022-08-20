package cn.org.twotomatoes.monitor.service;

import cn.org.twotomatoes.monitor.dto.R;
import cn.org.twotomatoes.monitor.entity.ResourceEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *
 */
public interface ResourceService extends IService<ResourceEntity> {

    R<String> uploadResource(ResourceEntity resource);
}
