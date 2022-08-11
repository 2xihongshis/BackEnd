package cn.org.twotomatoes.monitor.service.impl;

import cn.org.twotomatoes.monitor.dto.R;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.org.twotomatoes.monitor.entity.FirstInputDelay;
import cn.org.twotomatoes.monitor.service.FirstInputDelayService;
import cn.org.twotomatoes.monitor.mapper.FirstInputDelayMapper;
import org.springframework.stereotype.Service;

import static cn.org.twotomatoes.monitor.util.constant.RConstants.UPLOAD_FAIL;
import static cn.org.twotomatoes.monitor.util.constant.RConstants.UPLOAD_SUCCESS;

/**
 *
 */
@Service
public class FirstInputDelayServiceImpl extends ServiceImpl<FirstInputDelayMapper, FirstInputDelay>
    implements FirstInputDelayService{

    @Override
    public R<String> uploadFirstInputDelay(FirstInputDelay firstInputDelay) {
        return save(firstInputDelay) ? R.success(UPLOAD_SUCCESS) : R.error(UPLOAD_FAIL);
    }
}




