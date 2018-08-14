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
@ApiModel(value = "RoleForm", description = "角色Form表单")
public class RoleForm implements Serializable {

    public static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "更新时传入角色ID", hidden = true, position = 1)
    private Long id;

    @ApiModelProperty(value = "角色名称", position = 2, example = "admin", required = true)
    @NotBlank(message = "角色名称不可以为空")
    @Length(min = 2, max = 20, message = "角色名称长度需要在2-20个字之间")
    private String name;

    @ApiModelProperty(value = "角色类型", position = 3, example = "1", required = true)
    @Min(value = 1, message = "角色类型不合法")
    @Max(value = 2, message = "角色类型不合法")
    @Builder.Default
    private Integer type = 1;

    @ApiModelProperty(value = "角色状态", position = 4, example = "1", required = true)
    @NotNull(message = "角色状态不可以为空")
    @Min(value = 0, message = "角色状态不合法")
    @Max(value = 1, message = "角色状态不合法")
    private Integer status;

    @ApiModelProperty(value = "角色备注", position = 5, example = "备注")
    @Length(min = 0, max = 200, message = "角色备注长度需要在200个字符以内")
    private String remark;
}
