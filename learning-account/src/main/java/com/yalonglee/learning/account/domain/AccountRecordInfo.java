package com.yalonglee.learning.account.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @author
 */
@Data
@Builder
public class AccountRecordInfo {

    @ApiModelProperty(value = "主键id 无符号 雪花算法", dataType = "Long")
    private Long id;

    @ApiModelProperty(value = "流水号", dataType = "String")
    private String serialNumber;

    @ApiModelProperty(value = "创世货币uuid", dataType = "String")
    private String currencyBaseUuid;

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
