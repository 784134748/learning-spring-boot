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
@ApiModel(value = "AclForm", description = "权限Form表单")
public class AclForm implements Serializable {

    public static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "更新时传入权限ID", hidden = true, position = 1)
    private Long id;

    @ApiModelProperty(value = "权限点名称", position = 2, example = "admin", required = true)
    @NotBlank(message = "权限点名称不可以为空")
    @Length(min = 2, max = 20, message = "权限点名称长度需要在2-20个字之间")
    private String name;

    @ApiModelProperty(value = "关联权限模块ID", position = 3, example = "1", required = true)
    @NotNull(message = "必须指定权限模块")
    private Integer aclModuleId;

    @ApiModelProperty(value = "权限点URL", position = 4, example = "/sys/acl/acl", required = true)
    @Length(min = 6, max = 100, message = "权限点URL长度需要在6-100个字符之间")
    private String url;

    @ApiModelProperty(value = "权限点的类型", position = 5, example = "1", required = true)
    @NotNull(message = "必须指定权限点的类型")
    @Min(value = 1, message = "权限点类型不合法")
    @Max(value = 3, message = "权限点类型不合法")
    private Integer type;

    @ApiModelProperty(value = "权限点的状态", position = 6, example = "1", required = true)
    @NotNull(message = "必须指定权限点的状态")
    @Min(value = 0, message = "权限点状态不合法")
    @Max(value = 1, message = "权限点状态不合法")
    private Integer status;

    @ApiModelProperty(value = "权限点的展示顺序", position = 7, example = "1", required = true)
    @NotNull(message = "必须指定权限点的展示顺序")
    private Integer seq;

    @ApiModelProperty(value = "权限点备注", position = 8, example = "备注")
    @Length(min = 0, max = 200, message = "权限点备注长度需要在200个字符以内")
    private String remark;

}
