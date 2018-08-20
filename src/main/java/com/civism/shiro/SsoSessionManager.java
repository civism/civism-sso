package com.civism.shiro;

import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;


/**
 * @author star
 * @date 2018/5/23 上午10:43
 */
public class SsoSessionManager extends DefaultWebSessionManager {


    @Override
    protected Serializable getSessionId(ServletRequest httpRequest, ServletResponse response) {
        HttpServletRequest request = (HttpServletRequest) httpRequest;
        return request.getHeader("token");
    }
}
