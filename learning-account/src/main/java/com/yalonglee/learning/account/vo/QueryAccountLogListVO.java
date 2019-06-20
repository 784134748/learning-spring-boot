package com.yalonglee.learning.account.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.*;

/**
 * @author 
 */
@Data
public class QueryAccountLogListVO {

    @ApiModelProperty(value = "主键id 无符号 雪花算法", dataType = "Long")
    private Long id;

    @ApiModelProperty(value = "流水号;不同来源的金额，流水号前缀不一致，例如：资助=ZZ", dataType = "String")
    private String serialNumber;

    @ApiModelProperty(value = "资助id/提现id/奖励id（target_id）", dataType = "Long")
    private Long targetId;

    @ApiModelProperty(value = "转账来源 1创世货币 2资助 3提现 4奖励", dataType = "Integer")
    private Integer transactionFrom;

    @ApiModelProperty(value = "创世货币uuid", dataType = "String")
    private String currencyBaseUuid;

    @ApiModelProperty(value = "新生账户记录id（默认为0，提现操作不产生新生账户记录）", dataType = "Long")
    private Long accountRecordId;

    @ApiModelProperty(value = "新生账户记录变更次数（默认为0）", dataType = "Integer")
    private Integer accountRecordIndex;

    @ApiModelProperty(value = "资金来源账户记录id（默认为0，创世货币不来自任一账户）", dataType = "Long")
    private Long fromAccountRecordId;

    @ApiModelProperty(value = "资金来源账户记录变更次数", dataType = "Integer")
    private Integer fromAccountRecordIndex;

    @ApiModelProperty(value = "该变更记录所属的账户地址", dataType = "String")
    private String accountAddr;

    @ApiModelProperty(value = "账户记录变更后的可用金额，单位：分", dataType = "Double")
    private Double availableAmount;

    @ApiModelProperty(value = "账户记录变更后的冻结金额，单位：分", dataType = "Double")
    private Double frozenAmount;

    @ApiModelProperty(value = "创建时间", dataType = "java.time.LocalDateTime")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.time.LocalDateTime gmtCreate;

    @ApiModelProperty(value = "修改时间", dataType = "java.time.LocalDateTime")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.time.LocalDateTime gmtModified;


}
