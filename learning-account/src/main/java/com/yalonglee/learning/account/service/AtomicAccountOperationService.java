package com.yalonglee.learning.account.service;


import com.yalonglee.learning.account.utils.account.AccountInfo;
import com.yalonglee.learning.account.exception.BizzRuntimeException;

/**
 * @author yalonglee
 */
public interface AtomicAccountOperationService {

    /**
     * 开始转账前锁定账户
     *
     * @param accountAddr
     * @param paymentAmount
     * @return
     * @throws BizzRuntimeException
     */
    AccountInfo startTransaction(String accountAddr, Double paymentAmount);

    /**
     * 提现开始前锁定金额
     *
     * @param serialNumber
     * @param accountAddr
     * @param waitWithdrawCashesAmount
     */
    void waitWithdrawCashes(String serialNumber, String accountAddr, Double waitWithdrawCashesAmount);

    /**
     * 提现结束后释放金额
     *
     * @param serialNumber
     * @param accountAddr
     * @param withdrawCashesAmount
     */
    void endWithdrawCashes(String serialNumber, String accountAddr, Double withdrawCashesAmount);

}
