package com.civism.error;

import org.apache.shiro.authc.AuthenticationException;

/**
 * @author star
 * @date 2019-03-21 16:36
 */
public class CustomAccountException extends AuthenticationException {

    private ErrorType errorType;

    public CustomAccountException(ErrorType errorType) {
        this.errorType = errorType;
    }

    public ErrorType getErrorType() {
        return errorType;
    }
}
