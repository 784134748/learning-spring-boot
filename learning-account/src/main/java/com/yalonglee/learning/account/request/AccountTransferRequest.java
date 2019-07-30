package com.yalonglee.learning.account.request;

import com.yalonglee.learning.account.utils.account.TransferTarget;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author yalonglee
 */
@Data
@Builder
public class AccountTransferRequest {
    /**
     * 流水号
     */
    String serialNumber;
    /**
     * 转账出账账号地址
     */
    String sourceAccountAddr;
    /**
     * 转账金额
     */
    Double totalTransferAmount;
    /**
     * 入账账户地址
     */
    List<TransferTarget> transferTargetList;

}
