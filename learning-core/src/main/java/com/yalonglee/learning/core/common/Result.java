package com.yalonglee.learning.core.common;

import org.springframework.http.HttpStatus;

public class Result<T> {

    /**
     * 请求结果状态
     */
    private boolean status;

    /**
     * 接口请求消息
     */
    private String message;

    /**
     * 接口返回编码
     */
    private int responseCode;

    /**
     * 请求业务结果对象
     */
    private T entry;

    @SuppressWarnings("unchecked")
    public static Result success(HttpStatus httpStatus) {
        Result result = new Result();
        result.responseCode = httpStatus.value();
        result.setStatus(true);
        switch (httpStatus) {
            case OK:
                result.setMessage("操作成功!");
                break;
            case CREATED:
                result.setMessage("创建成功!");
                break;
            case ACCEPTED:
                result.setMessage("异步操作!");
                break;
            case NON_AUTHORITATIVE_INFORMATION:
                result.setMessage("无权限访问该资源!");
                break;
            case RESET_CONTENT:
                result.setMessage("更新成功!");
                break;
            case NO_CONTENT:
                result.setMessage("删除成功!");
                break;
            case NOT_FOUND:
                result.setMessage("未发现指定资源文件!");
                break;
            case BAD_REQUEST:
                result.setMessage("参数异常!");
                break;

        }
        return result;
    }

    @SuppressWarnings("unchecked")
    public static Result fail() {
        Result result = new Result();
        result.setStatus(false);
        return result;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public T getEntry() {
        return entry;
    }

    public void setEntry(T entry) {
        this.entry = entry;
    }
}
