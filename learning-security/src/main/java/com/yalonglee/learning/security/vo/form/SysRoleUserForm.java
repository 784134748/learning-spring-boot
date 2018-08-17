package com.yalonglee.learning.security.vo.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.time.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "SysRoleUserForm")
public class SysRoleUserForm implements Serializable {

  public static final long serialVersionUID = 1L;

 /**
  * 角色id【int(11)】
  */
  @ApiModelProperty(value = "角色id", dataType = "int(11)")
  private Integer roleId;

 /**
  * 用户id【int(11)】
  */
  @ApiModelProperty(value = "用户id", dataType = "int(11)")
  private Integer userId;

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
