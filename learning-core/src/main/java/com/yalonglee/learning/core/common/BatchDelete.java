package com.yalonglee.learning.core.common;

import com.google.common.collect.Lists;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "BatchDelete", description = "批量删除")
public class BatchDelete {

    @ApiModelProperty(value = "待删数据ID集合")
    @Builder.Default
    List<Long> ids = Lists.newArrayList();
}
