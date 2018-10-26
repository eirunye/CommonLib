package com.eirunye.common.lib.mvp.baseimpl;

/**
 * Author Eirunye
 * Created by on 2018/10/11.
 * Describe
 */
public class BaseBean<T> {

    private String code;
    private String message;

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
