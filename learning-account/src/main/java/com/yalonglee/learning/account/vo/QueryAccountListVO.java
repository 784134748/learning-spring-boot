package com.yalonglee.learning.account.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.*;

/**
 * @author 
 */
@Data
public class QueryAccountListVO {

    @ApiModelProperty(value = "主键id 无符号 自增长 步长为1", dataType = "Long")
    private Long id;

    @ApiModelProperty(value = "账户地址", dataType = "String")
    private String accountAddr;

    @ApiModelProperty(value = "时间锁", dataType = "Integer")
    private Integer timestampLock;

    @ApiModelProperty(value = "待提现金额", dataType = "Double")
    private Double waitWithdrawCashesAmount;

    @ApiModelProperty(value = "创建时间", dataType = "java.time.LocalDateTime")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.time.LocalDateTime gmtCreate;

    @ApiModelProperty(value = "修改时间", dataType = "java.time.LocalDateTime")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.time.LocalDateTime gmtModified;


}
