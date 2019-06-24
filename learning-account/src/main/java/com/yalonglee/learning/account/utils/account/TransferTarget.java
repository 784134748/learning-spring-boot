package com.yalonglee.learning.account.utils.account;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @author yalonglee
 */
@Data
@Builder
public class TransferTarget {

    @ApiModelProperty(value = "转账地址", dataType = "String")
    private String accountAddr;

    @ApiModelProperty(value = "转账金额", dataType = "Double")
    private Double transferAmount;

}
