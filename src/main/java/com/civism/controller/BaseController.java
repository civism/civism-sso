package com.civism.controller;

import com.civism.constants.SsoConstants;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author star
 * @date 2018/8/17 下午4:30
 */
public class BaseController {

    public String getToken(HttpServletRequest request) {
        if (StringUtils.isNotEmpty(request.getHeader(SsoConstants.TOKEN))) {
            return request.getHeader(SsoConstants.TOKEN);
        } else if (StringUtils.isNotEmpty(request.getParameter(SsoConstants.TOKEN))) {
            return request.getParameter(SsoConstants.TOKEN);
        } else {
            Cookie[] cookies = request.getCookies();
            String token;
            if (cookies != null && cookies.length > 0) {
                for (Cookie cookie : cookies) {
                    if (SsoConstants.SESSIONID.equals(cookie.getName())) {
                        token = cookie.getValue();
                        return token;
                    }
                }
            }
            return null;
        }
    }
}
