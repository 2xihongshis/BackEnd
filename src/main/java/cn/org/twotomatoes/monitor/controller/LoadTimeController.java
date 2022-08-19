package cn.org.twotomatoes.monitor.controller;

import cn.hutool.json.JSONUtil;
import cn.org.twotomatoes.monitor.dto.R;
import cn.org.twotomatoes.monitor.entity.LoadTime;
import cn.org.twotomatoes.monitor.service.LoadTimeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;

/**
 * @author HeYunjia
 */
@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("load-time")
public class LoadTimeController {

    @Resource
    private LoadTimeService loadTimeService;

    @ApiIgnore
    @PostMapping("upload")
    public R<String> uploadLoadTime(@RequestBody LoadTime loadTime) {
        log.info("上传数据 loadTime: {}", JSONUtil.toJsonStr(loadTime));

        return loadTimeService.uploadLoadTime(loadTime);
    }

}
