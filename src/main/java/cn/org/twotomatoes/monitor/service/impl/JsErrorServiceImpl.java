package cn.org.twotomatoes.monitor.service.impl;

import cn.org.twotomatoes.monitor.dto.R;
import cn.org.twotomatoes.monitor.helper.FilterEntityHelper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.org.twotomatoes.monitor.entity.JsError;
import cn.org.twotomatoes.monitor.service.JsErrorService;
import cn.org.twotomatoes.monitor.mapper.JsErrorMapper;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class JsErrorServiceImpl extends ServiceImpl<JsErrorMapper, JsError>
    implements JsErrorService{

    @Override
    public R<String> uploadJsError(JsError jsError) {
        return save(FilterEntityHelper.format(jsError))
                ? R.success()
                : R.fail();
    }
}




