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
  * 权限id
  */
  @ApiModelProperty(value = "权限id")
  private Long id;

 /**
  * 权限码
  */
  @ApiModelProperty(value = "权限码")
  private String code;

 /**
  * 权限名称
  */
  @ApiModelProperty(value = "权限名称")
  private String name;

 /**
  * 权限所在的权限模块id
  */
  @ApiModelProperty(value = "权限所在的权限模块id")
  private Long aclModuleId;

 /**
  * 请求的url, 可以填正则表达式
  */
  @ApiModelProperty(value = "请求的url, 可以填正则表达式")
  private String url;

 /**
  * 类型，1：菜单，2：按钮，3：其他
  */
  @ApiModelProperty(value = "类型，1：菜单，2：按钮，3：其他")
  private Long type;

 /**
  * 状态，1：正常，0：冻结
  */
  @ApiModelProperty(value = "状态，1：正常，0：冻结")
  private Long status;

 /**
  * 权限在当前模块下的顺序，由小到大
  */
  @ApiModelProperty(value = "权限在当前模块下的顺序，由小到大")
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
  * 最后一次更新时间
  */
  @ApiModelProperty(value = "最后一次更新时间")
  private java.time.LocalDateTime operateTime;

 /**
  * 最后一个更新者的ip地址
  */
  @ApiModelProperty(value = "最后一个更新者的ip地址")
  private String operateIp;


}
