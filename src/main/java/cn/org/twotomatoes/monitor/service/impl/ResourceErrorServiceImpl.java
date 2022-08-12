package cn.org.twotomatoes.monitor.service.impl;

import cn.org.twotomatoes.monitor.dto.R;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.org.twotomatoes.monitor.entity.ResourceError;
import cn.org.twotomatoes.monitor.service.ResourceErrorService;
import cn.org.twotomatoes.monitor.mapper.ResourceErrorMapper;
import org.springframework.stereotype.Service;


/**
 *
 */
@Service
public class ResourceErrorServiceImpl extends ServiceImpl<ResourceErrorMapper, ResourceError>
    implements ResourceErrorService{

    @Override
    public R<String> uploadResourceError(ResourceError resourceError) {
        return save(resourceError) ? R.success() : R.fail();
    }
}




