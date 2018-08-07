package com.yalonglee.learning.security.model;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysRoleAcl {

    /**
     * null
     */
    private long id;
    /**
     * 角色id
     */
    private long roleId;
    /**
     * 权限id
     */
    private long aclId;
    /**
     * 操作者
     */
    private String operator;
    /**
     * 最后一次更新的时间
     */
    private LocalDateTime operateTime;
    /**
     * 最后一次更新者的ip
     */
    private String operateIp;

}
