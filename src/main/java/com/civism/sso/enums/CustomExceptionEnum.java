package com.civism.sso.enums;

/**
 * @author : Civism
 * @version 1.0
 * @projectName：civism-sso
 * @E-mail:695234456@qq.com
 */
public enum CustomExceptionEnum {
    ACCOUNT_PASSWORD_ERROR("U001", "用户名或密码错误"),
    NO_LOGIN("U002", "登陆过期"),
    NO_POWER("U003", "没有权限"),
    SYSTEM_ERROR("Z999", "系统异常");

    CustomExceptionEnum(String code, String errorMsg) {
        this.code = code;
        this.errorMsg = errorMsg;
    }

    private String code;

    private String errorMsg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
