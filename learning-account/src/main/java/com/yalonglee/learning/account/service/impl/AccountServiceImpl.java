package com.yalonglee.learning.account.service.impl;

import com.yalonglee.learning.account.domain.AccountBO;
import com.yalonglee.learning.account.request.AccountDepositRequest;
import com.yalonglee.learning.account.request.AccountTransferRequest;
import com.yalonglee.learning.account.request.AccountWithdrawRequest;
import com.yalonglee.learning.account.service.AccountService;
import com.yalonglee.learning.account.utils.account.DepositParam;
import com.yalonglee.learning.account.utils.account.TransferParam;
import com.yalonglee.learning.account.utils.account.WithdrawParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountBO account;

    @Override
    public void createAccount(String accountAddr) {
        account.create(accountAddr);
    }

    @Override
    public void deleteAccount(String accountAddr) {
        account.delete(accountAddr);
    }

    @Override
    public void accountDeposit(AccountDepositRequest accountDepositRequest) {

        DepositParam depositParam = DepositParam.builder().build();
        BeanUtils.copyProperties(accountDepositRequest, depositParam);

        account.deposit(depositParam);
    }

    @Override
    public void accountTransfer(AccountTransferRequest accountTransferRequest) {

        TransferParam transferParam = TransferParam.builder().build();
        BeanUtils.copyProperties(accountTransferRequest, transferParam);

        account.transfer(transferParam);
    }

    @Override
    public void accountWithdraw(AccountWithdrawRequest accountWithdrawRequest) {

        WithdrawParam withdrawParam = WithdrawParam.builder().build();
        BeanUtils.copyProperties(accountWithdrawRequest, withdrawParam);

        account.beforeWithdrawCashes(withdrawParam);
        account.withdraw(withdrawParam);
    }

}
