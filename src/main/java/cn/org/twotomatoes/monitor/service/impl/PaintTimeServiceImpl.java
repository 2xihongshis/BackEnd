package cn.org.twotomatoes.monitor.service.impl;

import cn.org.twotomatoes.monitor.dto.R;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.org.twotomatoes.monitor.entity.PaintTime;
import cn.org.twotomatoes.monitor.service.PaintTimeService;
import cn.org.twotomatoes.monitor.mapper.PaintTimeMapper;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class PaintTimeServiceImpl extends ServiceImpl<PaintTimeMapper, PaintTime>
    implements PaintTimeService{

    @Override
    public R<String> uploadPaintTime(PaintTime paintTime) {
        return save(paintTime) ? R.success() : R.fail();
    }
}




