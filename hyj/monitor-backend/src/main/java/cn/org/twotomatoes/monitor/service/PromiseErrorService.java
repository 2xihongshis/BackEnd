package cn.org.twotomatoes.monitor.service;

import cn.org.twotomatoes.monitor.dto.R;
import cn.org.twotomatoes.monitor.entity.PromiseError;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *
 */
public interface PromiseErrorService extends IService<PromiseError> {

    R<String> uploadPromiseError(PromiseError promiseError);
}
