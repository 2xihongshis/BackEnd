package cn.org.twotomatoes.monitor.service.impl;

import cn.org.twotomatoes.monitor.dto.R;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.org.twotomatoes.monitor.entity.XhrInfo;
import cn.org.twotomatoes.monitor.service.XhrInfoService;
import cn.org.twotomatoes.monitor.mapper.XhrInfoMapper;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class XhrInfoServiceImpl extends ServiceImpl<XhrInfoMapper, XhrInfo>
    implements XhrInfoService{

    @Override
    public R<String> uploadXhrInfo(XhrInfo xhrInfo) {
        return save(xhrInfo) ? R.success() : R.fail();
    }
}




