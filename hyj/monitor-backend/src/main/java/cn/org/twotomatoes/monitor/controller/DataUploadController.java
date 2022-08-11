package cn.org.twotomatoes.monitor.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.org.twotomatoes.monitor.dto.R;
import cn.org.twotomatoes.monitor.util.MyRequestWrapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author HeYunjia
 */

@Slf4j
@Controller
@RequestMapping("upload")
public class DataUploadController {

    @PostMapping
    @SneakyThrows
    public void upload(HttpServletRequest request, HttpServletResponse response) {
        log.info("upload 被访问");
        MyRequestWrapper myRequest = new MyRequestWrapper(request);
        String type = (String) new JSONObject(myRequest.getBody()).get("type");
        log.info(type);
        if (StrUtil.isBlank(type)) {
            request.getRequestDispatcher("/upload/fail").forward(myRequest, response);
            return;
        }

        request.getRequestDispatcher("/test").forward(myRequest, response);
    }

    @ResponseBody
    @PostMapping("fail")
    public R<String> fail() {
        return R.error("fail");
    }

}
