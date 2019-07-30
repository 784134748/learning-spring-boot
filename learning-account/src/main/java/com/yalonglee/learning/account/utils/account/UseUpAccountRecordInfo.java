package com.yalonglee.learning.account.utils.account;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @author yalonglee
 */
@Data
@Builder
public class UseUpAccountRecordInfo {

    @ApiModelProperty(value = "账户记录id", dataType = "Long")
    private Long accountRecordId;

    @ApiModelProperty(value = "账户记录变更次数", dataType = "Integer")
    private Integer index;
}
