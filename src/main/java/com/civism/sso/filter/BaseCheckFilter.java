package com.civism.sso.filter;

import com.alibaba.fastjson.JSON;
import com.civism.sso.entity.Response;
import com.civism.sso.exception.CustomException;
import org.apache.shiro.web.filter.authc.UserFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author : Civism
 * @projectName : civism-sso
 * @E-mail : 695234456@qq.com
 */
public abstract class BaseCheckFilter extends UserFilter {

    protected abstract void checkFail() throws CustomException;

    @Override
    protected boolean onAccessDenied(ServletRequest req, ServletResponse resp)
            throws Exception {

        PrintWriter out = null;
        try {
            checkFail();
            return super.onAccessDenied(req, resp);
        } catch (CustomException e) {
            HttpServletResponse response = (HttpServletResponse) resp;
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Type", "application/json;charset=utf-8");
            response.setContentType("application/json;charset=UTF-8");
            out = response.getWriter();
            out.write(JSON.toJSONString(new Response<>(e)));
        } finally {
            if (out != null) {
                out.close();
            }
        }
        return false;
    }
}
