package com.yalonglee.learning.account.utils.account;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;


/**
 * @author yalonglee
 */
@Data
@Builder
public class AccountLogInfo {

    @ApiModelProperty(value = "主键id 无符号 雪花算法", dataType = "Long")
    private Long id;

    @ApiModelProperty(value = "流水号", dataType = "String")
    private String serialNumber;

    @ApiModelProperty(value = "新生账户记录id（默认为0，提现操作不产生新生账户记录）", dataType = "Long")
    private Long accountRecordId;

    @ApiModelProperty(value = "账户操作类型 1充值 2转账 3提现", dataType = "Integer")
    private Integer accountOperationType;

    @ApiModelProperty(value = "新生账户记录变更次数（默认为0）", dataType = "Integer")
    private Integer accountRecordIndex;

    @ApiModelProperty(value = "资金来源账户记录id（默认为0，充值的金额不来自任一账户）", dataType = "Long")
    private Long fromAccountRecordId;

    @ApiModelProperty(value = "资金来源账户记录变更次数", dataType = "Integer")
    private Integer fromAccountRecordIndex;

    @ApiModelProperty(value = "该变更记录所属的账户地址", dataType = "String")
    private String accountAddr;

    @ApiModelProperty(value = "账户记录变更后的可用金额，单位：分", dataType = "Double")
    private Double availableAmount;

    @ApiModelProperty(value = "账户记录变更后的冻结金额，单位：分", dataType = "Double")
    private Double frozenAmount;

}
