package com.yalonglee.learning.account.request;

import lombok.Builder;
import lombok.Data;

/**
 * @author yalonglee
 */
@Data
@Builder
public class AccountDepositRequest {

    /**
     * 流水号
     */
    private String serialNumber;
    /**
     * 充值入账账户地址
     */
    private String targetAccountAddr;
    /**
     * 充值金额
     */
    private Double totalDepositAmount;

}
