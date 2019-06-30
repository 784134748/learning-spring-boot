package com.yalonglee.learning.account.utils.account;

/**
 * @author yalonglee
 */
public enum AccountOperation {

    /**
     * 所有可用金额被耗尽的账户记录的ID（最后一条被消耗的账户记录除外）
     */
    UPDATE_USE_UP_ACCOUNT_RECORD_IDS,
    /**
     * 新增的账户变更记录
     */
    INSERT_NEW_ACCOUNT_LOG_INFO_LIST,
    /**
     * 新增的账户记录
     */
    INSERT_NEW_ACCOUNT_RECORD_INFO_LIST,
    /**
     * 更新最后一条账户记录
     */
    UPDATE_LAST_ACCOUNT_RECORD_INFO,
    /**
     * 新增的转账记录
     */
    INSERT_NEW_TRANSFER_INFO_LIST

}
