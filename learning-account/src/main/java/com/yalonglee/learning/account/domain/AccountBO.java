package com.yalonglee.learning.account.domain;

import com.yalonglee.learning.account.exception.BizzRuntimeException;
import com.yalonglee.learning.account.mapper.AccountLogMapper;
import com.yalonglee.learning.account.mapper.AccountMapper;
import com.yalonglee.learning.account.mapper.AccountRecordMapper;
import com.yalonglee.learning.account.model.AccountModel;
import com.yalonglee.learning.account.model.AccountRecordModel;
import com.yalonglee.learning.account.utils.account.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.EnumMap;
import java.util.List;

/**
 * @author yalonglee
 */
@Component
@Slf4j
public class AccountBO {

    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private AccountRecordMapper accountRecordMapper;
    @Autowired
    private AccountLogMapper accountLogMapper;

    /**
     * 创建账户
     *
     * @param accountAddr 账户地址
     * @return
     */
    public void create(String accountAddr) {
        AccountModel account = accountMapper.selectOneByQuery(AccountModel.builder()
                .accountAddr(accountAddr)
                .build());
        if (account != null) {
            throw new BizzRuntimeException("账户地址已存在！");
        }
        account = AccountModel.builder()
                .accountAddr(accountAddr)
                .build();
        accountMapper.insert(account);
    }

    /**
     * 释放账户
     *
     * @param accountAddr
     */
    public void delete(String accountAddr) {
        AccountModel account = accountMapper.selectOneByQuery(AccountModel.builder()
                .accountAddr(accountAddr)
                .build());
        if (account == null) {
            throw new BizzRuntimeException("账户地址不存在！");
        }
        accountMapper.deleteByPrimaryKey(account.getId());
    }

    /**
     * 账户充值
     * 事务隔离级别：已提交读（Read committed）
     *
     * @param depositParam
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void deposit(DepositParam depositParam) {
        EnumMap<AccountOperation, Object> operation = AccountUtil.deposit(depositParam);
        this.operationBD(operation);
        log.info("流水号：{},向账户地址为 {} 的账户充值金额 {}", depositParam.getSerialNumber(), depositParam.getTargetAccountAddr(), depositParam.getTotalDepositAmount());
    }

    /**
     * 账户转账
     * 事务隔离级别：已提交读（Read committed）
     *
     * @param transferParam
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void transfer(TransferParam transferParam) {
        this.lockAccountBeforeTransfer(transferParam.getSourceAccountAddr(), transferParam.getTotalTransferAmount());
        //完成转账的账户记录集合
        List<CompleteTransferAccountRecordInfo> completeTransferAccountRecordInfoList = accountRecordMapper.queryCompleteTransferAccountRecordInfoList(transferParam.getSourceAccountAddr(), transferParam.getTotalTransferAmount());
        transferParam.setCompleteTransferAccountRecordInfoList(completeTransferAccountRecordInfoList);
        //转账
        EnumMap<AccountOperation, Object> operation = AccountUtil.transfer(transferParam);
        this.operationBD(operation);
        log.info("流水号：{},账户地址为 {} 的账户完成转账金额 {}", transferParam.getSerialNumber(), transferParam.getSourceAccountAddr(), transferParam.getTotalTransferAmount());
    }

    /**
     * 账户提现
     * 事务隔离级别：已提交读（Read committed）
     *
     * @param withdrawParam
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void withdraw(WithdrawParam withdrawParam) {
        //判断账户待提现金额
        AccountInfo lockedAccount = this.lockedAccountWaitWithdrawCashesAmount(withdrawParam.getSourceAccountAddr(), withdrawParam.getTotalWithdrawAmount());
        //完成提现的账户记录集合
        List<CompleteTransferAccountRecordInfo> completeTransferAccountRecordInfoList = accountRecordMapper.queryCompleteTransferAccountRecordInfoList(withdrawParam.getSourceAccountAddr(), withdrawParam.getTotalWithdrawAmount());
        withdrawParam.setCompleteTransferAccountRecordInfoList(completeTransferAccountRecordInfoList);
        //提现
        EnumMap<AccountOperation, Object> operation = AccountUtil.withdraw(withdrawParam);
        this.operationBD(operation);
        this.cancelWithdrawCashes(withdrawParam);
    }

    /**
     * 提现开始前锁定金额
     * 事务隔离级别：已提交读（Read committed）
     *
     * @param withdrawParam
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void beforeWithdrawCashes(WithdrawParam withdrawParam) {
        //判断账户可提现金额是否满足条件（锁定状态下）
        AccountInfo account = this.lockedAccountAvailableWithdrawCashesAmount(withdrawParam.getSourceAccountAddr(), withdrawParam.getTotalWithdrawAmount());
        //更新待提现金额
        double updateWaitWithdrawCashes = account.getAccountWaitWithdrawCashesAmount() + withdrawParam.getTotalWithdrawAmount();
        accountMapper.updateWaitWithdrawCashesAmountByAccountAddr(withdrawParam.getSourceAccountAddr(), updateWaitWithdrawCashes);
        log.info("流水号：{},锁定账户地址为 {} 的账户待提现金额 {}", withdrawParam.getSerialNumber(), withdrawParam.getSourceAccountAddr(), withdrawParam.getTotalWithdrawAmount());
    }

    /**
     * 终止提现后释放金额
     * 事务隔离级别：已提交读（Read committed）
     *
     * @param withdrawParam
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void cancelWithdrawCashes(WithdrawParam withdrawParam) {
        //判断账户待提现金额是否满足条件（锁定状态下）
        AccountInfo lockedAccount = this.lockedAccountWaitWithdrawCashesAmount(withdrawParam.getSourceAccountAddr(), withdrawParam.getTotalWithdrawAmount());
        //更新待提现金额
        double updateWaitWithdrawCashes = lockedAccount.getAccountWaitWithdrawCashesAmount() - withdrawParam.getTotalWithdrawAmount();
        accountMapper.updateWaitWithdrawCashesAmountByAccountAddr(withdrawParam.getSourceAccountAddr(), updateWaitWithdrawCashes);
        log.info("流水号：{},释放账户地址为 {} 的账户待提现金额 {}", withdrawParam.getSerialNumber(), withdrawParam.getSourceAccountAddr(), withdrawParam.getTotalWithdrawAmount());
    }

    /**
     * 开始转账前锁定账户
     *
     * @param accountAddr
     * @param totalTransferAmount
     * @return
     * @throws BizzRuntimeException
     */
    private AccountInfo lockAccountBeforeTransfer(String accountAddr, Double totalTransferAmount) {
        return lockedAccountAvailableTransferAmount(accountAddr, totalTransferAmount);
    }

    /**
     * 判断账户可转账余额是否满足条件（非锁定状态下)
     *
     * @param accountAddr
     * @param paymentAmount
     */
    private AccountInfo accountAvailableTransferAmount(String accountAddr, Double paymentAmount) {
        AccountInfo account = accountMapper.getAccountInfoByAccountAddr(accountAddr);
        //检查账户可转账余额
        AccountCheck.checkAccountAvailableTransferAmount(account, paymentAmount);
        return account;
    }

    /**
     * 判断账户可转账余额是否满足条件（锁定状态下）
     *
     * @param accountAddr
     */
    private AccountInfo lockedAccountAvailableTransferAmount(String accountAddr, Double paymentAmount) {
        //不锁表情况下判断账户余额是否满足支付条件
        this.accountAvailableTransferAmount(accountAddr, paymentAmount);
        //锁表
        getAccountLock(accountAddr);
        AccountInfo lockedAccount = accountMapper.getAccountInfoByAccountAddr(accountAddr);
        //检查账户可转账余额
        AccountCheck.checkAccountAvailableTransferAmount(lockedAccount, paymentAmount);
        return lockedAccount;
    }

    /**
     * 判断账户可提现金额是否满足条件（非锁定状态下）
     *
     * @param accountAddr
     * @param withdrawCashesAmount
     */
    private AccountInfo accountAvailableWithdrawCashesAmount(String accountAddr, Double withdrawCashesAmount) {
        AccountInfo account = accountMapper.getAccountInfoByAccountAddr(accountAddr);
        //检查账户可提现金额
        AccountCheck.checkAccountAvailableWithdrawCashesAmount(account, withdrawCashesAmount);
        return account;
    }

    /**
     * 判断账户可提现金额是否满足条件（锁定状态下）
     *
     * @param accountAddr
     */
    private AccountInfo lockedAccountAvailableWithdrawCashesAmount(String accountAddr, Double withdrawCashesAmount) {
        //不锁表情况下判断可提现金额是否满足条件
        this.accountAvailableWithdrawCashesAmount(accountAddr, withdrawCashesAmount);
        //锁表
        getAccountLock(accountAddr);
        AccountInfo lockedAccount = accountMapper.getAccountInfoByAccountAddr(accountAddr);
        //锁表情况下判断可提现金额是否满足条件
        AccountCheck.checkAccountAvailableWithdrawCashesAmount(lockedAccount, withdrawCashesAmount);
        return lockedAccount;
    }

    /**
     * 判断账户待提现金额是否满足条件（锁定状态下）
     *
     * @param accountAddr
     */
    private AccountInfo lockedAccountWaitWithdrawCashesAmount(String accountAddr, Double withdrawCashesAmount) {
        //锁表
        AccountInfo lockedAccount = getAccountLock(accountAddr);
        //锁表情况下检查账户待提现金额是否满足条件
        AccountCheck.checkAccountWaitWithdrawCashesAmount(lockedAccount, withdrawCashesAmount);
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
        AccountCheck.checkAccountAvailable(account);
        //通过时间戳限定锁所能操作的数据集的范围（数据集的创建时间必须小于设定的时间）
        accountMapper.limitAccountRecordOperationScopeByTimestampLock(accountAddr);
        return account;
    }

    /**
     * 对数据库的所有操作
     *
     * @param operation
     */
    private void operationBD(EnumMap<AccountOperation, Object> operation) {
        List<AccountRecordInfo> insertNewAccountRecordInfoList = (List<AccountRecordInfo>) operation.get(AccountOperation.INSERT_NEW_ACCOUNT_RECORD_INFO_LIST);
        List<Long> updateUseUpAccountRecordIds = (List<Long>) operation.get(AccountOperation.UPDATE_USE_UP_ACCOUNT_RECORD_IDS);
        List<AccountLogInfo> insertNewAccountLogInfoList = (List<AccountLogInfo>) operation.get(AccountOperation.INSERT_NEW_ACCOUNT_LOG_INFO_LIST);
        AccountRecordInfo updateLastAccountRecordInfo = (AccountRecordInfo) operation.get(AccountOperation.UPDATE_LAST_ACCOUNT_RECORD_INFO);

        if (insertNewAccountRecordInfoList != null && insertNewAccountRecordInfoList.size() > 0) {
            accountRecordMapper.batchInsert(insertNewAccountRecordInfoList);
        }
        if (updateUseUpAccountRecordIds != null && updateUseUpAccountRecordIds.size() > 0) {
            accountRecordMapper.batchUpdateAvailableAmountToZero(updateUseUpAccountRecordIds);
        }
        if (insertNewAccountLogInfoList != null && insertNewAccountLogInfoList.size() > 0) {
            accountLogMapper.batchInsert(insertNewAccountLogInfoList);
        }
        if (updateLastAccountRecordInfo != null) {
            accountRecordMapper.incUpdate(AccountRecordModel.builder()
                    .id(updateLastAccountRecordInfo.getId())
                    .index(updateLastAccountRecordInfo.getIndex())
                    .availableAmount(updateLastAccountRecordInfo.getAccountRecordAvailableAmount())
                    .build());
        }
    }
}
