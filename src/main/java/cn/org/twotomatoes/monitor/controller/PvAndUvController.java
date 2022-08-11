package cn.org.twotomatoes.monitor.controller;

import cn.hutool.json.JSONUtil;
import cn.org.twotomatoes.monitor.dto.R;
import cn.org.twotomatoes.monitor.entity.PvAndUv;
import cn.org.twotomatoes.monitor.service.PvAndUvService;
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
@RequestMapping("pv-uv")
public class PvAndUvController {

    @Resource
    private PvAndUvService pvAndUvService;

    @PostMapping("upload")
    public R<String> uploadPvAndUv(@RequestBody PvAndUv pvAndUv) {
        log.info("上传数据 pvAndUv: {}", JSONUtil.toJsonStr(pvAndUv));

        return pvAndUvService.uploadPvAndUv(pvAndUv);
    }

}

