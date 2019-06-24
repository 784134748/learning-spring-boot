package com.yalonglee.learning.account.mapper;

import com.yalonglee.learning.account.utils.account.AccountInfo;
import com.yalonglee.learning.account.mapper.base.AccountBaseMapper;
import com.yalonglee.learning.account.model.AccountModel;
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
    @Select("select account.id from account where account.id = (select account.id from account where account_addr = #{accountAddr}) for update")
    AccountInfo getRecordLock(@Param("accountAddr") String accountAddr);

    /**
     * 根据账户id更新待提现金额
     *
     * @param id
     * @param waitWithdrawCashes
     */
    @Update("update account set wait_withdraw_cashes = #{waitWithdrawCashes} where id = #{id}")
    void updateWaitWithdrawCashes(@Param("id") Long id, @Param("waitWithdrawCashes") Double waitWithdrawCashes);

    /**
     * 根据账户地址查询账户金额
     *
     * @param accountAddr
     * @return
     */
    @Select("select account.account_addr, account.wait_withdraw_cashes_amount, account.timestamp_lock, current_timestamp, sum(account_record.available_amount) as total_available_amount, sum(account_record.frozen_amount) as total_frozen_amount from account, account_record where account.account_addr = #{accountAddr} and account_record.account_id = account.id")
    AccountInfo getAccountAmountByAccountAddr(@Param("accountAddr") String accountAddr);


}
