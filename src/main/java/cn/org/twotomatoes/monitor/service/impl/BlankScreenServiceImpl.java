package cn.org.twotomatoes.monitor.service.impl;

import cn.org.twotomatoes.monitor.dto.R;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.org.twotomatoes.monitor.entity.BlankScreen;
import cn.org.twotomatoes.monitor.service.BlankScreenService;
import cn.org.twotomatoes.monitor.mapper.BlankScreenMapper;
import org.springframework.stereotype.Service;

import static cn.org.twotomatoes.monitor.util.constant.RConstants.UPLOAD_FAIL;
import static cn.org.twotomatoes.monitor.util.constant.RConstants.UPLOAD_SUCCESS;

/**
 *
 */
@Service
public class BlankScreenServiceImpl extends ServiceImpl<BlankScreenMapper, BlankScreen>
    implements BlankScreenService{

    @Override
    public R<String> uploadBlankScreen(BlankScreen blankScreen) {
        return save(blankScreen) ? R.success(UPLOAD_SUCCESS) : R.error(UPLOAD_FAIL);
    }
}




