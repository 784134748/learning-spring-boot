package com.yalonglee.learning.security.model;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysAclModule {

    /**
     * 权限模块id
     */
    private long id;
    /**
     * 权限模块名称
     */
    private String name;
    /**
     * 上级权限模块id
     */
    private long parentId;
    /**
     * 权限模块层级
     */
    private String level;
    /**
     * 权限模块在当前层级下的顺序，由小到大
     */
    private long seq;
    /**
     * 状态，1：正常，0：冻结
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
     * 最后一次操作时间
     */
    private java.sql.Timestamp operateTime;
    /**
     * 最后一次更新操作者的ip地址
     */
    private String operateIp;

}
