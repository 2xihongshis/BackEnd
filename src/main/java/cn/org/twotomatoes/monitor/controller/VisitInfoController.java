package cn.org.twotomatoes.monitor.controller;

import cn.hutool.json.JSONUtil;
import cn.org.twotomatoes.monitor.dto.R;
import cn.org.twotomatoes.monitor.entity.VisitInfo;
import cn.org.twotomatoes.monitor.service.VisitInfoService;
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
@RequestMapping("visit-info")
public class VisitInfoController {

    @Resource
    private VisitInfoService visitInfoService;

    @PostMapping("upload")
    public R<String> uploadVisitInfo(@RequestBody VisitInfo visitInfo) {
        log.info("上传数据 visitInfo: {}", JSONUtil.toJsonStr(visitInfo));

        return visitInfoService.uploadVisitInfo(visitInfo);
    }

}