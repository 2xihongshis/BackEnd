package cn.org.twotomatoes.monitor.controller;

import cn.hutool.json.JSONUtil;
import cn.org.twotomatoes.monitor.dto.R;
import cn.org.twotomatoes.monitor.entity.FetchInfo;
import cn.org.twotomatoes.monitor.service.FetchInfoService;
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
@RequestMapping("fetch-info")
public class FetchInfoController {

    @Resource
    private FetchInfoService fetchInfoService;

    @ApiIgnore
    @PostMapping("upload")
    public R<String> uploadFetchInfo(@RequestBody FetchInfo fetchInfo) {
        log.info("上传数据 fetchInfo: {}", JSONUtil.toJsonStr(fetchInfo));

        return fetchInfoService.uploadFetchInfo(fetchInfo);
    }

}
