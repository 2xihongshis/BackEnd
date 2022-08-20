package cn.org.twotomatoes.monitor.controller;

import cn.hutool.json.JSONUtil;
import cn.org.twotomatoes.monitor.dto.R;
import cn.org.twotomatoes.monitor.entity.ResourceEntity;
import cn.org.twotomatoes.monitor.service.ResourceService;
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
@RequestMapping("resource")
public class ResourceController {

    @Resource
    private ResourceService resourceService;

    @ApiIgnore
    @PostMapping("upload")
    public R<String> uploadPromiseError(@RequestBody ResourceEntity resource) {
        log.info("上传数据 resource: {}", JSONUtil.toJsonStr(resource));

        return resourceService.uploadResource(resource);
    }

}
