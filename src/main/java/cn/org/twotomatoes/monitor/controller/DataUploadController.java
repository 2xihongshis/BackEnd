package cn.org.twotomatoes.monitor.controller;

import cn.hutool.json.JSONObject;
import cn.org.twotomatoes.monitor.dto.R;
import cn.org.twotomatoes.monitor.common.MyRequestWrapper;
import cn.org.twotomatoes.monitor.helper.UploadForwardHelper;
import cn.org.twotomatoes.monitor.util.Holder;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static cn.org.twotomatoes.monitor.constant.TypeConstants.VISIT_INFO_PV;
import static cn.org.twotomatoes.monitor.constant.TypeConstants.VISIT_INFO_UV;
import static cn.org.twotomatoes.monitor.util.Holder.UUID_HOLDER;

/**
 * @author HeYunjia
 */

@Slf4j
@Controller
@CrossOrigin("*")
@RequestMapping("upload")
public class DataUploadController {

    /**
     * 对上传的数据进行转发
     *
     * @param request 请求内容
     * @param response 响应内容
     */
    @PostMapping
    @SneakyThrows
    public void upload(HttpServletRequest request, HttpServletResponse response) {
        MyRequestWrapper myRequest = new MyRequestWrapper(request);

        String type = (String) new JSONObject(myRequest.getBody()).get("type");
        log.info("type 类型为: {}", type);
        if (type.equals(VISIT_INFO_PV) || type.equals(VISIT_INFO_UV)) {
            String uuid = (String) new JSONObject(myRequest.getBody()).get("uuid");
            Holder.set(UUID_HOLDER, uuid);
            log.info("UUID 数值为: {}", uuid);
        }
        request.getRequestDispatcher(UploadForwardHelper.getURL(type))
                .forward(myRequest, response);
    }

    @ResponseBody
    @PostMapping("fail")
    public R<String> fail() {
        return R.error("fail");
    }

}
