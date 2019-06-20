package com.yalonglee.learning.account.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author yalonglee
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountDTO {

    @ApiModelProperty(value = "账户id", dataType = "Long")
    private Long id;

    @ApiModelProperty(value = "账户地址", dataType = "String")
    private String accountAddr;

    @ApiModelProperty(value = "账户总金额 = 账户可用金额 + 账户冻结金额 + 待提现金额，单位：分", dataType = "Double")
    private Double totalAmount;

    @ApiModelProperty(value = "账户可用金额，单位：分", dataType = "Double")
    private Double totalAvailableAmount;

    @ApiModelProperty(value = "账户冻结金额，单位：分", dataType = "Double")
    private Double totalFrozenAmount;

    @ApiModelProperty(value = "待提现金额，单位：分", dataType = "Double")
    private Double waitWithDrawCashesAmount;

    @ApiModelProperty(value = "时间锁", dataType = "java.time.LocalDateTime")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.time.LocalDateTime timestampLock;

    @ApiModelProperty(value = "数据加盐后的md5", dataType = "String")
    private String mdfive;

}
