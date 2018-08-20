package com.civism.service.impl;

import com.civism.dao.RedisClient;
import com.civism.error.CivismException;
import com.civism.error.ErrorType;
import com.civism.service.UserService;
import com.civism.vo.LoginEntity;
import com.civism.vo.UserInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author star
 * @date 2018/8/17 下午4:26
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private RedisClient redisClient;

    @Override
    public UserInfo login(LoginEntity loginEntity) throws CivismException {
        //处理登录逻辑 放用户信息到userInfo 里面
        if ("admin".equals(loginEntity.getUserName()) && "123456".equals(loginEntity.getPassword())) {
            UserInfo userInfo = new UserInfo();
            userInfo.setEmail("695234456@qq.com");
            userInfo.setId(1);
            userInfo.setHeadImgUrl("http://1234.jpg");
            return userInfo;
        }
        //具体异常可以自己改写
        throw new CivismException(ErrorType.ERROR_PARAMETER);
    }


    @Override
    public boolean loginOut(String token) {
        redisClient.remove(token);
        return true;
    }
}
