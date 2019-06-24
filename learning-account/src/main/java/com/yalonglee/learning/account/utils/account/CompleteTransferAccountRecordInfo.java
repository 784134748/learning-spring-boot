package com.yalonglee.learning.account.utils.account;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @author yalonglee
 */
@Data
@Builder
public class CompleteTransferAccountRecordInfo {

    @ApiModelProperty(value = "账户记录id", dataType = "Long")
    private Long accountRecordId;

    @ApiModelProperty(value = "行号（倒序排序)", dataType = "Integer")
    private Integer accountRecordRowNo;

    @ApiModelProperty(value = "累加账户记录可用的金额(按行号正序递增)", dataType = "Double")
    private Double sumAccountRecordAvailableAmount;

    @ApiModelProperty(value = "账户地址", dataType = "String")
    private String accountAddr;

    @ApiModelProperty(value = "账户记录变更次数", dataType = "Integer")
    private Integer index;

    @ApiModelProperty(value = "账户记录可用的金额", dataType = "Double")
    private Double accountRecordAvailableAmount;

    @ApiModelProperty(value = "账户记录冻结的金额", dataType = "Double")
    private Double accountRecordFrozenAmount;

}
