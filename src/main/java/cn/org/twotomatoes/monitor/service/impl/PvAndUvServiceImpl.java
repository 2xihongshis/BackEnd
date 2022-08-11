package cn.org.twotomatoes.monitor.service.impl;

import cn.org.twotomatoes.monitor.dto.R;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.org.twotomatoes.monitor.entity.PvAndUv;
import cn.org.twotomatoes.monitor.service.PvAndUvService;
import cn.org.twotomatoes.monitor.mapper.PvAndUvMapper;
import org.springframework.stereotype.Service;

import static cn.org.twotomatoes.monitor.util.constant.RConstants.UPLOAD_SUCCESS;

/**
 *
 */
@Service
public class PvAndUvServiceImpl extends ServiceImpl<PvAndUvMapper, PvAndUv>
    implements PvAndUvService{

    @Override
    public R<String> uploadPvAndUv(PvAndUv pvAndUv) {
        return R.success(UPLOAD_SUCCESS);
    }
}




