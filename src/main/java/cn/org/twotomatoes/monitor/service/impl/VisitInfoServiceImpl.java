package cn.org.twotomatoes.monitor.service.impl;

import cn.org.twotomatoes.monitor.dto.R;
import cn.org.twotomatoes.monitor.util.CountUVUtil;
import cn.org.twotomatoes.monitor.util.Holder;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.org.twotomatoes.monitor.entity.VisitInfo;
import cn.org.twotomatoes.monitor.service.VisitInfoService;
import cn.org.twotomatoes.monitor.mapper.VisitInfoMapper;
import org.springframework.stereotype.Service;

import static cn.org.twotomatoes.monitor.util.constant.HolderConstants.IP_HOLDER;


/**
 *
 */
@Service
public class VisitInfoServiceImpl extends ServiceImpl<VisitInfoMapper, VisitInfo>
    implements VisitInfoService{

    @Override
    public R<String> uploadVisitInfo(VisitInfo visitInfo) {
        CountUVUtil.addRecord(visitInfo.getUrl(), Holder.get(IP_HOLDER));

        return save(visitInfo) ? R.success() : R.fail();
    }
}




