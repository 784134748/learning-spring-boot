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
  * null【bigint(11) unsigned】
  */
  @ApiModelProperty(value = "null", dataType = "bigint(11) unsigned")
  private Long id;

 /**
  * 角色id【bigint(11)】
  */
  @ApiModelProperty(value = "角色id", dataType = "bigint(11)")
  private Long roleId;

 /**
  * 用户id【bigint(11)】
  */
  @ApiModelProperty(value = "用户id", dataType = "bigint(11)")
  private Long userId;

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
