package com.beelego.sbadmin.local.filter;


import com.beelego.sbadmin.local.endpointinfo.Endpoint;
import com.beelego.sbadmin.local.endpointinfo.EndpointAbstractFactory;
import com.beelego.sbadmin.local.wrapper.WrapperResponse;
import de.codecentric.boot.admin.config.AdminServerProperties;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * @author : hama
 * @since : created in  2018/5/31
 */
@Slf4j
public class EndPointFilter implements Filter {

    private final String PREFIX = "/api/applications/**/";
    private Map<String, String> patterns = new HashMap<>();

    public EndPointFilter(AdminServerProperties.RoutesProperties routesProperties) {
        for (String endpoint : routesProperties.getEndpoints()) {
            patterns.put(PREFIX + endpoint, endpoint);
        }
        patterns.put(PREFIX + "health", "health");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req,
                         ServletResponse servletResponse,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        WrapperResponse response = new WrapperResponse((HttpServletResponse) servletResponse);
        String servletPath = request.getServletPath();
        log.debug("this is EndPointFilter,url :" + servletPath);
        if (!matchPath(servletPath)) {
            chain.doFilter(request, (HttpServletResponse) servletResponse);
            return;
        }
        chain.doFilter(req, response);
        byte[] contentByte = response.getContent();
        String result = "";
        try {
            result = new String(contentByte, "UTF-8");
        } catch (Exception e) {
            result = e.getMessage();
        }
        //
        Endpoint endpoint = EndpointAbstractFactory.getInstance(patterns).getEndpoint(adjustPath(servletPath));
        endpoint.convert(result);
        response.getWriter().write(new String(contentByte, "UTF-8"));
    }


    @Override
    public void destroy() {
        log.info("LoginFilter filter destroy");
    }


    private boolean matchPath(String path) {
        String adjusted = adjustPath(path);
        return null != patterns.get(adjusted);
    }

    private String adjustPath(String path) {
        log.debug("adjustPath path is : {}", path);
        String applicationId = path.replaceAll("/api/applications/|/(.*)", "");
        return path.replaceAll(applicationId, "**");
    }
}