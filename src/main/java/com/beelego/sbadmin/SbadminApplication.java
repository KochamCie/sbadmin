package com.beelego.sbadmin;

import com.beelego.sbadmin.local.IpUtils;
import de.codecentric.boot.admin.config.EnableAdminServer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SpringBootApplication
@EnableAdminServer
@RestController
@Slf4j
public class SbadminApplication {

    @Value("${sba.loginUsername}")
    private String sbaLoginUsername;

    @Value("${sba.loginPassword}")
    private String sbaLoginPassword;

    public static void main(String[] args) {
        SpringApplication.run(SbadminApplication.class, args);
    }


    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public Object swaggerLogin(HttpServletRequest request,
                               HttpServletResponse response,
                               String loginUsername,
                               String loginPassword) {

        String ip = IpUtils.getIp(request);
        log.info("{} access to sba login {},{}", ip, loginUsername, "******");
        log.info("{} access to sba login {},{}", ip, sbaLoginUsername, sbaLoginPassword);
        if (StringUtils.isBlank(sbaLoginUsername)
                || StringUtils.isBlank(sbaLoginPassword)) {
            return true;
        }

        if (StringUtils.isBlank(loginUsername)
                || StringUtils.isBlank(loginPassword)) {
            return false;
        }

        if (loginUsername.equals(sbaLoginUsername)
                && loginPassword.equals(sbaLoginPassword)) {
            request.getSession().setAttribute("swaggerLogin" + ip, true);
            request.getSession().setMaxInactiveInterval(1800);
            return true;
        }
        log.info("{}", loginUsername.equals(sbaLoginUsername));
        log.info("{}", loginPassword.equals(sbaLoginPassword));
        log.info("{} try to use {},{} login sba!!!", ip, loginUsername, "******");
        log.info("");
        log.info("");
        log.info("");
        log.info("");
        return false;
    }


}
