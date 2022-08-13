package cn.org.twotomatoes.monitor.service.impl;

import cn.org.twotomatoes.monitor.dto.R;
import cn.org.twotomatoes.monitor.helper.FilterEntityHelper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.org.twotomatoes.monitor.entity.StayTime;
import cn.org.twotomatoes.monitor.service.StayTimeService;
import cn.org.twotomatoes.monitor.mapper.StayTimeMapper;
import org.springframework.stereotype.Service;


/**
 *
 */
@Service
public class StayTimeServiceImpl extends ServiceImpl<StayTimeMapper, StayTime>
    implements StayTimeService{

    @Override
    public R<String> uploadStayTime(StayTime stayTime) {
        return save(FilterEntityHelper.format(stayTime))
                ? R.success()
                : R.fail();
    }
}




