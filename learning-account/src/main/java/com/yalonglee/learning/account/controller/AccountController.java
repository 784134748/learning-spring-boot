package com.yalonglee.learning.account.controller;

import com.yalonglee.learning.account.request.AccountDepositRequest;
import com.yalonglee.learning.account.request.AccountTransferRequest;
import com.yalonglee.learning.account.request.AccountWithdrawRequest;
import com.yalonglee.learning.account.service.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "账户相关接口")
@RequestMapping(value = "/v1/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @ApiOperation(value = "创建账户")
    @PostMapping(value = "/create/{accountAddr}")
    public void createAccount(@PathVariable("accountAddr") String accountAddr) {
        accountService.createAccount(accountAddr);
    }

    @ApiOperation(value = "释放账户")
    @PostMapping(value = "/delete/{accountAddr}")
    public void deleteAccount(@PathVariable("accountAddr") String accountAddr) {
        accountService.deleteAccount(accountAddr);
    }

    @ApiOperation(value = "账户充值")
    @PostMapping(value = "/deposit")
    public void accountDeposit(@RequestBody AccountDepositRequest accountDepositRequest) {
        accountService.accountDeposit(accountDepositRequest);
    }

    @ApiOperation(value = "账户转账")
    @PostMapping(value = "/transfer")
    public void accountTransfer(@RequestBody AccountTransferRequest accountTransferRequest) {
        accountService.accountTransfer(accountTransferRequest);
    }

    @ApiOperation(value = "账户提现")
    @PostMapping(value = "/withdraw")
    public void accountWithdraw(@RequestBody AccountWithdrawRequest accountWithdrawRequest) {
        accountService.accountWithdraw(accountWithdrawRequest);
    }

}
