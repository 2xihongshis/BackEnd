package cn.org.twotomatoes.monitor.service.impl;

import cn.org.twotomatoes.monitor.dto.R;
import cn.org.twotomatoes.monitor.helper.FilterEntityHelper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.org.twotomatoes.monitor.entity.BlankScreen;
import cn.org.twotomatoes.monitor.service.BlankScreenService;
import cn.org.twotomatoes.monitor.mapper.BlankScreenMapper;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class BlankScreenServiceImpl extends ServiceImpl<BlankScreenMapper, BlankScreen>
    implements BlankScreenService{

    @Override
    public R<String> uploadBlankScreen(BlankScreen blankScreen) {
        return save(FilterEntityHelper.format(blankScreen))
                ? R.success()
                : R.fail();
    }
}




