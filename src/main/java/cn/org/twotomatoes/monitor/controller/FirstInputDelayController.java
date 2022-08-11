package cn.org.twotomatoes.monitor.controller;

import cn.hutool.json.JSONUtil;
import cn.org.twotomatoes.monitor.dto.R;
import cn.org.twotomatoes.monitor.entity.FirstInputDelay;
import cn.org.twotomatoes.monitor.service.FirstInputDelayService;
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
@RequestMapping("first-input-delay")
public class FirstInputDelayController {

    @Resource
    private FirstInputDelayService firstInputDelayService;

    @PostMapping("upload")
    public R<String> uploadFirstInputDelay(@RequestBody FirstInputDelay firstInputDelay) {
        log.info("上传数据 firstInputDelay: {}", JSONUtil.toJsonStr(firstInputDelay));

        return firstInputDelayService.uploadFirstInputDelay(firstInputDelay);
    }

}