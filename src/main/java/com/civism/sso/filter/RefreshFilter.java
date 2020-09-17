package com.civism.sso.filter;

import com.civism.sso.redis.RedisClient;
import org.apache.shiro.web.filter.authc.UserFilter;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import static com.civism.sso.constant.CivismConstant.EXPIRE;
import static com.civism.sso.constant.CivismConstant.TOKEN;

/**
 * @author : Civism
 * @projectName : civism-sso
 * @E-mail : 695234456@qq.com
 */
public class RefreshFilter extends UserFilter {

    @Resource
    private RedisClient redisClient;

    @Override
    protected boolean isAccessAllowed(ServletRequest req, ServletResponse response, Object mappedValue) {
        HttpServletRequest request = (HttpServletRequest) req;
        String token = request.getHeader(TOKEN);
        redisClient.expire(token, EXPIRE);
        return super.isAccessAllowed(request, response, mappedValue);
    }
}
