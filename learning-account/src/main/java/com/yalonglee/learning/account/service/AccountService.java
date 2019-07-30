package com.yalonglee.learning.account.service;

import com.yalonglee.learning.account.request.AccountDepositRequest;
import com.yalonglee.learning.account.request.AccountTransferRequest;
import com.yalonglee.learning.account.request.AccountWithdrawRequest;

public interface AccountService {

    /**
     * 创建账户
     *
     * @param accountAddr 账户地址
     * @return
     */
    void createAccount(String accountAddr);

    /**
     * 释放账户
     *
     * @param accountAddr
     */
    void deleteAccount(String accountAddr);

    /**
     * 账户充值
     * 事务隔离级别：已提交读（Read committed）
     *
     * @param accountDepositRequest
     */
    void accountDeposit(AccountDepositRequest accountDepositRequest);

    /**
     * 账户转账
     * 事务隔离级别：已提交读（Read committed）
     *
     * @param accountTransferRequest
     */
    void accountTransfer(AccountTransferRequest accountTransferRequest);

    /**
     * 账户提现
     * 事务隔离级别：已提交读（Read committed）
     *
     * @param accountWithdrawRequest
     */
    void accountWithdraw(AccountWithdrawRequest accountWithdrawRequest);

}
