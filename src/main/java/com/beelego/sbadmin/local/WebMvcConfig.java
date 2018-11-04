package com.beelego.sbadmin.local;

import com.beelego.sbadmin.local.filter.EndPointFilter;
import com.beelego.sbadmin.local.filter.LoginFilter;
import de.codecentric.boot.admin.config.AdminServerProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : hama
 * @since : created in  2018/6/9
 */
@Slf4j
@Configuration
@Controller
public class WebMvcConfig extends WebMvcConfigurerAdapter {


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("test.jpg")
                .addResourceLocations("classpath:/META-INF/resources/spring-boot-admin-server-ui/");

        registry.addResourceHandler("login.html")
                .addResourceLocations("classpath:/META-INF/resources/spring-boot-admin-server-ui/login.html");
        registry.addResourceHandler("/jquery.min.js")
                .addResourceLocations("classpath:/META-INF/resources/spring-boot-admin-server-ui/jquery.min.js");
        registry.addResourceHandler("/bootstrap.min.css")
                .addResourceLocations("classpath:/META-INF/resources/spring-boot-admin-server-ui/bootstrap.min.css");

    }


    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        super.configurePathMatch(configurer);
        configurer.setUseSuffixPatternMatch(false);
    }


    @Bean
    public FilterRegistrationBean getDemoFilter() {
        LoginFilter demoFilter = new LoginFilter();
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(demoFilter);
        List<String> urlPatterns = new ArrayList<String>();
        urlPatterns.add("/*");//拦截路径，可以添加多个
        registrationBean.setUrlPatterns(urlPatterns);
        registrationBean.addInitParameter("excludedUris", "/login.html,/jquery.min.js,/bootstrap.min.css,/login,/api/applications");
        registrationBean.setOrder(1);
        return registrationBean;
    }


    @Autowired
    private AdminServerProperties adminServer;

    @Bean
    public FilterRegistrationBean endpointFilter() {
        EndPointFilter endPointFilter = new EndPointFilter(adminServer.getRoutes());
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(endPointFilter);
        List<String> urlPatterns = new ArrayList<String>();
        //urlPatterns.add("/api/applications");//拦截路径，可以添加多个
        urlPatterns.add("/api/applications/*");//拦截路径，可以添加多个
        registrationBean.setUrlPatterns(urlPatterns);
        registrationBean.setOrder(2);
        registrationBean.addInitParameter("routesProperties", adminServer.getRoutes().toString());
        return registrationBean;
    }

}

