package com.civism.sso.exception;

import com.civism.sso.enums.CustomExceptionEnum;
import org.apache.shiro.authc.AuthenticationException;

/**
 * @author : Civism
 * @projectName : civism-sso
 * @E-mail : 695234456@qq.com
 */
public class CustomException extends AuthenticationException {

    private String errorCode;
    private String errorMsg;

    public CustomException(CustomExceptionEnum exceptionEnum) {
        this.errorCode = exceptionEnum.getCode();
        this.errorMsg = exceptionEnum.getErrorMsg();
    }


    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

}
