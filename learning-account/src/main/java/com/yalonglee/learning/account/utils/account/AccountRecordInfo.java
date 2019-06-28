package com.yalonglee.learning.account.utils.account;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;


/**
 * @author yalonglee
 */
@Data
@Builder
public class AccountRecordInfo {

    @ApiModelProperty(value = "主键id 无符号 雪花算法", dataType = "Long")
    private Long id;

    @ApiModelProperty(value = "流水号", dataType = "String")
    private String serialNumber;

    @ApiModelProperty(value = "账户操作类型 1充值 2转账 3提现", dataType = "Integer")
    private Integer accountOperationType;

    @ApiModelProperty(value = "账户地址", dataType = "String")
    private String accountAddr;

    @ApiModelProperty(value = "可用金额，单位：分", dataType = "Double")
    private Double availableAmount;

    @ApiModelProperty(value = "冻结金额，单位：分", dataType = "Double")
    private Double frozenAmount;

    @ApiModelProperty(value = "账户金额变更次数", dataType = "Integer")
    private Integer index;

    @ApiModelProperty(value = "资金来源于该账户地址", dataType = "String")
    private String fromAccountAddr;

}