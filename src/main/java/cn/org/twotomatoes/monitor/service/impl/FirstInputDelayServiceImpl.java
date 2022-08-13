package cn.org.twotomatoes.monitor.service.impl;

import cn.org.twotomatoes.monitor.dto.R;
import cn.org.twotomatoes.monitor.helper.FilterEntityHelper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.org.twotomatoes.monitor.entity.FirstInputDelay;
import cn.org.twotomatoes.monitor.service.FirstInputDelayService;
import cn.org.twotomatoes.monitor.mapper.FirstInputDelayMapper;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class FirstInputDelayServiceImpl extends ServiceImpl<FirstInputDelayMapper, FirstInputDelay>
    implements FirstInputDelayService{

    @Override
    public R<String> uploadFirstInputDelay(FirstInputDelay firstInputDelay) {
        return save(FilterEntityHelper.format(firstInputDelay))
                ? R.success()
                : R.fail();
    }
}




