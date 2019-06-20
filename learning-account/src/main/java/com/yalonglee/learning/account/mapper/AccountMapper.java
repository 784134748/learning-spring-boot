package com.yalonglee.learning.account.mapper;

import com.yalonglee.learning.account.domain.AccountDTO;
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
     * 通过时间戳限定锁所能操作的数据集的范围（数据集的创建时间必须小于设定的时间）
     *
     * @param accountAddr
     */
    @Update("update account set timestamp_lock = null where account_addr = #{accountAddr}")
    void limitAccountRecordOperationScopeByTimestampLock(@Param("accountAddr") String accountAddr);

    /**
     * 获取记录锁（行锁）
     *
     * @param accountAddr
     * @return
     */
    @Select("select account.id from account where account.id = (select account.id from account where account_addr = #{accountAddr}) for update")
    AccountDTO getRecordLocks(@Param("accountAddr") String accountAddr);

    /**
     * 根据账户ID更新待提现金额
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
    @Select("select account.account_addr, account.wait_withdraw_cashes_amount, account.timestamp_lock, account.md5, sum(account_record.available_amount) as total_available_amount, sum(aaccount_record.frozen_amount) as total_frozen_amount from account, aaccount_record where account.account_addr = #{accountAddr} and account_record.account_id = account.id")
    AccountDTO getAccountAmountByAccountAddr(@Param("accountAddr") String accountAddr);


}
