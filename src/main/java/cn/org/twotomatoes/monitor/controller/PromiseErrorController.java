package cn.org.twotomatoes.monitor.controller;

import cn.hutool.json.JSONUtil;
import cn.org.twotomatoes.monitor.dto.R;
import cn.org.twotomatoes.monitor.entity.PromiseError;
import cn.org.twotomatoes.monitor.service.PromiseErrorService;
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
@RequestMapping("promise-error")
public class PromiseErrorController {

    @Resource
    private PromiseErrorService promiseErrorService;

    @ApiIgnore
    @PostMapping("upload")
    public R<String> uploadPromiseError(@RequestBody PromiseError promiseError) {
        log.info("上传数据 promiseError: {}", JSONUtil.toJsonStr(promiseError));

        return promiseErrorService.uploadPromiseError(promiseError);
    }

}
