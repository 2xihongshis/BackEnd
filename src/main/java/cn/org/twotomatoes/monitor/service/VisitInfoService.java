package cn.org.twotomatoes.monitor.service;

import cn.org.twotomatoes.monitor.dto.R;
import cn.org.twotomatoes.monitor.entity.VisitInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *
 */
public interface VisitInfoService extends IService<VisitInfo> {

    R<String> uploadVisitInfo(VisitInfo visitInfo);
}
