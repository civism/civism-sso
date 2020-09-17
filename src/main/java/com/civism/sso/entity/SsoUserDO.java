package com.civism.sso.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author : Civism
 * @projectName : civism-sso
 * @E-mail : 695234456@qq.com
 */
@Data
@TableName("sso_user")
public class SsoUserDO extends BaseDO {

    private static final long serialVersionUID = -898944646564104445L;

    /**
     * 账号密码
     */
    private String name;

    /**
     * 密码
     */
    private String password;
}
