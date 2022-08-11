package cn.org.twotomatoes.monitor.service;

import cn.org.twotomatoes.monitor.dto.R;
import cn.org.twotomatoes.monitor.entity.JsError;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *
 */
public interface JsErrorService extends IService<JsError> {

    R<String> uploadJsError(JsError jsError);
}
