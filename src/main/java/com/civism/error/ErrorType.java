package com.civism.error;

/**
 * @author star
 * @date 2018/5/10 下午3:44
 */
public enum ErrorType {
    INVALID_ARGUMENT("A10", "无效参数"),
    USER_NO_EXIST("A11", "用户不存在"),
    PASSWORD_ERROR("A12", "密码错误"),
    COOKIE_ERROR("A13", "无效的cookie"),
    USER_WX_ACCESSTOKEN_ERROR("A14", "微信授权失败"),
    ERROR_PARAMETER("A16","参数错误"),
    USER_WX_SILENCE_ACCESSTOKEN_ERROR("A15", "微信静默授权失败"),
    NO_AUTHORITY("B10", "没有权限"),
    TOKEN_INVALID("C10", "登陆过期"),
    SYSTEM_ERROR("D10", "系统异常"),
    PATH_ERROR("E10", "无效访问路径"),
    NULL_ERROR("E11","无效数据");


    private String errorCode;

    private String errorMsg;

    ErrorType(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }


    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
