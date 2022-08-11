package cn.org.twotomatoes.monitor.service.impl;

import cn.org.twotomatoes.monitor.dto.R;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.org.twotomatoes.monitor.entity.StayTime;
import cn.org.twotomatoes.monitor.service.StayTimeService;
import cn.org.twotomatoes.monitor.mapper.StayTimeMapper;
import org.springframework.stereotype.Service;

import static cn.org.twotomatoes.monitor.util.constant.RConstants.UPLOAD_FAIL;
import static cn.org.twotomatoes.monitor.util.constant.RConstants.UPLOAD_SUCCESS;

/**
 *
 */
@Service
public class StayTimeServiceImpl extends ServiceImpl<StayTimeMapper, StayTime>
    implements StayTimeService{

    @Override
    public R<String> uploadStayTime(StayTime stayTime) {
        return save(stayTime) ? R.success(UPLOAD_SUCCESS) : R.error(UPLOAD_FAIL);
    }
}




