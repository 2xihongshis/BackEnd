package cn.org.twotomatoes.monitor.controller;

import cn.org.twotomatoes.monitor.dto.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author HeYunjia
 */

@Slf4j
@RestController
@RequestMapping("/upload")
public class DataUploadController {

    @PostMapping
    public R<String> upload() {
        log.info("upload 被访问");
        return R.success("success");
    }
}
