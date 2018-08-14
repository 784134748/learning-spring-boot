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
@ApiModel(value = "SysRoleUser")
public class SysRoleUser implements Serializable {

  public static final long serialVersionUID = 1L;

 /**
  * null
  */
  @ApiModelProperty(value = "null")
  private Long id;

 /**
  * 角色id
  */
  @ApiModelProperty(value = "角色id")
  private Long roleId;

 /**
  * 用户id
  */
  @ApiModelProperty(value = "用户id")
  private Long userId;

 /**
  * 操作者
  */
  @ApiModelProperty(value = "操作者")
  private String operator;

 /**
  * 最后一次更新的时间
  */
  @ApiModelProperty(value = "最后一次更新的时间")
  private java.time.LocalDateTime operateTime;

 /**
  * 最后一次更新者的ip地址
  */
  @ApiModelProperty(value = "最后一次更新者的ip地址")
  private String operateIp;


}
