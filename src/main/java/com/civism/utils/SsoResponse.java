package com.civism.utils;

import com.alibaba.fastjson.JSON;
import com.civism.error.ErrorType;


import java.io.Serializable;

/**
 * @author star
 * @date 2018/5/10 下午3:38
 */
public class SsoResponse<T> implements Serializable {

    private static final long serialVersionUID = 2214047827446751L;

    private boolean success = true;

    private String errorCode;

    private String errorMsg;


    private T data;

    public SsoResponse() {
    }

    public SsoResponse(T data) {
        this.data = data;
    }

    public SsoResponse(ErrorType errorType) {
        this.errorCode = errorType.getErrorCode();
        this.errorMsg = errorType.getErrorMsg();
        this.success = false;
    }

    public SsoResponse(ErrorType errorType, String errorMsg) {
        this.errorCode = errorType.getErrorCode();
        this.errorMsg = errorMsg;
        this.success = false;
    }

    public T getData() {
        return data;
    }


    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
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


    public String getJSONP(String callback) {
        if (success) {
            return callback + "({\"success\":true," + "data:" + JSON.toJSONString(data) + "})";
        } else {
            return callback + "({\"" + "errorCode\":\"" + this.getErrorCode() + "\"" + ",\"success\":false" + ",\"errorMsg\":\"" + this.getErrorMsg() + "\"})";
        }
    }
}
