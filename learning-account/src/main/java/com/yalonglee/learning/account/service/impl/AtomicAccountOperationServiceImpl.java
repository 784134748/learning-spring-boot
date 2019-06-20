package com.yalonglee.learning.account.service.impl;

import com.yalonglee.learning.account.domain.AccountDTO;
import com.yalonglee.learning.account.exception.BizzRuntimeException;
import com.yalonglee.learning.account.mapper.AccountMapper;
import com.yalonglee.learning.account.service.AtomicAccountOperationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yalonglee
 * 账户的一些原子化操作
 * 开始转账前锁定账户
 * 提现开始前锁定金额
 * 提现结束后释放金额
 */
@Service
@Slf4j
public class AtomicAccountOperationServiceImpl implements AtomicAccountOperationService {

    @Autowired
    private AccountMapper accountMapper;

    /**
     * 开始转账前锁定账户
     *
     * @param accountAddr
     * @param paymentAmount
     * @return
     * @throws BizzRuntimeException
     */
    @Override
    public AccountDTO startTransaction(String accountAddr, Double paymentAmount) {
        return lockedAccountAvailablePaymentAmount(accountAddr, paymentAmount);
    }

    /**
     * 提现开始前锁定金额
     *
     * @param serialNumber
     * @param accountAddr
     * @param waitWithdrawCashesAmount
     */
    @Override
    public void waitWithdrawCashes(String serialNumber, String accountAddr, Double waitWithdrawCashesAmount) {
        AccountDTO account = this.lockedAccountAvailableWithdrawCashesAmount(accountAddr, waitWithdrawCashesAmount);
        //账户id
        long accountId = account.getId();
        //待更新的提现金额
        double updateWaitWithdrawCashes = account.getWaitWithDrawCashesAmount() + waitWithdrawCashesAmount;
        accountMapper.updateWaitWithdrawCashes(accountId, updateWaitWithdrawCashes);
        log.info("流水号：{},锁定账户地址为 {} 的账户待提现金额 {}", serialNumber, accountAddr, waitWithdrawCashesAmount);
    }

    /**
     * 提现结束后释放金额
     *
     * @param serialNumber
     * @param accountAddr
     * @param withdrawCashesAmount
     */
    @Override
    public void endWithdrawCashes(String serialNumber, String accountAddr, Double withdrawCashesAmount) {
        AccountDTO account = accountMapper.getAccountAmountByAccountAddr(accountAddr);
        //账户id
        long accountId = account.getId();
        //待更新的提现金额
        double updateWaitWithdrawCashes = account.getWaitWithDrawCashesAmount() - withdrawCashesAmount;
        accountMapper.updateWaitWithdrawCashes(accountId, updateWaitWithdrawCashes);
        log.info("流水号：{},释放账户地址为 {} 的账户待提现金额 {}", serialNumber, accountAddr, withdrawCashesAmount);
    }

    /**
     * 判断账户余额是否满足支付条件（非锁定状态下)
     *
     * @param accountAddr
     * @param paymentAmount
     */
    private AccountDTO accountAvailablePaymentAmount(String accountAddr, Double paymentAmount) {
        AccountDTO account = accountMapper.getAccountAmountByAccountAddr(accountAddr);
        //检查账户可用余额
        checkAccountAvailableAmount(account, paymentAmount, null);
        return account;
    }

    /**
     * 判断账户余额是否满足支付条件（锁定状态下）
     *
     * @param accountAddr
     */
    private AccountDTO lockedAccountAvailablePaymentAmount(String accountAddr, Double paymentAmount) {
        //不锁表情况下判断账户余额是否满足支付条件
        this.accountAvailablePaymentAmount(accountAddr, paymentAmount);
        //锁表
        AccountDTO lockedAccount = getAccountLock(accountAddr);
        //检查账户可用余额
        checkAccountAvailableAmount(lockedAccount, paymentAmount, null);
        return lockedAccount;
    }

    /**
     * 判断账户余额是否满足提现条件（非锁定状态下）
     *
     * @param accountAddr
     * @param withDrawCashesAmount
     */
    private AccountDTO accountAvailableWithdrawCashesAmount(String accountAddr, Double withDrawCashesAmount) {
        AccountDTO account = accountMapper.getAccountAmountByAccountAddr(accountAddr);
        //检查账户可提现金额
        checkAccountAvailableAmount(account, null, withDrawCashesAmount);
        return account;
    }

    /**
     * 判断账户余额是否满足提现条件（锁定状态下）
     *
     * @param accountAddr
     */
    private AccountDTO lockedAccountAvailableWithdrawCashesAmount(String accountAddr, Double withDrawCashesAmount) {
        //不锁表情况下判断账户余额是否满足提现条件
        this.accountAvailableWithdrawCashesAmount(accountAddr, withDrawCashesAmount);
        //锁表
        AccountDTO lockedAccount = getAccountLock(accountAddr);
        //检查账户可提现金额
        checkAccountAvailableAmount(lockedAccount, null, withDrawCashesAmount);
        return lockedAccount;
    }

    /**
     * 获取账户信息（加行锁）
     *
     * @param accountAddr
     * @throws BizzRuntimeException
     */
    private AccountDTO getAccountLock(String accountAddr) throws BizzRuntimeException {
        //获取记录锁（行锁）
        AccountDTO account = accountMapper.getRecordLock(accountAddr);
        //检查账户
        checkAccountAvailableAmount(account, null, null);
        //通过时间戳限定锁所能操作的数据集的范围（数据集的创建时间必须小于设定的时间）
        accountMapper.limitAccountRecordOperationScopeByTimestampLock(accountAddr);
        return account;
    }

    /**
     * 检查账户
     *
     * @param account
     * @param paymentAmount
     */
    private void checkAccountAvailableAmount(AccountDTO account, Double paymentAmount, Double withDrawCashesAmount) {
        if (account == null) {
            throw new BizzRuntimeException("账户地址不存在");
        }
        if (account.getTimestampLock() > account.getCurrentTimestamp()) {
            throw new BizzRuntimeException("数据库时间异常");
        }
        if (paymentAmount != null && account.getTotalAvailableAmount() < paymentAmount) {
            throw new BizzRuntimeException("当前账户可用余额不足");
        }
        if (withDrawCashesAmount != null && account.getWaitWithDrawCashesAmount() < withDrawCashesAmount) {
            throw new BizzRuntimeException("当前账户可提现金额不足");
        }
    }

}
