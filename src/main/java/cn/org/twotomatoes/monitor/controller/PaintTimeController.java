package cn.org.twotomatoes.monitor.controller;

import cn.hutool.json.JSONUtil;
import cn.org.twotomatoes.monitor.dto.R;
import cn.org.twotomatoes.monitor.entity.PaintTime;
import cn.org.twotomatoes.monitor.service.PaintTimeService;
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
@RequestMapping("paint-time")
public class PaintTimeController {

    @Resource
    private PaintTimeService paintTimeService;

    @ApiIgnore
    @PostMapping("upload")
    public R<String> uploadPaintTime(@RequestBody PaintTime paintTime) {
        log.info("上传数据 paintTime: {}", JSONUtil.toJsonStr(paintTime));

        return paintTimeService.uploadPaintTime(paintTime);
    }

}
