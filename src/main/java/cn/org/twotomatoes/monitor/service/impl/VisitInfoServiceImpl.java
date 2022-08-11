package cn.org.twotomatoes.monitor.service.impl;

import cn.org.twotomatoes.monitor.dto.R;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.org.twotomatoes.monitor.entity.VisitInfo;
import cn.org.twotomatoes.monitor.service.VisitInfoService;
import cn.org.twotomatoes.monitor.mapper.VisitInfoMapper;
import org.springframework.stereotype.Service;

import static cn.org.twotomatoes.monitor.util.constant.RConstants.UPLOAD_FAIL;
import static cn.org.twotomatoes.monitor.util.constant.RConstants.UPLOAD_SUCCESS;

/**
 *
 */
@Service
public class VisitInfoServiceImpl extends ServiceImpl<VisitInfoMapper, VisitInfo>
    implements VisitInfoService{

    @Override
    public R<String> uploadVisitInfo(VisitInfo visitInfo) {
        return save(visitInfo) ? R.success(UPLOAD_SUCCESS) : R.error(UPLOAD_FAIL);
    }
}




