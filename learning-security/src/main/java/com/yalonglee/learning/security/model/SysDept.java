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
@ApiModel(value = "SysDept")
public class SysDept implements Serializable {

  public static final long serialVersionUID = 1L;

 /**
  * 部门id
  */
  @ApiModelProperty(value = "部门id")
  private Long id;

 /**
  * 部门名称
  */
  @ApiModelProperty(value = "部门名称")
  private String name;

 /**
  * 上级部门id
  */
  @ApiModelProperty(value = "上级部门id")
  private Long parentId;

 /**
  * 部门层级
  */
  @ApiModelProperty(value = "部门层级")
  private String level;

 /**
  * 部门在当前层级下的顺序，由小到大
  */
  @ApiModelProperty(value = "部门在当前层级下的顺序，由小到大")
  private Long seq;

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
