package cn.org.twotomatoes.monitor.service;

import cn.org.twotomatoes.monitor.dto.R;
import cn.org.twotomatoes.monitor.entity.PaintTime;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *
 */
public interface PaintTimeService extends IService<PaintTime> {

    R<String> uploadPaintTime(PaintTime paintTime);
}
