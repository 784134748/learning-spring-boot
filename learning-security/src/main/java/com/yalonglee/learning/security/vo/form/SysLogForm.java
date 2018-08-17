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
@ApiModel(value = "SysLogForm")
public class SysLogForm implements Serializable {

  public static final long serialVersionUID = 1L;

 /**
  * 权限更新的类型，1：部门，2：用户，3：权限模块，4：权限，5：角色，6：角色用户关系，7：角色权限关系【int(11)】
  */
  @ApiModelProperty(value = "权限更新的类型，1：部门，2：用户，3：权限模块，4：权限，5：角色，6：角色用户关系，7：角色权限关系", dataType = "int(11)")
  private Integer type;

 /**
  * 基于type后指定的对象id，比如用户、权限、角色等表的主键【int(11)】
  */
  @ApiModelProperty(value = "基于type后指定的对象id，比如用户、权限、角色等表的主键", dataType = "int(11)")
  private Integer targetId;

 /**
  * 旧值【text】
  */
  @ApiModelProperty(value = "旧值", dataType = "text")
  private String oldValue;

 /**
  * 新值【text】
  */
  @ApiModelProperty(value = "新值", dataType = "text")
  private String newValue;

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

 /**
  * 当前是否复原过，0：没有，1：复原过【int(11)】
  */
  @ApiModelProperty(value = "当前是否复原过，0：没有，1：复原过", dataType = "int(11)")
  private Integer status;


}
