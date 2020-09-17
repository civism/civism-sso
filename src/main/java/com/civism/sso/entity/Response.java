package com.civism.sso.entity;

import com.civism.sso.exception.CustomException;
import java.io.Serializable;

/**
 * @author : Civism
 * @projectName : civism-sso
 * @E-mail : 695234456@qq.com
 */
public class Response<T> implements Serializable {

    private static final long serialVersionUID = 8817463078594954274L;

    private boolean success;

    private T data;

    private String errorCode;

    private String errorMsg;


    public Response(T data) {
        if (data instanceof CustomException) {
            CustomException customException = (CustomException) data;
            this.errorCode = customException.getErrorCode();
            this.errorMsg = customException.getErrorMsg();
        } else {
            this.data = data;
            this.success = true;
        }
    }


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
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
