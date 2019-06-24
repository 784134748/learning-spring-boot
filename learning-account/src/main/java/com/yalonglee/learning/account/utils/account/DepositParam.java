package com.yalonglee.learning.account.utils.account;

import lombok.Builder;
import lombok.Data;

/**
 * @author yalonglee
 */
@Data
@Builder
public class DepositParam {

    /**
     * 流水号
     */
    private String serialNumber;
    /**
     * 充值账户信息
     */
    private AccountInfo depositAccount;
    /**
     * 充值入账账户地址
     */
    private String targetAccountAddr;
    /**
     * 充值金额
     */
    private Double totalDepositAmount;

}
