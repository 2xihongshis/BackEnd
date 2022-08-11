package cn.org.twotomatoes.monitor.controller;

import cn.org.twotomatoes.monitor.dto.R;
import cn.org.twotomatoes.monitor.dto.Test;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author HeYunjia
 */

@Slf4j
@RestController
@RequestMapping("test")
public class TestController {

    @PostMapping
    public R<Test> testForward(@RequestBody Test test) {
        log.info("访问 /test, test: {}", test.toString());
        return R.success(test);
    }
}
