package cn.org.twotomatoes.monitor.service;

import cn.org.twotomatoes.monitor.dto.R;
import cn.org.twotomatoes.monitor.entity.FetchInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *
 */
public interface FetchInfoService extends IService<FetchInfo> {

    R<String> uploadFetchInfo(FetchInfo fetchInfo);
}
