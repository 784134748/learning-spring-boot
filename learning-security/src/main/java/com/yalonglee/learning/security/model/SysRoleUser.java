package com.yalonglee.learning.security.model;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysRoleUser {

    /**
     * null
     */
    private long id;
    /**
     * 角色id
     */
    private long roleId;
    /**
     * 用户id
     */
    private long userId;
    /**
     * 操作者
     */
    private String operator;
    /**
     * 最后一次更新的时间
     */
    private java.sql.Timestamp operateTime;
    /**
     * 最后一次更新者的ip地址
     */
    private String operateIp;

}