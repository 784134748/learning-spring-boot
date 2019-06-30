package com.yalonglee.learning.account.mapper;

import com.yalonglee.learning.account.model.AccountRecordModel;
import com.yalonglee.learning.account.utils.account.CompleteTransferAccountRecordInfo;
import com.yalonglee.learning.account.mapper.base.AccountBaseMapper;
import com.yalonglee.learning.account.utils.account.AccountRecordInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author
 */
@Repository
public interface AccountRecordMapper extends AccountBaseMapper<AccountRecordModel> {

    /**
     * 获取能够完成本次交易的账户记录列表
     *
     * @param accountAddr
     * @param amount
     * @return
     */
    List<CompleteTransferAccountRecordInfo> queryCompleteTransferAccountRecordInfoList(@Param("accountAddr") String accountAddr, @Param("amount") Double amount);

    /**
     * 批量插入账户记录
     *
     * @param accountRecordInfoList
     */
    void batchInsert(List<AccountRecordInfo> accountRecordInfoList);

    /**
     * 批量更新账户记录可用金额为0
     *
     * @param ids 待更新记录的id
     */
    void batchUpdateAvailableAmountToZero(@Param("ids") List<Long> ids);

}
