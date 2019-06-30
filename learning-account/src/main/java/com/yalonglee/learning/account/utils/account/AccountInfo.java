package com.yalonglee.learning.account.utils.account;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @author yalonglee
 */
@Data
@Builder
public class AccountInfo {

    @ApiModelProperty(value = "账户id", dataType = "Long")
    private Long id;

    @ApiModelProperty(value = "账户地址", dataType = "String")
    private String accountAddr;

    @ApiModelProperty(value = "账户可用总金额(包含了待提现金额)，单位：分", dataType = "Double")
    private Double accountTotalAvailableAmount;

    @ApiModelProperty(value = "账户冻结金额，单位：分", dataType = "Double")
    private Double accountTotalFrozenAmount;

    @ApiModelProperty(value = "账户待提现金额，单位：分", dataType = "Double")
    private Double accountWaitWithdrawCashesAmount;

    @ApiModelProperty(value = "该账户上一次获取行锁的时间戳", dataType = "Long")
    private Long accountTimestampLock;

    @ApiModelProperty(value = "数据库当前时间戳(交易开始的时间)", dataType = "Long")
    private Long accountCurrentTimestamp;

    /**
     * 金额的处理
     *
     * @return
     */

    @ApiModelProperty(value = "账户总金额(账户可用金额 + 账户冻结金额)，单位：分", dataType = "Double")
    public Double getAccountTotalAmount() {
        return accountTotalAvailableAmount + accountTotalFrozenAmount;
    }

    @ApiModelProperty(value = "账户可展示的可用金额(账户可用总金额 - 待提现金额)，单位：分", dataType = "Double")
    public Double getAccountAvailableShowAmount() {
        return accountTotalAvailableAmount - accountWaitWithdrawCashesAmount;
    }

    @ApiModelProperty(value = "账户可转账的金额(账户可用总金额 - 待提现金额)，单位：分", dataType = "Double")
    public Double getAccountAvailableTransferAmount() {
        return accountTotalAvailableAmount - accountWaitWithdrawCashesAmount;
    }

    @ApiModelProperty(value = "账户可转账的金额(账户可用总金额 - 待提现金额)，单位：分", dataType = "Double")
    public Double getAccountAvailableWithdrawCashesAmount() {
        return accountTotalAvailableAmount - accountWaitWithdrawCashesAmount;
    }

}
