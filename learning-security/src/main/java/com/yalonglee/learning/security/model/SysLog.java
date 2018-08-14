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
@ApiModel(value = "SysLog")
public class SysLog implements Serializable {

  public static final long serialVersionUID = 1L;

 /**
  * null
  */
  @ApiModelProperty(value = "null")
  private Long id;

 /**
  * 权限更新的类型，1：部门，2：用户，3：权限模块，4：权限，5：角色，6：角色用户关系，7：角色权限关系
  */
  @ApiModelProperty(value = "权限更新的类型，1：部门，2：用户，3：权限模块，4：权限，5：角色，6：角色用户关系，7：角色权限关系")
  private Long type;

 /**
  * 基于type后指定的对象id，比如用户、权限、角色等表的主键
  */
  @ApiModelProperty(value = "基于type后指定的对象id，比如用户、权限、角色等表的主键")
  private Long targetId;

 /**
  * 旧值
  */
  @ApiModelProperty(value = "旧值")
  private String oldValue;

 /**
  * 新值
  */
  @ApiModelProperty(value = "新值")
  private String newValue;

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

 /**
  * 当前是否复原过，0：没有，1：复原过
  */
  @ApiModelProperty(value = "当前是否复原过，0：没有，1：复原过")
  private Long status;


}
