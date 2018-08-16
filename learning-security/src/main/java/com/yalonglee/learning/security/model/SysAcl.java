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
@ApiModel(value = "SysAcl")
public class SysAcl implements Serializable {

  public static final long serialVersionUID = 1L;

 /**
  * 权限id【bigint(11) unsigned】
  */
  @ApiModelProperty(value = "权限id", dataType = "bigint(11) unsigned")
  private Long id;

 /**
  * 权限码【varchar(20)】
  */
  @ApiModelProperty(value = "权限码", dataType = "varchar(20)")
  private String code;

 /**
  * 权限名称【varchar(20)】
  */
  @ApiModelProperty(value = "权限名称", dataType = "varchar(20)")
  private String name;

 /**
  * 权限所在的权限模块id【int(11)】
  */
  @ApiModelProperty(value = "权限所在的权限模块id", dataType = "int(11)")
  private Integer aclModuleId;

 /**
  * 请求的url, 可以填正则表达式【varchar(100)】
  */
  @ApiModelProperty(value = "请求的url, 可以填正则表达式", dataType = "varchar(100)")
  private String url;

 /**
  * 类型，1：菜单，2：按钮，3：其他【int(11)】
  */
  @ApiModelProperty(value = "类型，1：菜单，2：按钮，3：其他", dataType = "int(11)")
  private Integer type;

 /**
  * 状态，1：正常，0：冻结【int(11)】
  */
  @ApiModelProperty(value = "状态，1：正常，0：冻结", dataType = "int(11)")
  private Integer status;

 /**
  * 权限在当前模块下的顺序，由小到大【int(11)】
  */
  @ApiModelProperty(value = "权限在当前模块下的顺序，由小到大", dataType = "int(11)")
  private Integer seq;

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
  * 最后一次更新时间【datetime】
  */
  @ApiModelProperty(value = "最后一次更新时间", dataType = "datetime")
  private java.time.LocalDateTime operateTime;

 /**
  * 最后一个更新者的ip地址【varchar(20)】
  */
  @ApiModelProperty(value = "最后一个更新者的ip地址", dataType = "varchar(20)")
  private String operateIp;


}
