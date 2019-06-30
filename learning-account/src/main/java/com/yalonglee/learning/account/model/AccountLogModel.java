package com.yalonglee.learning.account.model;

import com.yalonglee.learning.account.model.base.AccountBaseModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.*;

import java.io.Serializable;

/**
 * @author 
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "AccountLogModel", description = "账户变更记录")
public class AccountLogModel extends AccountBaseModel implements Serializable {

    public static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id 无符号 雪花算法", dataType = "Long", hidden = true)
    private Long id;

    @ApiModelProperty(value = "流水号;不同来源的金额，流水号前缀不一致，例如：资助=ZZ", dataType = "String")
    private String serialNumber;

    @ApiModelProperty(value = "账户操作类型 1充值 2转账 3提现", dataType = "Integer")
    private Integer accountOperationType;

    @ApiModelProperty(value = "新生账户记录id（默认为0，提现操作不产生新生账户记录）", dataType = "Long")
    private Long accountRecordId;

    @ApiModelProperty(value = "新生账户记录变更次数（默认为0）", dataType = "Integer")
    private Integer accountRecordIndex;

    @ApiModelProperty(value = "资金来源账户记录id（默认为0，创世货币不来自任一账户）", dataType = "Long")
    private Long sourceAccountRecordId;

    @ApiModelProperty(value = "资金来源账户记录变更次数", dataType = "Integer")
    private Integer sourceAccountRecordIndex;

    @ApiModelProperty(value = "该变更记录所属的账户地址", dataType = "String")
    private String accountAddr;

    @ApiModelProperty(value = "账户记录变更后的可用金额，单位：分", dataType = "Double")
    private Double accountRecordAvailableAmount;

    @ApiModelProperty(value = "账户记录变更后的冻结金额，单位：分", dataType = "Double")
    private Double accountRecordFrozenAmount;

    @ApiModelProperty(value = "创建时间", dataType = "java.time.LocalDateTime", hidden = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.time.LocalDateTime gmtCreate;

    @ApiModelProperty(value = "修改时间", dataType = "java.time.LocalDateTime", hidden = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.time.LocalDateTime gmtModified;


}
