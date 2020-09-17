package com.civism.sso.shiro;

import com.civism.sso.entity.LoginEntity;
import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @author : Civism
 * @projectName : civism-sso
 * @E-mail : 695234456@qq.com
 */
public class CustomUserPasswordToken extends UsernamePasswordToken {

    /**
     * 该对象中可以放如登陆方式 等
     */
    private LoginEntity loginEntity;

    public CustomUserPasswordToken(LoginEntity loginEntity) {
        super(loginEntity.getUserName(), loginEntity.getPassword());
        this.loginEntity = loginEntity;
    }

    public LoginEntity getLoginEntity() {
        return loginEntity;
    }
}
