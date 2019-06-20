package com.yalonglee.learning.account.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.*;

/**
 * @author 
 */
@Data
public class QueryAccountRecordListVO {

    @ApiModelProperty(value = "主键id 无符号 雪花算法", dataType = "Long")
    private Long id;

    @ApiModelProperty(value = "流水号;不同来源的金额，流水号前缀不一致，例如：资助=ZZ", dataType = "String")
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

    @ApiModelProperty(value = "创建时间", dataType = "java.time.LocalDateTime")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.time.LocalDateTime gmtCreate;

    @ApiModelProperty(value = "修改时间", dataType = "java.time.LocalDateTime")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.time.LocalDateTime gmtModified;


}
