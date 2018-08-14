package com.yalonglee.learning.security.vo.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "DeptForm", description = "部门Form表单")
public class DeptForm implements Serializable {

    public static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "更新时传入部门ID", hidden = true, position = 1)
    private Long id;

    @ApiModelProperty(value = "部门名称", position = 2, example = "技术部", required = true)
    @NotBlank(message = "部门名称不可以为空")
    @Length(max = 15, min = 2, message = "部门名称长度需要在2-15个字之间")
    private String name;

    @ApiModelProperty(value = "父节点", position = 3, example = "1", required = true)
    @Builder.Default
    private Integer parentId = 0;

    @ApiModelProperty(value = "展示顺序", position = 4, example = "1", required = true)
    @NotNull(message = "展示顺序不可以为空")
    private Integer seq;

    @ApiModelProperty(value = "备注", position = 5, example = "备注")
    @Length(max = 150, message = "备注的长度需要在150个字以内")
    private String remark;
}
