package cn.org.twotomatoes.monitor.controller;

import cn.hutool.json.JSONUtil;
import cn.org.twotomatoes.monitor.dto.R;
import cn.org.twotomatoes.monitor.entity.BlankScreen;
import cn.org.twotomatoes.monitor.service.BlankScreenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author HeYunjia
 */
@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("blank-screen")
public class BlankScreenController {

    @Resource
    private BlankScreenService blankScreenService;

    @PostMapping("upload")
    public R<String> uploadBlankScreen(@RequestBody BlankScreen blankScreen) {
        log.info("上传数据 blankScreen: {}", JSONUtil.toJsonStr(blankScreen));

        return blankScreenService.uploadBlankScreen(blankScreen);
    }

}
