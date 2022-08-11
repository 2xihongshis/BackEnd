package cn.org.twotomatoes.monitor.controller;

import cn.hutool.json.JSONUtil;
import cn.org.twotomatoes.monitor.dto.R;
import cn.org.twotomatoes.monitor.entity.FetchInfo;
import cn.org.twotomatoes.monitor.service.FetchInfoService;
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
@RequestMapping("fetch-info")
public class FetchInfoController {

    @Resource
    private FetchInfoService fetchInfoService;

    @PostMapping("upload")
    public R<String> uploadFetchInfo(@RequestBody FetchInfo fetchInfo) {
        log.info("上传数据 fetchInfo: {}", JSONUtil.toJsonStr(fetchInfo));

        return fetchInfoService.uploadFetchInfo(fetchInfo);
    }

}
