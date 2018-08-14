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
  * 权限模块id
  */
  @ApiModelProperty(value = "权限模块id")
  private Long id;

 /**
  * 权限模块名称
  */
  @ApiModelProperty(value = "权限模块名称")
  private String name;

 /**
  * 上级权限模块id
  */
  @ApiModelProperty(value = "上级权限模块id")
  private Long parentId;

 /**
  * 权限模块层级
  */
  @ApiModelProperty(value = "权限模块层级")
  private String level;

 /**
  * 权限模块在当前层级下的顺序，由小到大
  */
  @ApiModelProperty(value = "权限模块在当前层级下的顺序，由小到大")
  private Long seq;

 /**
  * 状态，1：正常，0：冻结
  */
  @ApiModelProperty(value = "状态，1：正常，0：冻结")
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
  * 最后一次操作时间
  */
  @ApiModelProperty(value = "最后一次操作时间")
  private java.time.LocalDateTime operateTime;

 /**
  * 最后一次更新操作者的ip地址
  */
  @ApiModelProperty(value = "最后一次更新操作者的ip地址")
  private String operateIp;


}
