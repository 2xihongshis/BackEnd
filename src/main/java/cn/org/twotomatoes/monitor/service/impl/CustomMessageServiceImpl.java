package cn.org.twotomatoes.monitor.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.org.twotomatoes.monitor.entity.CustomMessage;
import cn.org.twotomatoes.monitor.service.CustomMessageService;
import cn.org.twotomatoes.monitor.mapper.CustomMessageMapper;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class CustomMessageServiceImpl extends ServiceImpl<CustomMessageMapper, CustomMessage>
    implements CustomMessageService{

}




