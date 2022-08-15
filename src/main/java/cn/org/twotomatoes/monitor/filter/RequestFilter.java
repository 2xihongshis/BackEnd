package cn.org.twotomatoes.monitor.filter;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.org.twotomatoes.monitor.common.MyRequestWrapper;
import cn.org.twotomatoes.monitor.dto.R;
import cn.org.twotomatoes.monitor.helper.UploadForwardHelper;
import cn.org.twotomatoes.monitor.util.IPUtils;
import cn.org.twotomatoes.monitor.util.Holder;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static cn.org.twotomatoes.monitor.constant.ForwardConstants.FAIL_URL;
import static cn.org.twotomatoes.monitor.constant.ForwardConstants.VISIT_INFO_URL;
import static cn.org.twotomatoes.monitor.util.Holder.IP_HOLDER;
import static cn.org.twotomatoes.monitor.util.Holder.UUID_HOLDER;


/**
 * 对上传操作纪律 ip 地址
 *
 * @author HeYunjia
 */
@Slf4j
@WebFilter(filterName = "requestFilter", urlPatterns = "/upload")
public class RequestFilter implements Filter {

    /**
     * 拦截非 POST 的请求
     */
    private static final String POST = "POST";

    /**
     * 上传数据中的标识字段
     */
    private static final String TYPE = "type";

    /**
     * 上传数据中的访问者标识
     */
    private static final String UUID = "uuid";

    @Override
    @SuppressWarnings("all")
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String ipAddress = IPUtils.getIpAddress(request);
        log.info("ip 地址为 {} 的客户端上传数据", ipAddress);
        Holder.set(IP_HOLDER, ipAddress);

        MyRequestWrapper myRequest = new MyRequestWrapper(request);
        String data = myRequest.getBody();
        log.info("客户端上传的数据为: {}", data);

        if (!POST.equals(request.getMethod())) {
            log.info("非 POST 请求, 拦截");
            response.getWriter().write(JSONUtil.toJsonStr(R.fail()));
            return;
        }

        JSONObject jsonObject = new JSONObject(data);
        String type = (String) jsonObject.get(TYPE);
        log.info("type 类型为: {}", type);

        String url = UploadForwardHelper.getURL(type);
        if (url.equals(FAIL_URL)) {
            log.info("不能识别的数据类型, 拦截");
            response.getWriter().write(JSONUtil.toJsonStr(R.fail()));
            return;
        }

        if (url.equals(VISIT_INFO_URL)) {
            String uuid = (String) jsonObject.get(UUID);
            log.info("UUID 数值为: {}", uuid);
            Holder.set(UUID_HOLDER, uuid);
        }

        request.getRequestDispatcher(url).forward(myRequest, response);
    }
}
