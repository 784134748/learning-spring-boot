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
@ApiModel(value = "SysUser")
public class SysUser implements Serializable {

  public static final long serialVersionUID = 1L;

 /**
  * 用户id【bigint(11) unsigned】
  */
  @ApiModelProperty(value = "用户id", dataType = "bigint(11) unsigned")
  private Long id;

 /**
  * 用户名称【varchar(20)】
  */
  @ApiModelProperty(value = "用户名称", dataType = "varchar(20)")
  private String username;

 /**
  * 手机号【varchar(13)】
  */
  @ApiModelProperty(value = "手机号", dataType = "varchar(13)")
  private String telephone;

 /**
  * 邮箱【varchar(20)】
  */
  @ApiModelProperty(value = "邮箱", dataType = "varchar(20)")
  private String mail;

 /**
  * 加密后的密码【varchar(40)】
  */
  @ApiModelProperty(value = "加密后的密码", dataType = "varchar(40)")
  private String password;

 /**
  * 用户所在部门的id【int(11)】
  */
  @ApiModelProperty(value = "用户所在部门的id", dataType = "int(11)")
  private Integer deptId;

 /**
  * 状态，1：正常，0：冻结状态，2：删除【int(11)】
  */
  @ApiModelProperty(value = "状态，1：正常，0：冻结状态，2：删除", dataType = "int(11)")
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
  * 最后一次更新时间【datetime】
  */
  @ApiModelProperty(value = "最后一次更新时间", dataType = "datetime")
  private java.time.LocalDateTime operateTime;

 /**
  * 最后一次更新者的ip地址【varchar(20)】
  */
  @ApiModelProperty(value = "最后一次更新者的ip地址", dataType = "varchar(20)")
  private String operateIp;


}
