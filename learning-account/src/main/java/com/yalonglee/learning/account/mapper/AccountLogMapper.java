package com.yalonglee.learning.account.mapper;

import com.yalonglee.learning.account.mapper.base.AccountBaseMapper;
import com.yalonglee.learning.account.domain.AccountLogInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author
 */
@Repository
public interface AccountLogMapper extends AccountBaseMapper<AccountLogInfo> {

    /**
     * 批量插入账户变更记录
     *
     * @param accountLogInfoList
     */
    void batchInsert(List<AccountLogInfo> accountLogInfoList);

}
