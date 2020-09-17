package com.civism.sso.controller;

import com.civism.sso.entity.LoginEntity;
import com.civism.sso.shiro.CustomUserPasswordToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
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
        CustomUserPasswordToken customUserPasswordToken = new CustomUserPasswordToken(loginEntity);
        SecurityUtils.getSubject().login(customUserPasswordToken);
        return SecurityUtils.getSubject().getPrincipal();
    }

    @ApiOperation("鉴权")
    @GetMapping("/auth")
    public Object authenticate(HttpServletRequest request) {
        String servletPath = request.getServletPath();
        return SecurityUtils.getSubject().isPermitted(servletPath);
    }
}
