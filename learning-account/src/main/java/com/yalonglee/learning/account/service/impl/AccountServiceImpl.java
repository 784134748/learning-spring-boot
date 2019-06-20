package com.yalonglee.learning.account.service.impl;

import com.yalonglee.learning.account.domain.AccountDTO;
import com.yalonglee.learning.account.exception.BizzRuntimeException;
import com.yalonglee.learning.account.mapper.AccountMapper;
import com.yalonglee.learning.account.model.AccountModel;
import com.yalonglee.learning.account.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    /**
     * 获取账户信息（加行锁）
     *
     * @param accountAddr
     * @throws BizzRuntimeException
     */
    private void getAccountLock(String accountAddr) throws BizzRuntimeException {
        //获取锁
        AccountModel account = accountMapper.getLock(accountAddr);
        if (account == null) {
            throw new BizzRuntimeException("账户地址不存在！");
        }
    }

    /**
     * 获取转账时刻的账户信息（加行锁）
     *
     * @param accountAddr
     * @param paymentAmount
     * @return
     * @throws BizzRuntimeException
     */
    private AccountDTO getTransactionLock(String accountAddr, Double paymentAmount) throws BizzRuntimeException {
        //判断账户余额是否满足支付条件
        accountAmountAvailablePay(accountAddr, paymentAmount);
        //获取锁
        AccountModel account = accountMapper.getLock(accountAddr);
        if (account == null) {
            throw new BizzRuntimeException("账户地址不存在！");
        }
        account.setAccountAddr(accountAddr);
        //判断锁定时刻账户余额是否满足支付条件
        AccountDTO accountLock = accountAmountAvailablePayLocked(accountAddr, paymentAmount);
        return accountLock;
    }

    /**
     * 获取提现时刻的账户信息（加行锁）
     *
     * @param accountAddr
     * @param paymentAmount
     * @return
     */
    private AccountDTO getWithdrawCashesLock(String accountAddr, Double paymentAmount) {
        //判断账户余额是否满足提现条件
        accountAmountAvailableWithdrawCashes(accountAddr, paymentAmount);
        //获取锁
        AccountModel account = accountMapper.getLock(accountAddr);
        if (account == null) {
            throw new BizzRuntimeException("账户地址不存在！");
        }
        account.setAccountAddr(accountAddr);
        //判断锁定时刻账户余额是否满足支付条件
        AccountDTO accountLock = accountAmountAvailableWithdrawCashesLocked(accountAddr, paymentAmount);
        return accountLock;
    }

    /**
     * 重新获取账户信息
     *
     * @param accountAddr
     * @param paymentAmount
     * @return
     * @throws BizzRuntimeException
     */
    private AccountDTO restoreAccount(String accountAddr, Double paymentAmount) throws BizzRuntimeException {
        //判断账户余额是否满足支付条件
        accountAmountAvailablePay(accountAddr, paymentAmount);
        //判断锁定时刻账户余额是否满足支付条件
        AccountDTO accountLock = accountAmountAvailablePayLocked(accountAddr, paymentAmount);
        return accountLock;
    }

    /**
     * 锁定待提现金额
     *
     * @param serialNumber
     * @param accountAddr
     * @param withdrawCashesAmount
     */
    private void waitWithdrawCashes(String serialNumber, String accountAddr, Double withdrawCashesAmount) {
        //判断账户余额是否满足提现条件
        accountAmountAvailablePay(accountAddr, withdrawCashesAmount);
        //获取锁
        AccountModel account = accountMapper.getLock(accountAddr);
        if (account == null) {
            throw new BizzRuntimeException("账户地址不存在！");
        }
        //账户id
        long accountId = account.getId();
        //待更新的提现金额
        double updateWaitWithdrawCashes = account.getWaitWithdrawCashes() + withdrawCashesAmount;
        accountMapper.updateWaitWithdrawCashes(accountId, updateWaitWithdrawCashes);
        log.info("流水号：{},锁定账户地址为 {} 的账户待提现金额 {}", serialNumber, accountAddr, withdrawCashesAmount);
    }

    /**
     * 释放提现金额
     *
     * @param serialNumber
     * @param accountAddr
     * @param withdrawCashesAmount
     */
    private void endWithdrawCashes(String serialNumber, String accountAddr, Double withdrawCashesAmount) {
        //获取锁
        AccountModel account = accountMapper.getLock(accountAddr);
        if (account == null) {
            throw new BizzRuntimeException("账户地址不存在！");
        }
        //账户id
        long accountId = account.getId();
        //待更新的提现金额
        double updateWaitWithdrawCashes = account.getWaitWithdrawCashes() - withdrawCashesAmount;
        accountMapper.updateWaitWithdrawCashes(accountId, updateWaitWithdrawCashes);
        log.info("流水号：{},释放账户地址为 {} 的账户待提现金额 {}", serialNumber, accountAddr, withdrawCashesAmount);
    }

    /**
     * 判断账户余额是否满足支付条件（非锁定状态下)
     *
     * @param accountAddr
     * @param paymentAmount
     */
    private void accountAmountAvailablePay(String accountAddr, Double paymentAmount) {
        //查询账户余额
        AccountDTO accountDTO = accountMapper.getAvailableAmount(accountAddr);
        //检查账户可用余额
        checkAccountAmountAvailable(accountDTO, paymentAmount);
    }

    /**
     * 判断账户余额是否满足支付条件（锁定状态下）
     *
     * @param accountAddr
     */
    private AccountDTO accountAmountAvailablePayLocked(String accountAddr, Double paymentAmount) {
        this.accountAmountAvailablePay(accountAddr, paymentAmount);
        //锁定当前的账户记录（当前事务中所有操作仅针对当前锁定的账户记录）
        accountMapper.lockCurrentAccountRecord(accountAddr);
        AccountDTO lockAccountDTO = accountMapper.getLockAvailableAmount(accountAddr);
        //检查账户可用余额
        checkAccountAmountAvailable(lockAccountDTO, paymentAmount);
        return lockAccountDTO;
    }

    /**
     * 判断账户余额是否满足提现条件（非锁定状态下）
     *
     * @param accountAddr
     * @param paymentAmount
     */
    private void accountAmountAvailableWithdrawCashes(String accountAddr, Double paymentAmount) {
        //查询锁定的提现金额
        AccountDTO accountDTO = accountMapper.getLockWaitWithdrawCashes(accountAddr);
        //检查账户可用余额
        checkAccountAmountAvailable(accountDTO, paymentAmount);
    }

    /**
     * 判断账户余额是否满足提现条件（锁定状态下）
     *
     * @param accountAddr
     */
    private AccountDTO accountAmountAvailableWithdrawCashesLocked(String accountAddr, Double paymentAmount) {

        //锁定当前的账户记录（当前事务中所有操作仅针对当前锁定的账户记录）
        accountMapper.lockCurrentAccountRecord(accountAddr);
        //查询锁定的提现金额
        AccountDTO lockAccountDTO = accountMapper.getLockWaitWithdrawCashes(accountAddr);
        //检查账户可用余额
        checkAccountAmountAvailable(lockAccountDTO, paymentAmount);
        return lockAccountDTO;
    }

    /**
     * 检查账户可用余额
     *
     * @param accountDTO
     * @param paymentAmount
     */
    private void checkAccountAmountAvailable(AccountDTO accountDTO, Double paymentAmount) {
        if (paymentAmount == null) {
            throw new BizzRuntimeException("待支付/提现金额异常");
        }
        if (accountDTO == null) {
            throw new BizzRuntimeException("账户地址不存在");
        }
        if (accountDTO.getWaitWithDrawCashes() < paymentAmount) {
            throw new BizzRuntimeException("余额不足");
        }
    }

}
