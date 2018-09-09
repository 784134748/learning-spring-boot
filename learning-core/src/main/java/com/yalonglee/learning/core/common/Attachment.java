package com.yalonglee.learning.core.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Attachment", description = "附件")
public class Attachment {

    /**
     * 附件名【string】
     */
    @ApiModelProperty(value = "附件名", dataType = "string", example = "附件")
    private String attachedName;

    /**
     * 附件URL【string】
     */
    @ApiModelProperty(value = "附件URL", dataType = "string", example = "/abc/cde.exe")
    private String attachedUrl;
}
