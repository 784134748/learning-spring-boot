package com.yalonglee.learning.account.utils.account;

import com.yalonglee.learning.account.exception.BizzRuntimeException;

/**
 * @author yalonglee
 */
public class AccountCheck {

    /**
     * 检查账户
     *
     * @param account
     */
    public static void checkAccountAvailable(AccountInfo account) {
        if (account == null) {
            throw new BizzRuntimeException("账户地址不存在");
        }
        if (account.getAccountTimestampLock() > account.getAccountCurrentTimestamp()) {
            throw new BizzRuntimeException("数据库时间异常");
        }
    }

    /**
     * 检查账户可转账余额
     *
     * @param account
     * @param transferAmount 判断账户可转账余额是否满足条件
     */
    public static void checkAccountAvailableTransferAmount(AccountInfo account, Double transferAmount) {
        checkAccountAvailable(account);
        if (transferAmount != null && account.getAccountAvailableTransferAmount() < transferAmount) {
            throw new BizzRuntimeException("当前账户可转账余额不足");
        }
    }

    /**
     * 检查账户可提现金额
     *
     * @param account
     * @param withdrawCashesAmount 判断账户可提现金额是否满足条件
     */
    public static void checkAccountAvailableWithdrawCashesAmount(AccountInfo account, Double withdrawCashesAmount) {
        checkAccountAvailable(account);
        if (withdrawCashesAmount != null && account.getAccountAvailableWithdrawCashesAmount() < withdrawCashesAmount) {
            throw new BizzRuntimeException("当前账户可提现金额不足");
        }
    }

    /**
     * 检查账户待提现余额
     *
     * @param account
     * @param withdrawCashesAmount 判断账户待提现金额是否满足条件
     */
    public static void checkAccountWaitWithdrawCashesAmount(AccountInfo account, Double withdrawCashesAmount) {
        checkAccountAvailable(account);
        if (withdrawCashesAmount != null && account.getAccountWaitWithdrawCashesAmount() < withdrawCashesAmount) {
            throw new BizzRuntimeException("当前账户待提现金额不足");
        }
    }

}
