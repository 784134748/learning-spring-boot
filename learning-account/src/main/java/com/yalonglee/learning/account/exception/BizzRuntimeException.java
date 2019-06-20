package com.yalonglee.learning.account.exception;

/**
 * 业务异常类
 *
 * @author shaoshuai
 * @since 2019-01-05 12:57
 */
public final class BizzRuntimeException extends RuntimeException {

    /**
     * 异常编码
     */
    private Integer code;

    public BizzRuntimeException(String message) {
        super(message);
    }

    public BizzRuntimeException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public BizzRuntimeException(String message, Integer code, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

}

