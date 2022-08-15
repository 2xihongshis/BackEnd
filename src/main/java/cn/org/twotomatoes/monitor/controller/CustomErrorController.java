package cn.org.twotomatoes.monitor.controller;

import cn.hutool.json.JSONUtil;
import cn.org.twotomatoes.monitor.dto.R;
import cn.org.twotomatoes.monitor.entity.CustomError;
import cn.org.twotomatoes.monitor.service.CustomErrorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author HeYunjia
 */
@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("custom-error")
public class CustomErrorController {

    @Resource
    private CustomErrorService customErrorService;

    @PostMapping("upload")
    public R<String> uploadCustomError(@RequestBody CustomError customError) {
        log.info("上传数据 customError: {}", JSONUtil.toJsonStr(customError));

        return customErrorService.uploadCustomError(customError);
    }
}
