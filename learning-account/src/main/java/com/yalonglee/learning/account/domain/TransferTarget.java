package com.yalonglee.learning.account.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>《转账BO》
 * <p><功能详细描述>
 * <p>
 *
 * @author listener
 * @version [V1.0, 2019/1/8]
 * @see [相关类/方法]
 */
@Data
@Builder
public class TransferTarget {

    @ApiModelProperty(value = "转账地址", dataType = "String")
    private String accountAddr;

    @ApiModelProperty(value = "转账金额", dataType = "String")
    private Double totalTransferAmount;

}
