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

    @ApiModelProperty(value = "账户总金额 = 账户可用金额 + 账户冻结金额，单位：分", dataType = "Double")
    private Double totalAmount;

    @ApiModelProperty(value = "向用户展示的金额(账户可用金额 - 待提现金额)，单位：分", dataType = "Double")
    private Double Amount;

    @ApiModelProperty(value = "账户可用金额(包含待提现金额)，单位：分", dataType = "Double")
    private Double totalAvailableAmount;

    @ApiModelProperty(value = "账户冻结金额，单位：分", dataType = "Double")
    private Double totalFrozenAmount;

    @ApiModelProperty(value = "待提现金额，单位：分", dataType = "Double")
    private Double waitWithDrawCashesAmount;

    @ApiModelProperty(value = "该记录上一次获取行锁的时间戳", dataType = "Long")
    private Long timestampLock;

    @ApiModelProperty(value = "数据库当前时间戳", dataType = "Long")
    private Long currentTimestamp;

    public Double getTotalAmount() {
        return totalAvailableAmount + totalFrozenAmount;
    }

    public Double getAmount() {
        return totalAvailableAmount - waitWithDrawCashesAmount;
    }
}
