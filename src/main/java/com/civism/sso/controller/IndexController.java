package com.civism.sso.controller;

import com.civism.sso.entity.LoginEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : Civism
 * @projectName : civism-sso
 * @E-mail : 695234456@qq.com
 */
@RestController
@RequestMapping("/sso")
@Api(tags = "登陆接口")
@Slf4j
public class IndexController {


    @ApiOperation("登陆")
    @PostMapping("/login")
    public Object login(@RequestBody LoginEntity loginEntity) {
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken();
        usernamePasswordToken.setUsername(loginEntity.getUserName());
        usernamePasswordToken.setPassword(loginEntity.getPassword().toCharArray());
        Subject subject = SecurityUtils.getSubject();
        subject.login(usernamePasswordToken);
        if (subject.isAuthenticated()) {
            return SecurityUtils.getSubject().getPrincipal();
        }
        return null;
    }

    @ApiOperation("鉴权")
    @GetMapping("/auth")
    public Boolean authenticate(HttpServletRequest request) {
        String servletPath = request.getServletPath();
        return SecurityUtils.getSubject().isPermitted(servletPath);
    }
}
