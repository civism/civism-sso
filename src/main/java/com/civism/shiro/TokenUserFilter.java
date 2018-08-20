package com.civism.shiro;


import com.alibaba.fastjson.JSON;
import com.civism.error.ErrorType;
import com.civism.utils.SsoResponse;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


/**
 * @author star
 * @date 2018/3/20 下午1:19
 */
public class TokenUserFilter extends UserFilter {

    private static final Logger logger = LoggerFactory.getLogger(TokenUserFilter.class);


    private static List<String> validateList = null;

    static {
        validateList = new ArrayList<>();
        validateList.add("/authorize");
    }


    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object mappedValue) {
        String url = getPathWithinApplication(servletRequest);
        if (!validateList.contains(url)) {
            return false;
        }
        return true;
    }


    @Override
    protected boolean onAccessDenied(ServletRequest req, ServletResponse resp, Object mappedValue) throws Exception {
        HttpServletResponse response = (HttpServletResponse) resp;
        PrintWriter out = null;
        try {
            out = response.getWriter();
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Type", "application/json;charset=utf-8");
            response.setContentType("application/json");
            out.write(JSON.toJSONString(new SsoResponse(ErrorType.PATH_ERROR)));
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
