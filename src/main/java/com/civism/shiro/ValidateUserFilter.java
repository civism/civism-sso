package com.civism.shiro;

import com.alibaba.fastjson.JSON;

import com.civism.dao.RedisClient;
import com.civism.error.ErrorType;
import com.civism.utils.SsoResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

/**
 * @author star
 * @date 2018/6/28 上午10:51
 */
public class ValidateUserFilter extends UserFilter {

    private static final Logger logger = LoggerFactory.getLogger(ValidateUserFilter.class);

    @Resource
    private RedisClient redisClient;


    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        boolean existSession = SecurityUtils.getSubject().isAuthenticated();
        if (!existSession) {
            return false;
        } else {
            Session session = SecurityUtils.getSubject().getSession(false);
            if (session != null) {
                Serializable id = session.getId();
                if (id != null) {
                    if (redisClient.get((String) id) != null) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse resp) throws Exception {
        HttpServletResponse response = (HttpServletResponse) resp;
        PrintWriter out = null;
        try {
            out = response.getWriter();
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Type", "application/json;charset=utf-8");
            response.setContentType("application/json");
            out.write(JSON.toJSONString(new SsoResponse(ErrorType.TOKEN_INVALID)));
        } catch (IOException e) {
            logger.error("error onAccessDenied", e);
            return false;
        } finally {
            if (out != null) {
                out.close();
            }
        }
        return false;
    }
}
