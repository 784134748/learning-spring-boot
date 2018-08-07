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
@ApiModel(value = "UserForm", description = "用户Form表单")
public class UserForm implements Serializable {

    public static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "更新时传入用户ID", hidden = true, position = 1)
    private Long id;

    @ApiModelProperty(value = "用户名", position = 2, example = "admin", required = true)
    @NotBlank(message = "用户名不可以为空")
    @Length(min = 1, max = 20, message = "用户名长度需要在20个字以内")
    private String username;

    @ApiModelProperty(value = "电话", position = 3, example = "18895374321", required = true)
    @NotBlank(message = "电话不可以为空")
    @Length(min = 1, max = 13, message = "电话长度需要在13个字以内")
    private String telephone;

    @ApiModelProperty(value = "邮箱", position = 4, example = "132445@qq.com", required = true)
    @NotBlank(message = "邮箱不允许为空")
    @Length(min = 5, max = 50, message = "邮箱长度需要在50个字符以内")
    private String mail;

    @ApiModelProperty(value = "关联部门ID", position = 5, example = "1", required = true)
    @NotNull(message = "必须提供用户所在的部门")
    private Integer deptId;

    @ApiModelProperty(value = "用户状态", position = 6, example = "1", required = true)
    @NotNull(message = "必须指定用户的状态")
    @Min(value = 0, message = "用户状态不合法")
    @Max(value = 2, message = "用户状态不合法")
    private Integer status;

    @ApiModelProperty(value = "备注", position = 7, example = "备注")
    @Length(min = 0, max = 200, message = "备注长度需要在200个字以内")
    private String remark = "";
}
