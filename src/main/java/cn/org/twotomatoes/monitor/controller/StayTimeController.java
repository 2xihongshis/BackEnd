package cn.org.twotomatoes.monitor.controller;

import cn.hutool.json.JSONUtil;
import cn.org.twotomatoes.monitor.dto.R;
import cn.org.twotomatoes.monitor.entity.StayTime;
import cn.org.twotomatoes.monitor.service.StayTimeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author HeYunjia
 */
@Slf4j
@RestController
@RequestMapping("stay-time")
public class StayTimeController {

    @Resource
    private StayTimeService stayTimeService;

    @PostMapping("upload")
    public R<String> uploadStayTime(@RequestBody StayTime stayTime) {
        log.info("上传数据 stayTime: {}", JSONUtil.toJsonStr(stayTime));

        return stayTimeService.uploadStayTime(stayTime);
    }

}
