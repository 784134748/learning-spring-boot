package com.yalonglee.learning.account.utils.account;

import com.yalonglee.learning.account.exception.BizzRuntimeException;

import java.util.Objects;

/**
 * @author yalonglee
 */
public enum AccountOperationType {

    DEPOSIT(1, "充值"),

    TRANSFER(2, "转账"),

    WITHDRAW(3, "提现");

    private Integer key;

    private String value;

    AccountOperationType(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public static AccountOperationType getAccountOperationTypeByKey(Integer key) {
        if (Objects.isNull(key)) {
            throw new BizzRuntimeException("枚举值不能为空！");
        }
        for (AccountOperationType transactionType : AccountOperationType.values()) {
            if (transactionType.getKey().equals(key)) {
                return transactionType;
            }
        }
        throw new BizzRuntimeException("不存在该枚举值！");
    }

    public Integer getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

}
