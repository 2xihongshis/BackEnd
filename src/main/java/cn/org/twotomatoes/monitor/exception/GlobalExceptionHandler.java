package cn.org.twotomatoes.monitor.exception;

import cn.org.twotomatoes.monitor.dto.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 处理全局异常
 *
 * @author HeYunjia
 */

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public R<String> allException(Exception e) {
        log.info(e.getMessage());
        return R.error();
    }
}
