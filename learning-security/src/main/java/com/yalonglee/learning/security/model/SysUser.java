package com.yalonglee.learning.security.model;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysUser {

    /**
     * 用户id
     */
    private long id;
    /**
     * 用户名称
     */
    private String username;
    /**
     * 手机号
     */
    private String telephone;
    /**
     * 邮箱
     */
    private String mail;
    /**
     * 加密后的密码
     */
    private String password;
    /**
     * 用户所在部门的id
     */
    private long deptId;
    /**
     * 状态，1：正常，0：冻结状态，2：删除
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
     * 最后一次更新时间
     */
    private java.sql.Timestamp operateTime;
    /**
     * 最后一次更新者的ip地址
     */
    private String operateIp;

}
