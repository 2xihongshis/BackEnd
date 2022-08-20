package cn.org.twotomatoes.monitor.service.impl;

import cn.org.twotomatoes.monitor.dto.R;
import cn.org.twotomatoes.monitor.helper.FilterEntityHelper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.org.twotomatoes.monitor.entity.ResourceEntity;
import cn.org.twotomatoes.monitor.service.ResourceService;
import cn.org.twotomatoes.monitor.mapper.ResourceEntryMapper;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceEntryMapper, ResourceEntity>
    implements ResourceService {

    @Override
    public R<String> uploadResource(ResourceEntity resource) {
        return save(FilterEntityHelper.format(resource))
                ? R.success()
                : R.fail();
    }
}




