package com.civism.shiro;

import com.civism.vo.LoginEntity;
import com.civism.vo.LoginWayEnum;
import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @author star
 * @date 2018/8/17 下午4:15
 */
public class SsoUserNameToken extends UsernamePasswordToken {

    private LoginEntity loginEntity;

    public SsoUserNameToken(LoginEntity loginEntity) {
        this.loginEntity = loginEntity;
    }

    public LoginEntity getLoginEntity() {
        return loginEntity;
    }

    public void setLoginEntity(LoginEntity loginEntity) {
        this.loginEntity = loginEntity;
    }

    @Override
    public Object getCredentials() {
        if (loginEntity.getWay().intValue() == LoginWayEnum.ACCOUNT_LOGIN.getWay().intValue()) {
            return loginEntity.getPassword();
        }
        return getPassword();
    }
}
