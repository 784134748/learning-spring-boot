package com.yalonglee.learning.account.service.impl;

import com.yalonglee.learning.account.utils.account.AccountInfo;
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
    public AccountInfo startTransaction(String accountAddr, Double paymentAmount) {
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
        AccountInfo account = this.lockedAccountAvailableWithdrawCashesAmount(accountAddr, waitWithdrawCashesAmount);
        //账户id
        long accountId = account.getId();
        //待更新的提现金额
        double updateWaitWithdrawCashes = account.getAccountWaitWithdrawCashesAmount() + waitWithdrawCashesAmount;
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
        AccountInfo account = accountMapper.getAccountAmountByAccountAddr(accountAddr);
        //账户id
        long accountId = account.getId();
        //待更新的提现金额
        double updateWaitWithdrawCashes = account.getAccountWaitWithdrawCashesAmount() - withdrawCashesAmount;
        accountMapper.updateWaitWithdrawCashes(accountId, updateWaitWithdrawCashes);
        log.info("流水号：{},释放账户地址为 {} 的账户待提现金额 {}", serialNumber, accountAddr, withdrawCashesAmount);
    }

    /**
     * 判断账户余额是否满足支付条件（非锁定状态下)
     *
     * @param accountAddr
     * @param paymentAmount
     */
    private AccountInfo accountAvailablePaymentAmount(String accountAddr, Double paymentAmount) {
        AccountInfo account = accountMapper.getAccountAmountByAccountAddr(accountAddr);
        //检查账户可用余额
        checkAccountAvailableAmount(account, paymentAmount, null);
        return account;
    }

    /**
     * 判断账户余额是否满足支付条件（锁定状态下）
     *
     * @param accountAddr
     */
    private AccountInfo lockedAccountAvailablePaymentAmount(String accountAddr, Double paymentAmount) {
        //不锁表情况下判断账户余额是否满足支付条件
        this.accountAvailablePaymentAmount(accountAddr, paymentAmount);
        //锁表
        AccountInfo lockedAccount = getAccountLock(accountAddr);
        //检查账户可用余额
        checkAccountAvailableAmount(lockedAccount, paymentAmount, null);
        return lockedAccount;
    }

    /**
     * 判断账户余额是否满足提现条件（非锁定状态下）
     *
     * @param accountAddr
     * @param withdrawCashesAmount
     */
    private AccountInfo accountAvailableWithdrawCashesAmount(String accountAddr, Double withdrawCashesAmount) {
        AccountInfo account = accountMapper.getAccountAmountByAccountAddr(accountAddr);
        //检查账户可提现金额
        checkAccountAvailableAmount(account, null, withdrawCashesAmount);
        return account;
    }

    /**
     * 判断账户余额是否满足提现条件（锁定状态下）
     *
     * @param accountAddr
     */
    private AccountInfo lockedAccountAvailableWithdrawCashesAmount(String accountAddr, Double withdrawCashesAmount) {
        //不锁表情况下判断账户余额是否满足提现条件
        this.accountAvailableWithdrawCashesAmount(accountAddr, withdrawCashesAmount);
        //锁表
        AccountInfo lockedAccount = getAccountLock(accountAddr);
        //检查账户可提现金额
        checkAccountAvailableAmount(lockedAccount, null, withdrawCashesAmount);
        return lockedAccount;
    }

    /**
     * 获取账户信息（加行锁）
     *
     * @param accountAddr
     * @throws BizzRuntimeException
     */
    private AccountInfo getAccountLock(String accountAddr) throws BizzRuntimeException {
        //获取记录锁（行锁）
        AccountInfo account = accountMapper.getRecordLock(accountAddr);
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
    private void checkAccountAvailableAmount(AccountInfo account, Double paymentAmount, Double withdrawCashesAmount) {
        if (account == null) {
            throw new BizzRuntimeException("账户地址不存在");
        }
        if (account.getAccountTimestampLock() > account.getAccountCurrentTimestamp()) {
            throw new BizzRuntimeException("数据库时间异常");
        }
        if (paymentAmount != null && account.getAccountTotalAvailableAmount() < paymentAmount) {
            throw new BizzRuntimeException("当前账户可用余额不足");
        }
        if (withdrawCashesAmount != null && account.getAccountWaitWithdrawCashesAmount() < withdrawCashesAmount) {
            throw new BizzRuntimeException("当前账户可提现金额不足");
        }
    }

}
