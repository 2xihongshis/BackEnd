package cn.org.twotomatoes.monitor.controller;

import cn.hutool.json.JSONUtil;
import cn.org.twotomatoes.monitor.dto.R;
import cn.org.twotomatoes.monitor.entity.JsError;
import cn.org.twotomatoes.monitor.service.JsErrorService;
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
@RequestMapping("js-error")
public class JsErrorController {

    @Resource
    private JsErrorService jsErrorService;

    @PostMapping("upload")
    public R<String> uploadJsError(@RequestBody JsError jsError) {
        log.info("上传数据 jsError: {}", JSONUtil.toJsonStr(jsError));
        return jsErrorService.uploadJsError(jsError);
    }

}
