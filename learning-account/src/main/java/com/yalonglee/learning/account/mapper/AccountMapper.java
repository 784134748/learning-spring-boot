package com.yalonglee.learning.account.mapper;

import com.yalonglee.learning.account.mapper.base.AccountBaseMapper;
import com.yalonglee.learning.account.model.AccountModel;
import com.yalonglee.learning.account.utils.account.AccountInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * @author
 */
@Repository
public interface AccountMapper extends AccountBaseMapper<AccountModel> {

    /**
     * 通过时间戳限定当前记录锁所能操作的数据集（数据集为时间戳之前创建的账户记录）
     *
     * @param accountAddr
     */
    @Update("update account set timestamp_lock = current_timestamp where account_addr = #{accountAddr}")
    void limitAccountRecordOperationScopeByTimestampLock(@Param("accountAddr") String accountAddr);

    /**
     * 获取记录锁（行锁）
     *
     * @param accountAddr
     * @return
     */
    @Select("select account.id, account.timestamp_lock as account_timestamp_lock, current_timestamp as account_current_timestamp, account.wait_withdraw_cashes_amount as account_wait_withdraw_cashes_amount from account where account.id = (select account.id from account where account_addr = #{accountAddr}) for update")
    AccountInfo getRecordLock(@Param("accountAddr") String accountAddr);

    /**
     * 根据账户地址更新待提现金额
     *
     * @param accountAddr
     * @param waitWithdrawCashesAmount
     */
    @Update("update account set wait_withdraw_cashes_amount = #{waitWithdrawCashesAmount} where account.account_addr = #{accountAddr}")
    void updateWaitWithdrawCashesAmountByAccountAddr(@Param("accountAddr") String accountAddr, @Param("waitWithdrawCashesAmount") Double waitWithdrawCashesAmount);

    /**
     * 根据账户地址查询账户信息
     *
     * @param accountAddr
     * @return
     */
    @Select("select\n" +
            "account.account_addr,\n" +
            "account.wait_withdraw_cashes_amount as account_wait_withdraw_cashes_amount,\n" +
            "account.timestamp_lock as account_timestamp_lock,\n" +
            "current_timestamp as account_current_timestamp,\n" +
            "sum(account_record.available_amount) as account_total_available_amount,\n" +
            "sum(account_record.frozen_amount) as account_total_frozen_amount\n" +
            "from account, account_record\n" +
            "where\n" +
            "account.account_addr = #{accountAddr}\n" +
            "and account_record.account_addr = account.account_addr\n" +
            "group by account.wait_withdraw_cashes_amount, account.timestamp_lock")
    AccountInfo getAccountInfoByAccountAddr(@Param("accountAddr") String accountAddr);


}
