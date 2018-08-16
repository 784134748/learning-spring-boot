package com.yalonglee.learning.security.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.time.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "SysRole")
public class SysRole implements Serializable {

  public static final long serialVersionUID = 1L;

 /**
  * 角色id【bigint(11) unsigned】
  */
  @ApiModelProperty(value = "角色id", dataType = "bigint(11) unsigned")
  private Long id;

 /**
  * null【varchar(20)】
  */
  @ApiModelProperty(value = "null", dataType = "varchar(20)")
  private String name;

 /**
  * 角色的类型，1：管理员角色，2：其他【int(11)】
  */
  @ApiModelProperty(value = "角色的类型，1：管理员角色，2：其他", dataType = "int(11)")
  private Integer type;

 /**
  * 状态，1：可用，0：冻结【int(11)】
  */
  @ApiModelProperty(value = "状态，1：可用，0：冻结", dataType = "int(11)")
  private Integer status;

 /**
  * 备注【varchar(200)】
  */
  @ApiModelProperty(value = "备注", dataType = "varchar(200)")
  private String remark;

 /**
  * 操作者【varchar(20)】
  */
  @ApiModelProperty(value = "操作者", dataType = "varchar(20)")
  private String operator;

 /**
  * 最后一次更新的时间【datetime】
  */
  @ApiModelProperty(value = "最后一次更新的时间", dataType = "datetime")
  private java.time.LocalDateTime operateTime;

 /**
  * 最后一次更新者的ip地址【varchar(20)】
  */
  @ApiModelProperty(value = "最后一次更新者的ip地址", dataType = "varchar(20)")
  private String operateIp;


}
