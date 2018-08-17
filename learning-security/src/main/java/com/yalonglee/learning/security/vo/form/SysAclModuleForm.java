package com.yalonglee.learning.security.vo.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "SysAclModuleForm", description = "权限模块Form表单")
public class SysAclModuleForm implements Serializable {

    public static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "更新时传入权限模块ID", hidden = true, position = 1)
    private Long id;

    @ApiModelProperty(value = "权限模块名称", position = 2, example = "acl_model", required = true)
    @NotBlank(message = "权限模块名称不可以为空")
    @Length(min = 2, max = 20, message = "权限模块名称长度需要在2~20个字之间")
    private String name;

    @ApiModelProperty(value = "权限模块父节点", position = 3, example = "1", required = true)
    @Builder.Default
    private Integer parentId = 0;

    @ApiModelProperty(value = "权限模块展示顺序", position = 4, example = "1", required = true)
    @NotNull(message = "权限模块展示顺序不可以为空")
    private Integer seq;

    @ApiModelProperty(value = "权限模块状态", position = 5, example = "1", required = true)
    @NotNull(message = "权限模块状态不可以为空")
    @Min(value = 0, message = "权限模块状态不合法")
    @Max(value = 1, message = "权限模块状态不合法")
    private Integer status;

    @ApiModelProperty(value = "权限模块备注", position = 6, example = "备注")
    @Length(max = 200, message = "权限模块备注需要在200个字之间")
    private String remark;

}
