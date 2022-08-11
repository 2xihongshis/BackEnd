package cn.org.twotomatoes.monitor.service.impl;

import cn.org.twotomatoes.monitor.dto.R;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.org.twotomatoes.monitor.entity.LoadTime;
import cn.org.twotomatoes.monitor.service.LoadTimeService;
import cn.org.twotomatoes.monitor.mapper.LoadTimeMapper;
import org.springframework.stereotype.Service;

import static cn.org.twotomatoes.monitor.util.constant.RConstants.UPLOAD_FAIL;
import static cn.org.twotomatoes.monitor.util.constant.RConstants.UPLOAD_SUCCESS;

/**
 *
 */
@Service
public class LoadTimeServiceImpl extends ServiceImpl<LoadTimeMapper, LoadTime>
    implements LoadTimeService{

    @Override
    public R<String> uploadLoadTime(LoadTime loadTime) {
        return save(loadTime) ? R.success(UPLOAD_SUCCESS) : R.error(UPLOAD_FAIL);

    }
}




