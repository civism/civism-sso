package com.civism.sso.shiro;

import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;


/**
 * @author : Civism
 * @projectName : civism-sso
 * @E-mail : 695234456@qq.com
 */
public class ShiroSessionManager extends DefaultWebSessionManager {


    @Override
    protected Serializable getSessionId(ServletRequest httpRequest, ServletResponse response) {
        HttpServletRequest request = (HttpServletRequest) httpRequest;
        return request.getHeader("token");
    }
}
