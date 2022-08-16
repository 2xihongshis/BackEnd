package cn.org.twotomatoes.monitor.service;

import cn.org.twotomatoes.monitor.dto.CustomErrorDTO;
import cn.org.twotomatoes.monitor.dto.R;
import cn.org.twotomatoes.monitor.entity.CustomError;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *
 */
public interface CustomErrorService extends IService<CustomError> {

    R<String> uploadCustomError(CustomErrorDTO customErrorDTO);
}
