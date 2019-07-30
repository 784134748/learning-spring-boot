package com.yalonglee.learning.account.controller;

import com.yalonglee.learning.account.request.AccountDepositRequest;
import com.yalonglee.learning.account.request.AccountTransferRequest;
import com.yalonglee.learning.account.request.AccountWithdrawRequest;
import com.yalonglee.learning.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    /**
     * 创建账户
     *
     * @param accountAddr 账户地址
     * @return
     */
    @PostMapping(value = "/create/{accountAddr}")
    public void createAccount(@PathVariable("accountAddr") String accountAddr) {
        accountService.createAccount(accountAddr);
    }

    /**
     * 释放账户
     *
     * @param accountAddr
     */
    @PostMapping(value = "/delete/{accountAddr}")
    public void deleteAccount(@PathVariable("accountAddr") String accountAddr) {
        accountService.deleteAccount(accountAddr);
    }

    /**
     * 账户充值
     * 事务隔离级别：已提交读（Read committed）
     *
     * @param accountDepositRequest
     */
    @PostMapping(value = "/deposit")
    public void accountDeposit(@RequestBody AccountDepositRequest accountDepositRequest) {
        accountService.accountDeposit(accountDepositRequest);
    }

    /**
     * 账户转账
     * 事务隔离级别：已提交读（Read committed）
     *
     * @param accountTransferRequest
     */
    @PostMapping(value = "/transfer")
    public void accountTransfer(@RequestBody AccountTransferRequest accountTransferRequest) {
        accountService.accountTransfer(accountTransferRequest);
    }

    /**
     * 账户提现
     * 事务隔离级别：已提交读（Read committed）
     *
     * @param accountWithdrawRequest
     */
    @PostMapping(value = "/withdraw")
    public void accountWithdraw(@RequestBody AccountWithdrawRequest accountWithdrawRequest) {
        accountService.accountWithdraw(accountWithdrawRequest);
    }

}
