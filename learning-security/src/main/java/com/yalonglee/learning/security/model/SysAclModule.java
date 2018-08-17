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
@ApiModel(value = "SysAclModule")
public class SysAclModule implements Serializable {

  public static final long serialVersionUID = 1L;

 /**
  * 权限模块id【bigint(11) unsigned】
  */
  @ApiModelProperty(value = "权限模块id", dataType = "bigint(11) unsigned")
  private Long id;

 /**
  * 权限模块名称【varchar(20)】
  */
  @ApiModelProperty(value = "权限模块名称", dataType = "varchar(20)")
  private String name;

 /**
  * 上级权限模块id【bigint(11)】
  */
  @ApiModelProperty(value = "上级权限模块id", dataType = "bigint(11)")
  private Long parentId;

 /**
  * 权限模块层级【varchar(200)】
  */
  @ApiModelProperty(value = "权限模块层级", dataType = "varchar(200)")
  private String level;

 /**
  * 权限模块在当前层级下的顺序，由小到大【int(11)】
  */
  @ApiModelProperty(value = "权限模块在当前层级下的顺序，由小到大", dataType = "int(11)")
  private Integer seq;

 /**
  * 状态，1：正常，0：冻结【int(11)】
  */
  @ApiModelProperty(value = "状态，1：正常，0：冻结", dataType = "int(11)")
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
  * 最后一次操作时间【datetime】
  */
  @ApiModelProperty(value = "最后一次操作时间", dataType = "datetime")
  private java.time.LocalDateTime operateTime;

 /**
  * 最后一次更新操作者的ip地址【varchar(20)】
  */
  @ApiModelProperty(value = "最后一次更新操作者的ip地址", dataType = "varchar(20)")
  private String operateIp;


}
