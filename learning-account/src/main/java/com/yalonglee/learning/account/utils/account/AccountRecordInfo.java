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

    @ApiModelProperty(value = "账户记录操作类型 1充值 2转账 3提现", dataType = "Integer")
    private Integer accountRecordOperationType;

    @ApiModelProperty(value = "账户地址", dataType = "String")
    private String accountAddr;

    @ApiModelProperty(value = "单条账户记录可用金额，单位：分", dataType = "Double")
    private Double accountRecordAvailableAmount;

    @ApiModelProperty(value = "单条账户记录冻结金额，单位：分", dataType = "Double")
    private Double accountRecordFrozenAmount;

    @ApiModelProperty(value = "账户记录金额变更次数", dataType = "Integer")
    private Integer index;

    @ApiModelProperty(value = "账户记录资金的来源账户地址", dataType = "String")
    private String sourceAccountAddr;

}
