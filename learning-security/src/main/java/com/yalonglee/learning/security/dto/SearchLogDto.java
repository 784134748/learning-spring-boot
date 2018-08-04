package com.yalonglee.learning.security.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class SearchLogDto {

    /**
     * 日志类型
     */
    private Integer type;

    /**
     * 修改前数据
     */
    private String beforeSeg;

    /**
     * 修改后
     */
    private String afterSeg;

    /**
     * 操作人
     */
    private String operator;

    /**
     *
     * yyyy-MM-dd HH:mm:ss
     */
    private Date fromTime;

    /**
     *
     * yyyy-MM-dd HH:mm:ss
     */
    private Date toTime;
}
