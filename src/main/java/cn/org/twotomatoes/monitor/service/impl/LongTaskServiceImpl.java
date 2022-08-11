package cn.org.twotomatoes.monitor.service.impl;

import cn.org.twotomatoes.monitor.dto.R;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.org.twotomatoes.monitor.entity.LongTask;
import cn.org.twotomatoes.monitor.service.LongTaskService;
import cn.org.twotomatoes.monitor.mapper.LongTaskMapper;
import org.springframework.stereotype.Service;

import static cn.org.twotomatoes.monitor.util.constant.RConstants.UPLOAD_FAIL;
import static cn.org.twotomatoes.monitor.util.constant.RConstants.UPLOAD_SUCCESS;

/**
 *
 */
@Service
public class LongTaskServiceImpl extends ServiceImpl<LongTaskMapper, LongTask>
    implements LongTaskService{

    @Override
    public R<String> uploadLongTask(LongTask longTask) {
        return save(longTask) ? R.success(UPLOAD_SUCCESS) : R.error(UPLOAD_FAIL);
    }
}




