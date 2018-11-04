package com.beelego.sbadmin.local.aspect;

import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import org.apache.commons.io.IOUtils;

/**
 * @author : hama
 * @since : created in  2018/10/22
 */

@Slf4j
@Aspect
@Component
public class WebLogAspect {
    @Pointcut("execution(* org.springframework.cloud.netflix.zuul.filters.route.SimpleHostRoutingFilter.run(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 记录下请求内容
        log.info("URL : " + request.getRequestURL().toString());
        //log.info("HTTP_METHOD : " + request.getMethod());
        //log.info("IP : " + request.getRemoteAddr());
        //log.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        //log.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));

    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        RequestContext requestContext = RequestContext.getCurrentContext();
        if (!requestContext.containsKey("zuulResponse")) {
            log.info("");
            log.info("");
            log.info("");
            log.info("");
            return;
        }
        log(requestContext.get("zuulResponse"));
    }


    public void log(Object contextResponse) throws IOException {
        HttpResponse response = (HttpResponse) contextResponse;
        log.info("{}", response);
        HttpEntity httpEntity = response.getEntity();
        String result = EntityUtils.toString(httpEntity);
        log.info("requestContext : " + result);
        log.info("");
        log.info("");
        log.info("");
        log.info("");

    }
}
