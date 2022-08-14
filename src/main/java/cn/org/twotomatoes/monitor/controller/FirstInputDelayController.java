package cn.org.twotomatoes.monitor.controller;

import cn.hutool.json.JSONUtil;
import cn.org.twotomatoes.monitor.dto.R;
import cn.org.twotomatoes.monitor.entity.FirstInputDelay;
import cn.org.twotomatoes.monitor.service.FirstInputDelayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author HeYunjia
 */
@Slf4j
@RestController
@CrossOrigin("*")
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
