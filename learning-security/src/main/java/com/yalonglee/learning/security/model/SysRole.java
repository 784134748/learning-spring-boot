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
  * 角色id
  */
  @ApiModelProperty(value = "角色id")
  private Long id;

 /**
  * null
  */
  @ApiModelProperty(value = "null")
  private String name;

 /**
  * 角色的类型，1：管理员角色，2：其他
  */
  @ApiModelProperty(value = "角色的类型，1：管理员角色，2：其他")
  private Long type;

 /**
  * 状态，1：可用，0：冻结
  */
  @ApiModelProperty(value = "状态，1：可用，0：冻结")
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
