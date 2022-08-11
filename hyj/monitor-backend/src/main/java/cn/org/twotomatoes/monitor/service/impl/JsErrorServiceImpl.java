package cn.org.twotomatoes.monitor.service.impl;

import cn.org.twotomatoes.monitor.dto.R;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.org.twotomatoes.monitor.entity.JsError;
import cn.org.twotomatoes.monitor.service.JsErrorService;
import cn.org.twotomatoes.monitor.mapper.JsErrorMapper;
import org.springframework.stereotype.Service;

import static cn.org.twotomatoes.monitor.util.constant.RConstants.UPLOAD_FAIL;
import static cn.org.twotomatoes.monitor.util.constant.RConstants.UPLOAD_SUCCESS;

/**
 *
 */
@Service
public class JsErrorServiceImpl extends ServiceImpl<JsErrorMapper, JsError>
    implements JsErrorService{

    @Override
    public R<String> uploadJsError(JsError jsError) {
        return save(jsError) ? R.success(UPLOAD_SUCCESS) : R.error(UPLOAD_FAIL);
    }
}




