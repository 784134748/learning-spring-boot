package com.yalonglee.learning.security.model;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysRole {

    /**
     * 角色id
     */
    private long id;
    /**
     * null
     */
    private String name;
    /**
     * 角色的类型，1：管理员角色，2：其他
     */
    private long type;
    /**
     * 状态，1：可用，0：冻结
     */
    private long status;
    /**
     * 备注
     */
    private String remark;
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
