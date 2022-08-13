package cn.org.twotomatoes.monitor.service.impl;

import cn.org.twotomatoes.monitor.dto.R;
import cn.org.twotomatoes.monitor.helper.FilterEntityHelper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.org.twotomatoes.monitor.entity.FetchInfo;
import cn.org.twotomatoes.monitor.service.FetchInfoService;
import cn.org.twotomatoes.monitor.mapper.FetchInfoMapper;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class FetchInfoServiceImpl extends ServiceImpl<FetchInfoMapper, FetchInfo>
    implements FetchInfoService{

    @Override
    public R<String> uploadFetchInfo(FetchInfo fetchInfo) {
        return save(FilterEntityHelper.format(fetchInfo))
                ? R.success()
                : R.fail();
    }
}




