package com.yalonglee.learning.account.mapper;

import com.yalonglee.learning.account.utils.account.PaymentDTO;
import com.yalonglee.learning.account.mapper.base.AccountBaseMapper;
import com.yalonglee.learning.account.utils.account.AccountRecordInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author
 */
@Repository
public interface AccountRecordMapper extends AccountBaseMapper<AccountRecordInfo> {

    /**
     * 获取转账消耗的的账户记录
     *
     * @param accountAddr
     * @param amount
     * @return
     */
    List<PaymentDTO> payment(@Param("accountAddr") String accountAddr, @Param("amount") Double amount);

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
    void batchUpdateAmount(@Param("ids") List<Long> ids);

}
