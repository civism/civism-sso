package com.civism.sso.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
/**
 * @author : Civism
 * @projectName : civism-sso
 * @E-mail : 695234456@qq.com
 */
@Data
public class LoginEntity implements Serializable {

    private static final long serialVersionUID = 2449244918272018993L;

    /**
     * 用户名称
     */
    @ApiModelProperty("用户名称")
    private String userName;

    /**
     * 用户密码
     */
    @ApiModelProperty("用户密码")
    private String password;
}
