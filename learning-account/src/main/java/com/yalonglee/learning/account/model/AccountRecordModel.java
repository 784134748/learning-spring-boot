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
@ApiModel(value = "AccountRecordModel", description = "账户记录")
public class AccountRecordModel extends AccountBaseModel implements Serializable {

    public static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id 无符号 雪花算法", dataType = "Long", hidden = true)
    private Long id;

    @ApiModelProperty(value = "流水号", dataType = "String")
    private String serialNumber;

    @ApiModelProperty(value = "账户地址", dataType = "String")
    private String accountAddr;

    @ApiModelProperty(value = "可用金额，单位：分", dataType = "Double")
    private Double availableAmount;

    @ApiModelProperty(value = "冻结金额，单位：分", dataType = "Double")
    private Double frozenAmount;

    @ApiModelProperty(value = "账户金额变更次数", dataType = "Integer")
    private Integer index;

    @ApiModelProperty(value = "资金来源于该账户地址", dataType = "String")
    private String sourceAccountAddr;

    @ApiModelProperty(value = "生效时间", dataType = "java.time.LocalDateTime")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.time.LocalDateTime gmtEffect;

    @ApiModelProperty(value = "创建时间", dataType = "java.time.LocalDateTime", hidden = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.time.LocalDateTime gmtCreate;

    @ApiModelProperty(value = "修改时间", dataType = "java.time.LocalDateTime", hidden = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.time.LocalDateTime gmtModified;


}
