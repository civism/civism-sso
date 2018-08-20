package com.civism.service;

import com.civism.error.CivismException;
import com.civism.vo.LoginEntity;
import com.civism.vo.UserInfo;

/**
 * @author star
 * @date 2018/8/17 下午4:26
 */
public interface UserService {

    UserInfo login(LoginEntity loginEntity) throws CivismException;

    boolean loginOut(String token);
}
