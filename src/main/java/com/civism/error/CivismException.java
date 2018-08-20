package com.civism.error;


/**
 * @author star
 * @date 2018/4/27 下午3:38
 */
public class CivismException extends Exception {

    private ErrorType errorCode;

    public CivismException(ErrorType errorCode) {
        this.errorCode = errorCode;
    }

    public ErrorType getErrorCode() {
        return errorCode;
    }

    @Override
    public String toString() {
        return "RuhnnException{" +
                "code=" + errorCode.getErrorCode() + ",errorMsg=" + errorCode.getErrorMsg() +
                '}';
    }
}
