package com.yalonglee.learning.account.utils.account;

import com.yalonglee.learning.account.exception.BizzRuntimeException;

import java.util.Objects;

/**
 * @author yalonglee
 */
public enum TransferType {

    IN(1, "转入"),

    OUT(2, "转出");

    private Integer key;

    private String value;

    TransferType(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public static TransferType getTransferTypeByKey(Integer key) {
        if (Objects.isNull(key)) {
            throw new BizzRuntimeException("枚举值不能为空！");
        }
        for (TransferType transactionType : TransferType.values()) {
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
