package com.yalonglee.learning.account.utils.account;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author yalonglee
 */
@Data
@Builder
public class WithdrawParam {
    /**
     * 提现账户信息
     */
    private AccountInfo withdrawAccount;
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
    /**
     * 可提现的账户记录
     */
    private List<CompleteTransferAccountRecordInfo> completeTransferAccountRecordInfoList;
}
