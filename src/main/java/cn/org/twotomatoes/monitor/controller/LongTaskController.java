package cn.org.twotomatoes.monitor.controller;

import cn.hutool.json.JSONUtil;
import cn.org.twotomatoes.monitor.dto.R;
import cn.org.twotomatoes.monitor.entity.LongTask;
import cn.org.twotomatoes.monitor.service.LongTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author HeYunjia
 */
@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("long-task")
public class LongTaskController {

    @Resource
    private LongTaskService longTaskService;

    @PostMapping("upload")
    public R<String> uploadLongTask(@RequestBody LongTask longTask) {
        log.info("上传数据 longTask: {}", JSONUtil.toJsonStr(longTask));

        return longTaskService.uploadLongTask(longTask);
    }

}
