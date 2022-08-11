package cn.org.twotomatoes.monitor.controller;

import cn.hutool.json.JSONUtil;
import cn.org.twotomatoes.monitor.dto.R;
import cn.org.twotomatoes.monitor.entity.LoadTime;
import cn.org.twotomatoes.monitor.service.LoadTimeService;
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
@RequestMapping("load-time")
public class LoadTimeController {

    @Resource
    private LoadTimeService loadTimeService;

    @PostMapping("upload")
    public R<String> uploadLoadTime(@RequestBody LoadTime loadTime) {
        log.info("上传数据 loadTime: {}", JSONUtil.toJsonStr(loadTime));

        return loadTimeService.uploadLoadTime(loadTime);
    }

}
