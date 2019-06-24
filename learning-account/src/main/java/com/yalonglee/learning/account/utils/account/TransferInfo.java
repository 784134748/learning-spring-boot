package com.yalonglee.learning.account.utils.account;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @author yalonglee
 */
@Data
@Builder
public class TransferInfo {

    @ApiModelProperty(value = "主键id", dataType = "Long")
    private Long id;

    @ApiModelProperty(value = "流水号", dataType = "String")
    private String serialNumber;

    @ApiModelProperty(value = "金额，单位：分", dataType = "Double")
    private Double amount;

    @ApiModelProperty(value = "账户操作类型 1充值 2转账 3提现", dataType = "Integer")
    private Integer accountOperationType;

    @ApiModelProperty(value = "转账类型 1转入 2转出", dataType = "Integer")
    private Integer transferType;

    @ApiModelProperty(value = "转账记录所属的账户地址", dataType = "String")
    private String accountAddr;

    @ApiModelProperty(value = "转出账户地址", dataType = "String")
    private String sourceAccountAddr;

    @ApiModelProperty(value = "转入账户地址", dataType = "String")
    private String targetAccountAddr;

}
