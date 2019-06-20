package com.yalonglee.learning.account.service;

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
     * 充值接口
     */
    void deposit();

    /**
     * 转账接口
     */
    void transfer();

    /**
     * 提现接口
     */
    void withdraw();

}
