package cn.org.twotomatoes.monitor.service;

import cn.org.twotomatoes.monitor.dto.R;
import cn.org.twotomatoes.monitor.entity.PvAndUv;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *
 */
public interface PvAndUvService extends IService<PvAndUv> {

    R<String> uploadPvAndUv(PvAndUv pvAndUv);
}
