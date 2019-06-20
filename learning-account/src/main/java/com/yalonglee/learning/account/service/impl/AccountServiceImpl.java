package com.yalonglee.learning.account.service.impl;

import com.yalonglee.learning.account.exception.BizzRuntimeException;
import com.yalonglee.learning.account.mapper.AccountMapper;
import com.yalonglee.learning.account.model.AccountModel;
import com.yalonglee.learning.account.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yalonglee
 */
@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public void createAccount(String accountAddr) {
        AccountModel account = accountMapper.selectOneByQuery(AccountModel.builder()
                .accountAddr(accountAddr)
                .build());
        if (account != null) {
            throw new BizzRuntimeException("账户地址已存在！");
        }
        AccountModel accountInfo = AccountModel.builder()
                .accountAddr(accountAddr)
                .build();
        accountMapper.insert(accountInfo);
    }

    @Override
    public void deleteAccount(String accountAddr) {
        AccountModel account = accountMapper.selectOneByQuery(AccountModel.builder()
                .accountAddr(accountAddr)
                .build());
        if (account == null) {
            throw new BizzRuntimeException("账户地址不存在！");
        }
        accountMapper.deleteByPrimaryKey(account.getId());
    }

    @Override
    public void deposit() {

    }

    @Override
    public void transfer() {

    }

    @Override
    public void withdraw() {

    }

}
