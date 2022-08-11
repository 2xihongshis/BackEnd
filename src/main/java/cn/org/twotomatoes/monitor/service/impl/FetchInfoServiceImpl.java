package cn.org.twotomatoes.monitor.service.impl;

import cn.org.twotomatoes.monitor.dto.R;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.org.twotomatoes.monitor.entity.FetchInfo;
import cn.org.twotomatoes.monitor.service.FetchInfoService;
import cn.org.twotomatoes.monitor.mapper.FetchInfoMapper;
import org.springframework.stereotype.Service;

import static cn.org.twotomatoes.monitor.util.constant.RConstants.UPLOAD_FAIL;
import static cn.org.twotomatoes.monitor.util.constant.RConstants.UPLOAD_SUCCESS;

/**
 *
 */
@Service
public class FetchInfoServiceImpl extends ServiceImpl<FetchInfoMapper, FetchInfo>
    implements FetchInfoService{

    @Override
    public R<String> uploadFetchInfo(FetchInfo fetchInfo) {
        return save(fetchInfo) ? R.success(UPLOAD_SUCCESS) : R.error(UPLOAD_FAIL);
    }
}




