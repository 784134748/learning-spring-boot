package com.yalonglee.learning.core.common;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Builder
@Setter
@Getter
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

    /**
     * 查询数据总量
     */
    private Long total;

    @SuppressWarnings("unchecked")
    public static Result success(HttpStatus httpStatus) {
        Result result = Result.builder()
                .status(true)
                .responseCode(httpStatus.value())
                .build();
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
            default:
                result.setMessage("");
                break;

        }
        return result;
    }

    @SuppressWarnings("unchecked")
    public static Result fail() {
        return Result.builder()
                .status(false)
                .build();
    }

}
