package com.yalonglee.learning.account.model;

import com.yalonglee.learning.account.model.base.AccountBaseModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.*;

import java.io.Serializable;

/**
 * @author 
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "AccountModel", description = "账户")
public class AccountModel extends AccountBaseModel implements Serializable {

    public static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id 无符号 自增长 步长为1", dataType = "Long", hidden = true)
    private Long id;

    @ApiModelProperty(value = "账户地址", dataType = "String")
    private String accountAddr;

    @ApiModelProperty(value = "时间锁", dataType = "Integer")
    private Integer timestampLock;

    @ApiModelProperty(value = "待提现金额", dataType = "Double")
    private Double waitWithdrawCashesAmount;

    @ApiModelProperty(value = "创建时间", dataType = "java.time.LocalDateTime", hidden = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.time.LocalDateTime gmtCreate;

    @ApiModelProperty(value = "修改时间", dataType = "java.time.LocalDateTime", hidden = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.time.LocalDateTime gmtModified;


}
