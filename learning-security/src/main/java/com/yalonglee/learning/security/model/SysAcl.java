package com.yalonglee.learning.security.model;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysAcl {

    /**
     * 权限id
     */
    private long id;
    /**
     * 权限码
     */
    private String code;
    /**
     * 权限名称
     */
    private String name;
    /**
     * 权限所在的权限模块id
     */
    private long aclModuleId;
    /**
     * 请求的url, 可以填正则表达式
     */
    private String url;
    /**
     * 类型，1：菜单，2：按钮，3：其他
     */
    private long type;
    /**
     * 状态，1：正常，0：冻结
     */
    private long status;
    /**
     * 权限在当前模块下的顺序，由小到大
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
     * 最后一次更新时间
     */
    private LocalDateTime operateTime;
    /**
     * 最后一个更新者的ip地址
     */
    private String operateIp;

}
