package cn.org.twotomatoes.monitor.service.impl;

import cn.org.twotomatoes.monitor.dto.R;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.org.twotomatoes.monitor.entity.PromiseError;
import cn.org.twotomatoes.monitor.service.PromiseErrorService;
import cn.org.twotomatoes.monitor.mapper.PromiseErrorMapper;
import org.springframework.stereotype.Service;

import static cn.org.twotomatoes.monitor.util.constant.RConstants.UPLOAD_FAIL;
import static cn.org.twotomatoes.monitor.util.constant.RConstants.UPLOAD_SUCCESS;

/**
 *
 */
@Service
public class PromiseErrorServiceImpl extends ServiceImpl<PromiseErrorMapper, PromiseError>
    implements PromiseErrorService{

    @Override
    public R<String> uploadPromiseError(PromiseError promiseError) {
        return save(promiseError) ? R.success(UPLOAD_SUCCESS) : R.error(UPLOAD_FAIL);
    }
}




