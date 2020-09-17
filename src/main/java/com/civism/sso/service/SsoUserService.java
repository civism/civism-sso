package com.civism.sso.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.civism.sso.entity.SsoUserDO;

/**
 * @author : Civism
 * @projectName : civism-sso
 * @E-mail : 695234456@qq.com
 */
public interface SsoUserService extends IService<SsoUserDO> {

    /**
     * 通过用户名和密码查找用户
     * @param userName
     * @return
     */
    SsoUserDO queryUserByUserName(String userName);
}
