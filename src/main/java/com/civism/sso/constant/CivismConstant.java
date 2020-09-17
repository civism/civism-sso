package com.civism.sso.constant;

import java.io.Serializable;

/**
 * @author : Civism
 * @projectName : civism-sso
 * @E-mail : 695234456@qq.com
 */
public class CivismConstant implements Serializable {

    private static final long serialVersionUID = 1751077975755493749L;

    /**
     * redis中的token
     */
    public static final String TOKEN = "token";

    /**
     * 登陆有效期
     */
    public static final Long EXPIRE = 30 * 60 * 1L;
}
