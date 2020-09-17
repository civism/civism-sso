package com.civism.sso.filter;

import com.civism.sso.constant.CivismConstant;
import com.civism.sso.enums.CustomExceptionEnum;
import com.civism.sso.exception.CustomException;
import com.civism.sso.redis.RedisClient;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * @author : Civism
 * @projectName : civism-sso
 * @E-mail : 695234456@qq.com
 */
public class CheckLoginFilter extends BaseCheckFilter {

    @Resource
    private RedisClient redisClient;

    @Override
    protected boolean isAccessAllowed(ServletRequest req, ServletResponse resp, Object mappedValue) {

        HttpServletRequest request = (HttpServletRequest) req;
        String token = request.getHeader(CivismConstant.TOKEN);
        if (StringUtils.isEmpty(token)) {
            return false;
        }
        Object o = redisClient.get(token);
        if (o == null) {
            return false;
        }

        return true;


    }

    @Override
    protected void checkFail() throws CustomException {
        throw new CustomException(CustomExceptionEnum.NO_LOGIN);
    }
}
