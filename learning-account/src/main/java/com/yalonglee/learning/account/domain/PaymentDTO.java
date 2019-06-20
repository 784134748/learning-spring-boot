package com.yalonglee.learning.account.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentDTO {

    @ApiModelProperty(value = "行号（倒序排序", dataType = "Integer")
    private Integer rowNo;

    @ApiModelProperty(value = "累加可支付金额", dataType = "Double")
    private Double sum;

    @ApiModelProperty(value = "账户记录来源的创世货币uuid", dataType = "String")
    private String currencyBaseUuid;

    @ApiModelProperty(value = "账户记录id", dataType = "Long")
    private Long accountRecordId;

    @ApiModelProperty(value = "账户地址", dataType = "String")
    private String accountAddr;

    @ApiModelProperty(value = "账户记录变更次数", dataType = "Integer")
    private Integer index;

    @ApiModelProperty(value = "账户记录可用金额", dataType = "Double")
    private Double availableAmount;

    @ApiModelProperty(value = "冻结的金额", dataType = "Double")
    private Double frozenAmount;

}
