package com.beelego.sbadmin.local.filter;


import com.beelego.sbadmin.local.IpUtils;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * @author : hama
 * @since : created in  2018/5/31
 */
@Slf4j
public class LoginFilter implements Filter {

    private String[] excludedUris;

    private static final List<String> EXCLUDE_URI = new ArrayList<>();


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        excludedUris = filterConfig.getInitParameter("excludedUris").split(",");
        EXCLUDE_URI.add("/login.html");
        EXCLUDE_URI.add("/jquery.min.js");
        EXCLUDE_URI.add("/bootstrap.min.css");
        EXCLUDE_URI.add("/login");
        EXCLUDE_URI.add("/api/applications");
        EXCLUDE_URI.add("/api/journal");
        EXCLUDE_URI.add("/img/favicon.png");
        EXCLUDE_URI.add("/login");
        log.info("LoginFilter filter init, excludedUris is {}", excludedUris);


    }

    @Override
    public void doFilter(ServletRequest req,
                         ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {
        log.info("LoginFilter filter doFilter");
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;
        String uri = request.getServletPath();
        log.info("this is LoginFilter,url :" + uri);
        String ip = IpUtils.getIp(request);
        Object obj = request.getSession().getAttribute("swaggerLogin" + ip);
        if (!isExcludedUri(uri)) {
            if (null == obj || !(obj instanceof Boolean)) {
                String redirect = request.getRequestURL().toString();
                response.sendRedirect(request.getContextPath()+"/login.html");
                return;
            }
        } else {
            log.info("isExcludedUri(){}", uri);
            log.info("");
            log.info("");
            log.info("");
        }

        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {
        log.info("LoginFilter filter destroy");
    }

    private boolean isExcludedUri(String uri) {
        if (excludedUris == null || excludedUris.length <= 0) {
            return false;
        }
        for (String exclude : excludedUris) {
            uri = uri.trim();
            exclude = exclude.trim();
            if (uri.toLowerCase().matches(exclude.toLowerCase().replace("*", ".*"))
                    || EXCLUDE_URI.contains(uri))
                return true;
        }
        return false;
    }
}