package cn.org.twotomatoes.monitor.service.impl;

import cn.org.twotomatoes.monitor.dto.R;
import cn.org.twotomatoes.monitor.helper.CountUVHelper;
import cn.org.twotomatoes.monitor.helper.FilterEntityHelper;
import cn.org.twotomatoes.monitor.util.Holder;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.org.twotomatoes.monitor.entity.VisitInfo;
import cn.org.twotomatoes.monitor.service.VisitInfoService;
import cn.org.twotomatoes.monitor.mapper.VisitInfoMapper;
import org.springframework.stereotype.Service;

import static cn.org.twotomatoes.monitor.util.Holder.IP_HOLDER;
import static cn.org.twotomatoes.monitor.util.Holder.UUID_HOLDER;


/**
 *
 */
@Service
public class VisitInfoServiceImpl extends ServiceImpl<VisitInfoMapper, VisitInfo>
    implements VisitInfoService{

    @Override
    public R<String> uploadVisitInfo(VisitInfo visitInfo) {
        CountUVHelper.addRecord(visitInfo.getUrl(),
                Holder.get(IP_HOLDER),
                Holder.get(UUID_HOLDER));

        return save(FilterEntityHelper.format(visitInfo))
                ? R.success()
                : R.fail();
    }
}




