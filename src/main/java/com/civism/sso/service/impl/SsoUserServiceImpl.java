package com.civism.sso.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.civism.sso.dao.SsoUserDao;
import com.civism.sso.entity.SsoUserDO;
import com.civism.sso.service.SsoUserService;
import org.springframework.stereotype.Service;

/**
 * @author : Civism
 * @projectName : civism-sso
 * @E-mail : 695234456@qq.com
 */
@Service
public class SsoUserServiceImpl extends ServiceImpl<SsoUserDao, SsoUserDO> implements SsoUserService {

    @Override
    public SsoUserDO queryUserByUserName(String userName, String password) {
        return this.getOne(Wrappers.<SsoUserDO>lambdaQuery().eq(SsoUserDO::getName, userName).eq(SsoUserDO::getPassword, password));
    }
}
