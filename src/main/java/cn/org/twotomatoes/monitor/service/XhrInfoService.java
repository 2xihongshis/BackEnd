package cn.org.twotomatoes.monitor.service;

import cn.org.twotomatoes.monitor.dto.R;
import cn.org.twotomatoes.monitor.entity.XhrInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *
 */
public interface XhrInfoService extends IService<XhrInfo> {

    R<String> uploadXhrInfo(XhrInfo xhrInfo);
}
