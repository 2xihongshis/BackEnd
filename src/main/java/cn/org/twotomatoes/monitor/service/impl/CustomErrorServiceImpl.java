package cn.org.twotomatoes.monitor.service.impl;

import cn.org.twotomatoes.monitor.dto.R;
import cn.org.twotomatoes.monitor.entity.CustomMessage;
import cn.org.twotomatoes.monitor.service.CustomMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.org.twotomatoes.monitor.entity.CustomError;
import cn.org.twotomatoes.monitor.service.CustomErrorService;
import cn.org.twotomatoes.monitor.mapper.CustomErrorMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
@Service
public class CustomErrorServiceImpl extends ServiceImpl<CustomErrorMapper, CustomError>
    implements CustomErrorService{

    @Resource
    private CustomMessageService customMessageService;

    @Override
    @Transactional
    public R<String> uploadCustomError(CustomError customError) {
        boolean save = save(customError);
        if (!save) return R.fail();

        Long customId = customError.getId();
        List<CustomMessage> messageList =
                customError.getMessage().stream().map(s -> {
                    CustomMessage customMessage = new CustomMessage();
                    customMessage.setMessage(s);
                    customMessage.setCustomId(customId);
                    return customMessage;
                }).collect(Collectors.toList());
        boolean saveBatch = customMessageService.saveBatch(messageList);

        return saveBatch ? R.success() : R.fail();
    }
}




