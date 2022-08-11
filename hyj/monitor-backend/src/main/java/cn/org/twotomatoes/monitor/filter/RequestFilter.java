package cn.org.twotomatoes.monitor.filter;

import cn.org.twotomatoes.monitor.util.IpUtils;
import cn.org.twotomatoes.monitor.util.Holder;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static cn.org.twotomatoes.monitor.util.constant.HolderConstants.IP_HOLDER;


/**
 * 对上传操作纪律 ip 地址
 *
 * @author HeYunjia
 */
@Slf4j
@WebFilter(filterName = "requestFilter", urlPatterns = "/upload")
public class RequestFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String ipAddress = IpUtils.getIpAddress(request);
        Holder.set(IP_HOLDER, ipAddress);
        log.info("ip 地址为 {} 的客户端上传数据", ipAddress);

        filterChain.doFilter(request, response);
    }
}
