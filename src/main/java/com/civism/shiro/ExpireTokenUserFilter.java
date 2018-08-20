package com.civism.shiro;


import com.civism.constants.SsoConstants;
import com.civism.dao.RedisClient;
import com.civism.utils.SerializeUtil;
import com.civism.vo.UserInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

/**
 * @author star
 * @date 2018/6/28 上午10:58
 */
public class ExpireTokenUserFilter extends UserFilter {

    private static final Logger logger = LoggerFactory.getLogger(ExpireTokenUserFilter.class);

    @Resource
    private RedisClient redisClient;

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        try {
            Serializable id = SecurityUtils.getSubject().getSession(false).getId();
            byte[] value = redisClient.get((String) id);
            if (value != null) {
                UserInfo userInfo = SerializeUtil.deserialize(value, UserInfo.class);
                redisClient.expire((String) id, userInfo.getExpireTime() == null ? SsoConstants.DEFAULT_LOGIN_EXPIRE : userInfo.getExpireTime());
            }
        } catch (Exception e) {
            logger.error("error ", e);
        }
        return true;
    }
}
