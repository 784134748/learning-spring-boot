package com.yalonglee.learning.security.model;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysDept {

    /**
     * 部门id
     */
    private long id;
    /**
     * 部门名称
     */
    private String name;
    /**
     * 上级部门id
     */
    private long parentId;
    /**
     * 部门层级
     */
    private String level;
    /**
     * 部门在当前层级下的顺序，由小到大
     */
    private long seq;
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
