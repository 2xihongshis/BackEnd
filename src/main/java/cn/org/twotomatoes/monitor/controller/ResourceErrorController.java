package cn.org.twotomatoes.monitor.controller;

import cn.hutool.json.JSONUtil;
import cn.org.twotomatoes.monitor.dto.R;
import cn.org.twotomatoes.monitor.entity.ResourceError;
import cn.org.twotomatoes.monitor.service.ResourceErrorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author HeYunjia
 */
@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("resource-error")
public class ResourceErrorController {

    @Resource
    private ResourceErrorService resourceErrorService;

    @PostMapping("upload")
    public R<String> uploadResourceError(@RequestBody ResourceError resourceError) {
        log.info("上传数据 resourceError: {}", JSONUtil.toJsonStr(resourceError));

        return resourceErrorService.uploadResourceError(resourceError);
    }

}
