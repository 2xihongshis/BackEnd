package cn.org.twotomatoes.monitor.controller;

import cn.hutool.json.JSONUtil;
import cn.org.twotomatoes.monitor.dto.R;
import cn.org.twotomatoes.monitor.entity.XhrInfo;
import cn.org.twotomatoes.monitor.service.XhrInfoService;
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
@RequestMapping("xhr-info")
public class XhrInfoController {

    @Resource
    private XhrInfoService xhrInfoService;

    @ApiIgnore
    @PostMapping("upload")
    public R<String> uploadXhrInfo(@RequestBody XhrInfo xhrInfo) {
        log.info("上传数据 xhrInfo: {}", JSONUtil.toJsonStr(xhrInfo));

        return xhrInfoService.uploadXhrInfo(xhrInfo);
    }

}
