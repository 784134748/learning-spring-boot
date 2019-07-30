package com.yalonglee.learning.account.request;

import lombok.Builder;
import lombok.Data;

/**
 * @author yalonglee
 */
@Data
@Builder
public class AccountWithdrawRequest {
    /**
     * 流水号
     */
    private String serialNumber;
    /**
     * 提现出账账号地址
     */
    private String sourceAccountAddr;
    /**
     * 提现金额
     */
    private Double totalWithdrawAmount;

}
