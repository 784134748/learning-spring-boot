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
  * 用户id
  */
  @ApiModelProperty(value = "用户id")
  private Long id;

 /**
  * 用户名称
  */
  @ApiModelProperty(value = "用户名称")
  private String username;

 /**
  * 手机号
  */
  @ApiModelProperty(value = "手机号")
  private String telephone;

 /**
  * 邮箱
  */
  @ApiModelProperty(value = "邮箱")
  private String mail;

 /**
  * 加密后的密码
  */
  @ApiModelProperty(value = "加密后的密码")
  private String password;

 /**
  * 用户所在部门的id
  */
  @ApiModelProperty(value = "用户所在部门的id")
  private Long deptId;

 /**
  * 状态，1：正常，0：冻结状态，2：删除
  */
  @ApiModelProperty(value = "状态，1：正常，0：冻结状态，2：删除")
  private Long status;

 /**
  * 备注
  */
  @ApiModelProperty(value = "备注")
  private String remark;

 /**
  * 操作者
  */
  @ApiModelProperty(value = "操作者")
  private String operator;

 /**
  * 最后一次更新时间
  */
  @ApiModelProperty(value = "最后一次更新时间")
  private java.time.LocalDateTime operateTime;

 /**
  * 最后一次更新者的ip地址
  */
  @ApiModelProperty(value = "最后一次更新者的ip地址")
  private String operateIp;


}
