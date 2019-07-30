package com.yalonglee.learning.account;

import com.google.common.collect.Lists;
import com.yalonglee.learning.account.domain.AccountBO;
import com.yalonglee.learning.account.utils.account.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public class PaymentAccountTest extends LearningAccountApplicationTests {

    @Autowired
    AccountBO account;

    @Test
    public void account() {

        String accountAddr = "yalonglee4";
//        account.create(accountAddr);
//
//        account.deposit(AccountDepositRequest.builder()
//                .serialNumber("123456890")
//                .targetAccountAddr(accountAddr)
//                .totalDepositAmount(10.00D)
//                .build());
//
//        account.beforeWithdrawCashes(AccountWithdrawRequest.builder()
//                .serialNumber("1234567891")
//                .sourceAccountAddr(accountAddr)
//                .totalWithdrawAmount(1.00D)
//                .build());
//
//        account.withdraw(AccountWithdrawRequest.builder()
//                .serialNumber("1234567891")
//                .sourceAccountAddr(accountAddr)
//                .totalWithdrawAmount(10.00D)
//                .build());
//
//        account.cancelWithdrawCashes(AccountWithdrawRequest.builder()
//                .serialNumber("1234567891")
//                .sourceAccountAddr(accountAddr)
//                .totalWithdrawAmount(1.00D)
//                .build());
//
//        List<TransferTarget> transferTargetList = Lists.newArrayList();
//        transferTargetList.add(TransferTarget.builder().accountAddr("yalonglee").transferAmount(1.00D).build());
//        transferTargetList.add(TransferTarget.builder().accountAddr("yalonglee1").transferAmount(2.00D).build());
//        account.transfer(AccountTransferRequest.builder()
//                .serialNumber("1234567893")
//                .sourceAccountAddr(accountAddr)
//                .totalTransferAmount(3.00D)
//                .transferTargetList(transferTargetList)
//                .build());
    }

}
