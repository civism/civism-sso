package com.civism.sso.shiro;

import com.civism.sso.constant.CivismConstant;
import com.civism.sso.entity.SsoUserDO;
import com.civism.sso.entity.UserInfo;
import com.civism.sso.enums.CustomExceptionEnum;
import com.civism.sso.exception.CustomException;
import com.civism.sso.redis.RedisClient;
import com.civism.sso.service.SsoUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author : Civism
 * @projectName : civism-sso
 * @E-mail : 695234456@qq.com
 */
public class CivismShiroUpmsRealm extends AuthorizingRealm {


    @Resource
    private SsoUserService ssoUserService;

    @Resource
    private RedisClient redisClient;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        UserInfo userInfo = (UserInfo) principalCollection.getPrimaryPrincipal();

        //根据用户查询有数据接口权限
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> urlSet = new HashSet<>();
        urlSet.add("/sso/auth");
        info.setStringPermissions(urlSet);

//        info.setRoles(Sets.newHashSet("admin"));
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {


        Object principal = authenticationToken.getPrincipal();

        SsoUserDO ssoUserDO = ssoUserService.queryUserByUserName((String) principal);
        if (ssoUserDO == null) {
            throw new CustomException(CustomExceptionEnum.ACCOUNT_PASSWORD_ERROR);
        }

        Serializable token = SecurityUtils.getSubject().getSession().getId();

        UserInfo userInfo = UserInfo.builder().name(ssoUserDO.getName())
                .token((String) token).id(ssoUserDO.getId()).build();
        redisClient.set((String) token, userInfo, CivismConstant.EXPIRE);
        return new SimpleAuthenticationInfo(userInfo, ssoUserDO.getPassword(),
                getName());
    }
}
