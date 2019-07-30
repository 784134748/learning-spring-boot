package com.yalonglee.learning.account.utils.account;

import com.google.common.collect.Lists;
import com.yalonglee.learning.account.exception.BizzRuntimeException;
import com.yalonglee.learning.account.utils.snowflake.SnowflakeIdWorker;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author yalonglee
 */
@Slf4j
@Component
public class AccountUtil {

    /**
     * 雪花算法
     */
    private static SnowflakeIdWorker autowiredSnowflakeIdWorker = new SnowflakeIdWorker();
    /**
     * 系统默认账户
     */
    private static final String SYSTEM_ACCOUNT_ADDR = "system_account_addr";
    /**
     * 系统默认金额
     */
    private static final double SYSTEM_DEFINE_AMOUNT_ZERO = 0.00D;
    /**
     * 账户记录默认变更次数
     */
    private static final int SYSTEM_INDEX = 0;
    /**
     * 账户记录默认起始行号
     */
    private static final int START_ACCOUNT_RECORD_NO = 0;

    /**
     * 充值操作
     *
     * @return
     */
    public static EnumMap<AccountOperation, Object> deposit(DepositParam depositParam) {

        //参数校验
        checkDespiteParams(depositParam);

        List<CompleteTransferAccountRecordInfo> completeTransferAccountRecordInfoList = Lists.newArrayList();
        completeTransferAccountRecordInfoList.add(CompleteTransferAccountRecordInfo.builder()
                .index(AccountUtil.SYSTEM_INDEX)
                .accountRecordRowNo(AccountUtil.START_ACCOUNT_RECORD_NO)
                .sumAccountRecordAvailableAmount(depositParam.getTotalDepositAmount())
                .accountAddr(AccountUtil.SYSTEM_ACCOUNT_ADDR).accountRecordId(0L)
                .accountRecordAvailableAmount(depositParam.getTotalDepositAmount())
                .accountRecordFrozenAmount(AccountUtil.SYSTEM_DEFINE_AMOUNT_ZERO)
                .build());

        List<TransferTarget> transferTargetList = Lists.newArrayList();
        transferTargetList.add(TransferTarget.builder().accountAddr(depositParam.getTargetAccountAddr()).transferAmount(depositParam.getTotalDepositAmount()).build());

        return oneToManyTransfer(OneToManyTransferParam.builder()
                .serialNumber(depositParam.getSerialNumber())
                .sourceAccountAddr(AccountUtil.SYSTEM_ACCOUNT_ADDR)
                .accountOperationType(AccountOperationType.DEPOSIT.getKey())
                .totalTransferAmount(depositParam.getTotalDepositAmount())
                .completeTransferAccountRecordInfoList(completeTransferAccountRecordInfoList)
                .transferTargetList(transferTargetList)
                .build());

    }

    /**
     * 转账操作
     *
     * @return
     */
    public static EnumMap<AccountOperation, Object> transfer(TransferParam transferParam) {

        //参数校验
        checkTransferParams(transferParam);

        return oneToManyTransfer(OneToManyTransferParam.builder()
                .serialNumber(transferParam.getSerialNumber())
                .sourceAccountAddr(transferParam.getSourceAccountAddr())
                .accountOperationType(AccountOperationType.TRANSFER.getKey())
                .totalTransferAmount(transferParam.getTotalTransferAmount())
                .completeTransferAccountRecordInfoList(transferParam.getCompleteTransferAccountRecordInfoList())
                .transferTargetList(transferParam.getTransferTargetList())
                .build());
    }

    /**
     * 提现操作
     *
     * @return
     */
    public static EnumMap<AccountOperation, Object> withdraw(WithdrawParam withdrawParam) {
        //参数校验
        checkWithdrawParams(withdrawParam);

        List<TransferTarget> transferTargetList = Lists.newArrayList();
        transferTargetList.add(TransferTarget.builder().accountAddr(AccountUtil.SYSTEM_ACCOUNT_ADDR).transferAmount(withdrawParam.getTotalWithdrawAmount()).build());

        return oneToManyTransfer(OneToManyTransferParam.builder()
                .serialNumber(withdrawParam.getSerialNumber())
                .sourceAccountAddr(withdrawParam.getSourceAccountAddr())
                .accountOperationType(AccountOperationType.WITHDRAW.getKey())
                .totalTransferAmount(withdrawParam.getTotalWithdrawAmount())
                .completeTransferAccountRecordInfoList(withdrawParam.getCompleteTransferAccountRecordInfoList())
                .transferTargetList(transferTargetList)
                .build());
    }

    /**
     * 校验充值参数
     */
    private static void checkDespiteParams(DepositParam depositParam) {
        final double zero = 0.00D;

        if (StringUtils.isBlank(depositParam.getSerialNumber())) {
            throw new BizzRuntimeException("流水号不能为空");
        }
        if (StringUtils.isBlank(depositParam.getTargetAccountAddr())) {
            throw new BizzRuntimeException("充值账户异常");
        }
        if (depositParam.getTotalDepositAmount() <= zero) {
            throw new BizzRuntimeException("充值金额必须大于0.00");
        }
    }

    /**
     * 校验转账参数
     */
    private static void checkTransferParams(TransferParam transferParam) {
        final double zero = 0.00D;

        if (StringUtils.isBlank(transferParam.getSerialNumber())) {
            throw new BizzRuntimeException("流水号不能为空");
        }
        if (StringUtils.isBlank(transferParam.getSourceAccountAddr())) {
            throw new BizzRuntimeException("转账账户异常");
        }
        if (transferParam.getTotalTransferAmount() <= zero) {
            throw new BizzRuntimeException("转账金额必须大于0.00");
        }
        if (transferParam.getCompleteTransferAccountRecordInfoList() == null || transferParam.getCompleteTransferAccountRecordInfoList().isEmpty()) {
            throw new BizzRuntimeException("未获取到任何可支付的账户记录");
        }
        if (transferParam.transferTargetList == null || transferParam.getTransferTargetList().isEmpty()) {
            throw new BizzRuntimeException("未获取到任何入账账户地址");
        }

        //本次转账涉及到的账户记录进行升序排序
        transferParam.getCompleteTransferAccountRecordInfoList().stream()
                .filter(completeTransferAccountRecordInfo -> completeTransferAccountRecordInfo.getSumAccountRecordAvailableAmount() - completeTransferAccountRecordInfo.getAccountRecordAvailableAmount() < transferParam.getTotalTransferAmount())
                .sorted(Comparator.comparing(CompleteTransferAccountRecordInfo::getSumAccountRecordAvailableAmount))
                .collect(Collectors.toList());
    }

    /**
     * 校验提现参数
     */
    private static void checkWithdrawParams(WithdrawParam withdrawParam) {
        final double zero = 0.00D;

        if (StringUtils.isBlank(withdrawParam.getSerialNumber())) {
            throw new BizzRuntimeException("流水号不能为空");
        }
        if (StringUtils.isBlank(withdrawParam.getSourceAccountAddr())) {
            throw new BizzRuntimeException("提现账户异常");
        }
        if (withdrawParam.getTotalWithdrawAmount() <= zero) {
            throw new BizzRuntimeException("提现金额必须大于0.00");
        }
        if (withdrawParam.getCompleteTransferAccountRecordInfoList() == null || withdrawParam.getCompleteTransferAccountRecordInfoList().isEmpty()) {
            throw new BizzRuntimeException("未获取到任何可提现的账户记录");
        }

        //本次转账涉及到的账户记录进行升序排序
        withdrawParam.getCompleteTransferAccountRecordInfoList().stream()
                .filter(completeTransferAccountRecordInfo -> completeTransferAccountRecordInfo.getSumAccountRecordAvailableAmount() - completeTransferAccountRecordInfo.getAccountRecordAvailableAmount() < withdrawParam.getTotalWithdrawAmount())
                .sorted(Comparator.comparing(CompleteTransferAccountRecordInfo::getSumAccountRecordAvailableAmount))
                .collect(Collectors.toList());
    }

    /**
     * 转账金额分割逻辑
     */
    private static EnumMap<AccountOperation, Object> oneToManyTransfer(OneToManyTransferParam oneToManyTransferParam) {

        final String serialNumber = oneToManyTransferParam.getSerialNumber();
        final String sourceAccountAddr = oneToManyTransferParam.getSourceAccountAddr();
        final Integer accountOperationType = oneToManyTransferParam.getAccountOperationType();
        final Double totalTransferAmount = oneToManyTransferParam.getTotalTransferAmount();
        final List<CompleteTransferAccountRecordInfo> completeTransferAccountRecordInfoList = oneToManyTransferParam.getCompleteTransferAccountRecordInfoList();
        final List<TransferTarget> transferTargetList = oneToManyTransferParam.getTransferTargetList();

        log.info("流水号：{},进入一对多转账的方法: 出账账户地址【{}】 - 出账总金额【{}】", serialNumber, sourceAccountAddr, totalTransferAmount);

        //转账总金额
        final double total = totalTransferAmount;
        //累计已转账金额
        double already = AccountUtil.SYSTEM_DEFINE_AMOUNT_ZERO;

        //被消耗的最后一条账户记录
        AccountRecordInfo lastAccountRecord = null;
        //所有可用金额被耗尽的账户记录的ID（最后一条被消耗的账户记录除外）
        List<UseUpAccountRecordInfo> updateUseUpAccountRecordInfoList = Lists.newArrayList();
        //新增的账户记录
        List<AccountRecordInfo> insertNewAccountRecordInfoList = Lists.newArrayList();
        //新增的转账记录
        List<TransferInfo> insertNewTransferInfoList = Lists.newArrayList();
        //新增的账户变更记录
        List<AccountLogInfo> insertNewAccountLogInfoList = Lists.newArrayList();

        //支付记录倒序排序
        completeTransferAccountRecordInfoList.stream()
                .sorted(Comparator.comparing(CompleteTransferAccountRecordInfo::getSumAccountRecordAvailableAmount))
                .collect(Collectors.toList());
        Collections.reverse(completeTransferAccountRecordInfoList);
        //获取支付记录的迭代器
        Iterator<CompleteTransferAccountRecordInfo> iterator = completeTransferAccountRecordInfoList.listIterator();
        //迭代器当前位置的paymentDTO
        CompleteTransferAccountRecordInfo payment = CompleteTransferAccountRecordInfo.builder().build();
        //迭代器是否继续迭代(当前账户记录中的可用金额是否被消耗殆尽)
        boolean isUseUp = true;
        //账户记录变更的次数
        int index = 0;

        for (TransferTarget transferTarget : transferTargetList) {
            //是否已发现本次转账涉及的起点账户记录
            boolean isFindStart = false;
            //是否已发现本次转账涉及的终点账户记录
            boolean isFindEnd = false;
            //涉及本次转账的账户记录
            List<CompleteTransferAccountRecordInfo> accountRecordInfoList = Lists.newArrayList();
            do {
                //上一条账户记录被耗尽，取下一条账户记录
                if (isUseUp) {
                    payment = iterator.next();
                    index = payment.getIndex();
                }
                //循环一次，账户记录变更次数递增1
                index += 1;
                /**
                 * A:本次转账涉及的起点账户记录累计金额减去可用金额
                 * B:累计已转账金额
                 * C:本次转账涉及的起点账户记录累计金额
                 * D:本次转账涉及的终点账户记录累计金额减去可用金额
                 * E:本次转账完成后的累计已转账金额
                 * F:本次转账涉及的终点账户记录累计金额
                 */
                //A、B、C、D、E、F值之间的关系，[A小于等于B],[B小于C],[D小于E],[E小于等于F]
                //获取值大于等于零的首间距
                if (!isFindStart) {
                    double valueA = payment.getSumAccountRecordAvailableAmount() - payment.getAccountRecordAvailableAmount();
                    double valueB = already;
                    double valueC = payment.getSumAccountRecordAvailableAmount();
                    if ((valueA <= valueB) && (valueB < valueC)) {
                        isFindStart = true;
                    } else {
                        throw new BizzRuntimeException("获取账户记录异常！");
                    }
                }
                //统计涉及本次转账的账户记录(从发现开始节点开始统计，直至发现结束节点)
                if (isFindStart && (!isFindEnd)) {
                    accountRecordInfoList.add(payment);
                }
                //统计所有可用金额被耗尽的账户记录的ID
                if (iterator.hasNext() && isUseUp) {
                    updateUseUpAccountRecordInfoList.add(UseUpAccountRecordInfo.builder()
                            .accountRecordId(payment.getAccountRecordId())
                            .index(index)
                            .build());
                }
                //统计被消耗的最后一条账户记录（没有下一条记录，且该对象只被赋值一次）
                if ((!iterator.hasNext()) && lastAccountRecord == null) {
                    AccountRecordInfo accountRecordTmp = AccountRecordInfo.builder()
                            .id(payment.getAccountRecordId())
                            .accountRecordAvailableAmount(payment.getSumAccountRecordAvailableAmount() - total)
                            .index(index)
                            .build();
                    lastAccountRecord = accountRecordTmp;
                }
                //更新账户记录的变更次数
                if (lastAccountRecord != null && index > lastAccountRecord.getIndex()) {
                    lastAccountRecord.setIndex(index);
                }
                //获取值大于等于零的尾间距
                if (!isFindEnd) {
                    double valueD = payment.getSumAccountRecordAvailableAmount() - payment.getAccountRecordAvailableAmount();
                    double valueE = already + transferTarget.getTransferAmount();
                    double valueF = payment.getSumAccountRecordAvailableAmount();
                    if ((valueD < valueE) && (valueE <= valueF)) {
                        isFindEnd = true;
                    }
                }
            } while (iterator.hasNext());
            //未匹配可完成转账金额的账户记录
            if (!(isFindStart && isFindEnd)) {
                throw new BizzRuntimeException("未匹配上可完成该笔转账金额的账户记录");
            }
            //一对一转账
            oneToOneTransfer(OneToOneTransferParam.builder()
                    .serialNumber(serialNumber)
                    .sourceAccountAddr(sourceAccountAddr)
                    .accountOperationType(accountOperationType)
                    .completeTransferAccountRecordInfoList(accountRecordInfoList)
                    .transferTarget(transferTarget)
                    .insertNewAccountRecordInfoList(insertNewAccountRecordInfoList)
                    .insertNewTransferInfoList(insertNewTransferInfoList)
                    .insertNewAccountLogInfoList(insertNewAccountLogInfoList)
                    .build());
            //更新已转账金额
            already += transferTarget.getTransferAmount();
            //迭代器是否继续迭代(当前账户记录中的可用金额是否被消耗殆尽)
            if (already == payment.getSumAccountRecordAvailableAmount()) {
                isUseUp = true;
            } else {
                isUseUp = false;
            }
        }

        EnumMap<AccountOperation, Object> operation = new EnumMap<>(AccountOperation.class);
        operation.put(AccountOperation.UPDATE_USE_UP_ACCOUNT_RECORD_IDS, updateUseUpAccountRecordInfoList);
        operation.put(AccountOperation.INSERT_NEW_ACCOUNT_LOG_INFO_LIST, insertNewAccountLogInfoList);
        operation.put(AccountOperation.INSERT_NEW_ACCOUNT_RECORD_INFO_LIST, insertNewAccountRecordInfoList);
        operation.put(AccountOperation.INSERT_NEW_TRANSFER_INFO_LIST, insertNewTransferInfoList);
        operation.put(AccountOperation.UPDATE_LAST_ACCOUNT_RECORD_INFO, lastAccountRecord);

        return operation;
    }

    /**
     * 转账记录的生成逻辑
     */
    private static void oneToOneTransfer(OneToOneTransferParam oneToOneTransferParam) {
        final String serialNumber = oneToOneTransferParam.getSerialNumber();
        final String sourceAccountAddr = oneToOneTransferParam.getSourceAccountAddr();
        final Integer accountOperationType = oneToOneTransferParam.getAccountOperationType();
        final List<CompleteTransferAccountRecordInfo> completeTransferAccountRecordInfoList = oneToOneTransferParam.getCompleteTransferAccountRecordInfoList();
        final TransferTarget transferTarget = oneToOneTransferParam.getTransferTarget();
        List<AccountRecordInfo> insertNewAccountRecordInfoList = oneToOneTransferParam.getInsertNewAccountRecordInfoList();
        List<TransferInfo> insertNewTransferInfoList = oneToOneTransferParam.getInsertNewTransferInfoList();
        List<AccountLogInfo> insertNewAccountLogInfoList = oneToOneTransferParam.getInsertNewAccountLogInfoList();

        log.info("流水号：{},进入顺序转账方法: 入账账户地址【{}】 - 入账总金额【{}】", serialNumber, transferTarget.getAccountAddr(), transferTarget.getTransferAmount());
        //入账账户地址
        String targetAccountAddr = transferTarget.getAccountAddr();
        //转账总金额
        final double totalTransferAmount = transferTarget.getTransferAmount();
        //累计已转账金额
        double alreadyTransferAmount = AccountUtil.SYSTEM_DEFINE_AMOUNT_ZERO;
        for (CompleteTransferAccountRecordInfo completeTransferAccountRecordInfo : completeTransferAccountRecordInfoList) {
            //剩余待转金额
            double leftTransferAmount = totalTransferAmount - alreadyTransferAmount;
            //来源账户记录id
            long fromAccountRecordId = completeTransferAccountRecordInfo.getAccountRecordId();
            //新产生账户记录的id
            long accountRecordId = autowiredSnowflakeIdWorker.genId();
            //来源账户记录变更次数
            int sourceAccountRecordIndex = completeTransferAccountRecordInfo.getIndex();
            //出账账户记录变更次数
            int newSourceAccountRecordIndex = sourceAccountRecordIndex + 1;
            //入账账户记录变更次数
            int newTargetAccountRecordIndex = AccountUtil.SYSTEM_INDEX;
            //冻结的金额
            double frozenAmount = completeTransferAccountRecordInfo.getAccountRecordFrozenAmount();
            //支付记录剩余的可用金额
            double leftAvailableAmount = completeTransferAccountRecordInfo.getAccountRecordAvailableAmount() - leftTransferAmount;
            //支付记录可用金额被耗尽
            if (leftAvailableAmount < AccountUtil.SYSTEM_DEFINE_AMOUNT_ZERO) {
                leftAvailableAmount = AccountUtil.SYSTEM_DEFINE_AMOUNT_ZERO;
            }
            //支付记录消耗的金额
            double payAvailableAmount = completeTransferAccountRecordInfo.getAccountRecordAvailableAmount() - leftAvailableAmount;
            alreadyTransferAmount += payAvailableAmount;

            log.info("流水号：{},支付记录: 支付记录id【{}】 - 支付记录的可用金额【{}】 - 支付记录消耗的可用金额【{}】", serialNumber, accountRecordId, payAvailableAmount);
            //生成新的账户记录
            AccountRecordInfo newAccountRecordInfo = AccountRecordInfo.builder()
                    .id(accountRecordId)
                    .serialNumber(serialNumber)
                    .accountRecordOperationType(accountOperationType)
                    .sourceAccountAddr(sourceAccountAddr)
                    .accountAddr(targetAccountAddr)
                    .index(AccountUtil.SYSTEM_INDEX)
                    .accountRecordAvailableAmount(completeTransferAccountRecordInfo.getAccountRecordAvailableAmount() - leftAvailableAmount)
                    .accountRecordFrozenAmount(AccountUtil.SYSTEM_DEFINE_AMOUNT_ZERO)
                    .build();
            insertNewAccountRecordInfoList.add(newAccountRecordInfo);
            //生成账户变更记录(入账账户记录和出账账户记录各一条)
            AccountLogInfo newSourceAccountLogInfo = AccountLogInfo.builder()
                    .id(autowiredSnowflakeIdWorker.genId())
                    .serialNumber(serialNumber)
                    .sourceAccountRecordId(fromAccountRecordId)
                    .accountOperationType(accountOperationType)
                    .sourceAccountRecordIndex(sourceAccountRecordIndex)
                    .accountRecordId(fromAccountRecordId)
                    .accountAddr(sourceAccountAddr)
                    .accountRecordIndex(newSourceAccountRecordIndex)
                    .availableAmount(leftAvailableAmount)
                    .frozenAmount(frozenAmount)
                    .build();
            AccountLogInfo newTargetAccountLogInfo = AccountLogInfo.builder()
                    .id(autowiredSnowflakeIdWorker.genId())
                    .serialNumber(serialNumber)
                    .sourceAccountRecordId(fromAccountRecordId)
                    .accountOperationType(accountOperationType)
                    .sourceAccountRecordIndex(sourceAccountRecordIndex)
                    .accountAddr(targetAccountAddr)
                    .accountRecordId(accountRecordId)
                    .accountRecordIndex(newTargetAccountRecordIndex)
                    .availableAmount(payAvailableAmount)
                    .frozenAmount(frozenAmount)
                    .build();
            insertNewAccountLogInfoList.add(newSourceAccountLogInfo);
            insertNewAccountLogInfoList.add(newTargetAccountLogInfo);
            //完成转账
            if (leftAvailableAmount >= AccountUtil.SYSTEM_DEFINE_AMOUNT_ZERO) {
                //生成转账记录(入账账户记录和出账账户记录各一条)
                TransferInfo newSourceTransferInfo = TransferInfo.builder()
                        .id(autowiredSnowflakeIdWorker.genId())
                        .serialNumber(serialNumber)
                        .accountOperationType(accountOperationType)
                        .amount(totalTransferAmount)
                        .sourceAccountAddr(sourceAccountAddr)
                        .targetAccountAddr(targetAccountAddr)
                        .accountAddr(sourceAccountAddr)
                        .transferType(TransferType.OUT.getKey())
                        .build();
                TransferInfo newTargetTransferInfo = TransferInfo.builder()
                        .id(autowiredSnowflakeIdWorker.genId())
                        .serialNumber(serialNumber)
                        .accountOperationType(accountOperationType)
                        .amount(totalTransferAmount)
                        .sourceAccountAddr(sourceAccountAddr)
                        .targetAccountAddr(targetAccountAddr)
                        .accountAddr(targetAccountAddr)
                        .transferType(TransferType.IN.getKey())
                        .build();
                insertNewTransferInfoList.add(newSourceTransferInfo);
                insertNewTransferInfoList.add(newTargetTransferInfo);
            }
            completeTransferAccountRecordInfo.setIndex(newSourceAccountRecordIndex);
            completeTransferAccountRecordInfo.setAccountRecordAvailableAmount(leftAvailableAmount);
        }
    }

    public static void main(String[] args) {

        List<CompleteTransferAccountRecordInfo> completeTransferAccountRecordInfoList = Lists.newArrayList();
        completeTransferAccountRecordInfoList.add(CompleteTransferAccountRecordInfo.builder().accountRecordId(1L).index(0).accountRecordRowNo(0).accountAddr("a").accountRecordAvailableAmount(10.00D).accountRecordFrozenAmount(0.00D).sumAccountRecordAvailableAmount(10.00D).build());
        completeTransferAccountRecordInfoList.add(CompleteTransferAccountRecordInfo.builder().accountRecordId(2L).index(0).accountRecordRowNo(1).accountAddr("b").accountRecordAvailableAmount(10.00D).accountRecordFrozenAmount(0.00D).sumAccountRecordAvailableAmount(20.00D).build());
        completeTransferAccountRecordInfoList.add(CompleteTransferAccountRecordInfo.builder().accountRecordId(3L).index(0).accountRecordRowNo(2).accountAddr("c").accountRecordAvailableAmount(10.00D).accountRecordFrozenAmount(0.00D).sumAccountRecordAvailableAmount(30.00D).build());

        List<TransferTarget> transferTargetList = Lists.newArrayList();
        transferTargetList.add(TransferTarget.builder().accountAddr("e").transferAmount(3.00D).build());

        AccountUtil.deposit(DepositParam.builder().serialNumber("123456789").targetAccountAddr("12345").totalDepositAmount(13.00D).build());
        AccountUtil.transfer(TransferParam.builder().serialNumber("123456789").sourceAccountAddr("12345").completeTransferAccountRecordInfoList(completeTransferAccountRecordInfoList).transferTargetList(transferTargetList).totalTransferAmount(13.00D).build());
        AccountUtil.withdraw(WithdrawParam.builder().serialNumber("123456789").sourceAccountAddr("12345").totalWithdrawAmount(13.00D).completeTransferAccountRecordInfoList(completeTransferAccountRecordInfoList).build());

    }
}

@Data
@Builder
class OneToManyTransferParam {
    private String serialNumber;
    private String sourceAccountAddr;
    private Integer accountOperationType;
    private Double totalTransferAmount;
    private List<CompleteTransferAccountRecordInfo> completeTransferAccountRecordInfoList;
    private List<TransferTarget> transferTargetList;
}

@Data
@Builder
class OneToOneTransferParam {
    private String serialNumber;
    private String sourceAccountAddr;
    private Integer accountOperationType;
    private List<CompleteTransferAccountRecordInfo> completeTransferAccountRecordInfoList;
    private TransferTarget transferTarget;
    private List<AccountRecordInfo> insertNewAccountRecordInfoList;
    private List<TransferInfo> insertNewTransferInfoList;
    private List<AccountLogInfo> insertNewAccountLogInfoList;
}
