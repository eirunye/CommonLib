package com.eirunye.common.lib.net;

/**
 * Author Eirunye
 * Created by on 2018/10/11.
 * Describe
 */
public class ApiException extends RuntimeException {

    private int code;


    public ApiException(Throwable throwable, int code) {
        super(throwable);
        this.code = code;
    }

    public ApiException(String message) {
        super(new Throwable(message));

    }
}
