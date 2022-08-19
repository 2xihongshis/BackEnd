package cn.org.twotomatoes.monitor.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.org.twotomatoes.monitor.common.MyRequestWrapper;
import cn.org.twotomatoes.monitor.dto.R;
import cn.org.twotomatoes.monitor.helper.UploadForwardHelper;
import cn.org.twotomatoes.monitor.util.Holder;
import cn.org.twotomatoes.monitor.util.IPUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static cn.org.twotomatoes.monitor.constant.ForwardConstants.FAIL_URL;
import static cn.org.twotomatoes.monitor.constant.ForwardConstants.VISIT_INFO_URL;
import static cn.org.twotomatoes.monitor.util.Holder.IP_HOLDER;
import static cn.org.twotomatoes.monitor.util.Holder.UUID_HOLDER;

/**
 * @author HeYunjia
 */
@Slf4j
@Controller
@CrossOrigin("*")
@Api(tags = "数据上传")
@RequestMapping("upload")
public class DataUploadController {

    /**
     * 上传数据中的标识字段
     */
    private static final String TYPE = "type";

    /**
     * 上传数据中的访问者标识
     */
    private static final String UUID = "uuid";

    @PostMapping
    @SneakyThrows
    @SuppressWarnings("all")
    @ApiOperation(value = "上传数据", notes = "所有数据上传的统一接口")
    public void upload(HttpServletRequest request, HttpServletResponse response) {
        String ipAddress = IPUtils.getIpAddress(request);
        log.info("ip 地址为 {} 的客户端上传数据", ipAddress);
        Holder.set(IP_HOLDER, ipAddress);

        MyRequestWrapper myRequest = new MyRequestWrapper(request);
        String data = myRequest.getBody();
        log.info("客户端上传的数据为: {}", data);

        JSONObject jsonObject = new JSONObject(data);
        String type = (String) jsonObject.get(TYPE);
        log.info("type 类型为: {}", type);

        String url = UploadForwardHelper.getURL(type);
        if (url.equals(FAIL_URL)) {
            log.info("不能识别的数据类型, 拦截");
            response.getWriter().write(JSONUtil.toJsonStr(R.fail()));
            return ;
        }

        if (url.equals(VISIT_INFO_URL)) {
            String uuid = (String) jsonObject.get(UUID);
            log.info("UUID 数值为: {}", uuid);
            Holder.set(UUID_HOLDER, uuid);
        }

        request.getRequestDispatcher(url).forward(myRequest, response);
    }

    @ApiIgnore
    @ResponseBody
    @PostMapping("fail")
    public R<String> fail() {
        return R.fail();
    }
}
