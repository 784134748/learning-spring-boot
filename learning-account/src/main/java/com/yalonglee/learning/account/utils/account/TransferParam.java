package com.yalonglee.learning.account.utils.account;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author yalonglee
 */
@Data
@Builder
public class TransferParam {

    /**
     * 流水号
     */
    String serialNumber;
    /**
     * 充值账户信息
     */
    private AccountInfo transferAccount;
    /**
     * 转账出账账号地址
     */
    String sourceAccountAddr;
    /**
     * 转账金额
     */
    Double totalTransferAmount;
    /**
     * 可支付的账户记录
     */
    List<CompleteTransferAccountRecordInfo> completeTransferAccountRecordInfoList;
    /**
     * 入账账户地址
     */
    List<TransferTarget> transferTargetList;

}
