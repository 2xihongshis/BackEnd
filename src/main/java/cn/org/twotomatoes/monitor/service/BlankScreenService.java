package cn.org.twotomatoes.monitor.service;

import cn.org.twotomatoes.monitor.dto.R;
import cn.org.twotomatoes.monitor.entity.BlankScreen;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *
 */
public interface BlankScreenService extends IService<BlankScreen> {

    R<String> uploadBlankScreen(BlankScreen blankScreen);
}
