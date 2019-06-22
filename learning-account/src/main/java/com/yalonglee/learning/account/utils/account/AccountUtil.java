package com.yalonglee.learning.account.utils.account;

import com.google.common.collect.Lists;
import com.yalonglee.learning.account.exception.BizzRuntimeException;
import com.yalonglee.learning.account.utils.Json2;
import com.yalonglee.learning.account.utils.snowflake.SnowflakeIdWorker;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>《转账BO》
 * <p><功能详细描述>
 * <p>
 *
 * @author listener
 * @version [V1.0, 2019/1/8]
 * @see [相关类/方法]
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
     * 充值操作
     *
     * @return
     */
    public static EnumMap<AccountOperation, Object> deposit(String serialNumber, String targetAccountAddr, Double totalDepositAmount) {

        //参数校验
        checkDespiteParams(serialNumber, targetAccountAddr, totalDepositAmount);

        List<PaymentDTO> paymentDTOList = Lists.newArrayList();
        paymentDTOList.add(PaymentDTO.builder().index(0).rowNo(1).sum(totalDepositAmount).accountAddr(AccountUtil.SYSTEM_ACCOUNT_ADDR).accountRecordId(0L).availableAmount(totalDepositAmount).frozenAmount(0.00D).build());

        List<TransferTarget> transferTargetList = Lists.newArrayList();
        transferTargetList.add(TransferTarget.builder().accountAddr(targetAccountAddr).totalTransferAmount(totalDepositAmount).build());

        return oneToManyTransfer(serialNumber, AccountUtil.SYSTEM_ACCOUNT_ADDR, totalDepositAmount, paymentDTOList, transferTargetList);

    }

    /**
     * 转账操作
     *
     * @return
     */
    public static EnumMap<AccountOperation, Object> transfer(String serialNumber, String sourceAccountAddr, Double totalTransferAmount, List<PaymentDTO> paymentDTOList, List<TransferTarget> transferTargetList) {

        //参数校验
        checkTransferParams(serialNumber, sourceAccountAddr, totalTransferAmount, paymentDTOList, transferTargetList);

        return oneToManyTransfer(serialNumber, sourceAccountAddr, totalTransferAmount, paymentDTOList, transferTargetList);

    }

    /**
     * 提现操作
     *
     * @param serialNumber
     * @param sourceAccountAddr
     * @param totalWithdrawAmount
     * @param paymentDTOList
     * @return
     */
    public static EnumMap<AccountOperation, Object> withdraw(String serialNumber, String sourceAccountAddr, Double totalWithdrawAmount, List<PaymentDTO> paymentDTOList) {
        //参数校验
        checkWithdrawParams(serialNumber, sourceAccountAddr, totalWithdrawAmount, paymentDTOList);

        List<TransferTarget> transferTargetList = Lists.newArrayList();
        transferTargetList.add(TransferTarget.builder().accountAddr(AccountUtil.SYSTEM_ACCOUNT_ADDR).totalTransferAmount(totalWithdrawAmount).build());

        return oneToManyTransfer(serialNumber, sourceAccountAddr, totalWithdrawAmount, paymentDTOList, transferTargetList);
    }

    /**
     * 校验转账参数
     *
     * @param serialNumber
     * @param targetAccountAddr
     * @param totalDespiteAmount
     */
    private static void checkDespiteParams(String serialNumber, String targetAccountAddr, Double totalDespiteAmount) {
        final double zero = 0.00D;

        if (StringUtils.isBlank(serialNumber)) {
            throw new BizzRuntimeException("流水号不能为空");
        }
        if (StringUtils.isBlank(targetAccountAddr)) {
            throw new BizzRuntimeException("充值账户异常");
        }
        if (totalDespiteAmount <= zero) {
            throw new BizzRuntimeException("充值金额必须大于0.00");
        }
    }

    /**
     * 校验转账参数
     *
     * @param serialNumber
     * @param sourceAccountAddr
     * @param totalTransferAmount
     * @param paymentDTOList
     * @param transferTargetList
     */
    private static void checkTransferParams(String serialNumber, String sourceAccountAddr, Double totalTransferAmount, List<PaymentDTO> paymentDTOList, List<TransferTarget> transferTargetList) {
        final double zero = 0.00D;

        if (StringUtils.isBlank(serialNumber)) {
            throw new BizzRuntimeException("流水号不能为空");
        }
        if (StringUtils.isBlank(sourceAccountAddr)) {
            throw new BizzRuntimeException("转账账户异常");
        }
        if (totalTransferAmount <= zero) {
            throw new BizzRuntimeException("转账金额必须大于0.00");
        }
        if (paymentDTOList == null || paymentDTOList.isEmpty()) {
            throw new BizzRuntimeException("未获取到任何可支付的账户记录");
        }
        if (transferTargetList == null || transferTargetList.isEmpty()) {
            throw new BizzRuntimeException("未获取到任何入账账户地址");
        }

        //本次转账涉及到的账户记录进行升序排序
        paymentDTOList.stream()
                .filter(paymentDTO -> paymentDTO.getSum() - paymentDTO.getAvailableAmount() < totalTransferAmount)
                .sorted(Comparator.comparing(PaymentDTO::getSum))
                .collect(Collectors.toList());
    }

    /**
     * 校验提现参数
     *
     * @param serialNumber
     * @param sourceAccountAddr
     * @param totalTransferAmount
     * @param paymentDTOList
     */
    private static void checkWithdrawParams(String serialNumber, String sourceAccountAddr, Double totalTransferAmount, List<PaymentDTO> paymentDTOList) {
        final double zero = 0.00D;

        if (StringUtils.isBlank(serialNumber)) {
            throw new BizzRuntimeException("流水号不能为空");
        }
        if (StringUtils.isBlank(sourceAccountAddr)) {
            throw new BizzRuntimeException("提现账户异常");
        }
        if (totalTransferAmount <= zero) {
            throw new BizzRuntimeException("提现金额必须大于0.00");
        }
        if (paymentDTOList == null || paymentDTOList.isEmpty()) {
            throw new BizzRuntimeException("未获取到任何可支付的账户记录");
        }

        //本次转账涉及到的账户记录进行升序排序
        paymentDTOList.stream()
                .filter(paymentDTO -> paymentDTO.getSum() - paymentDTO.getAvailableAmount() < totalTransferAmount)
                .sorted(Comparator.comparing(PaymentDTO::getSum))
                .collect(Collectors.toList());
    }

    /**
     * 转账金额分割逻辑
     */
    private static EnumMap<AccountOperation, Object> oneToManyTransfer(String serialNumber, String sourceAccountAddr, Double totalTransferAmount, List<PaymentDTO> paymentDTOList, List<TransferTarget> transferTargetList) {

        log.info("流水号：{},进入一对多转账的方法: 出账账户地址{} - 出账总金额{}", serialNumber, sourceAccountAddr, totalTransferAmount);

        //转账总金额
        final double total = totalTransferAmount;
        //累计已转账金额
        double already = AccountUtil.SYSTEM_DEFINE_AMOUNT_ZERO;

        //被消耗的最后一条账户记录
        AccountRecordInfo lastAccountRecord = null;
        //所有可用金额被耗尽的账户记录的ID（最后一条被消耗的账户记录除外）
        List<Long> updateUseUpAccountRecordIds = Lists.newArrayList();
        //新增的账户记录
        List<AccountRecordInfo> insertNewAccountRecordInfoList = Lists.newArrayList();
        //新增的转账记录
        List<TransferInfo> insertNewTransferInfoList = Lists.newArrayList();
        //新增的账户变更记录
        List<AccountLogInfo> insertNewAccountLogInfoList = Lists.newArrayList();


        //获取支付记录的迭代器
        Iterator<PaymentDTO> iterator = paymentDTOList.listIterator();
        //迭代器当前位置的paymentDTO
        PaymentDTO payment = PaymentDTO.builder().build();
        //迭代器是否继续迭代(当前账户记录中的可用金额是否被消耗殆尽)
        boolean isUseUp = true;

        for (TransferTarget tt : transferTargetList) {
            //是否已发现本次转账涉及的起点账户记录
            boolean isFindStart = false;
            //是否已发现本次转账涉及的终点账户记录
            boolean isFindEnd = false;
            //涉及本次转账的账户记录
            List<PaymentDTO> payments = Lists.newArrayList();
            do {
                if (isUseUp) {
                    payment = iterator.next();
                }
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
                    double valueA = payment.getSum() - payment.getAvailableAmount();
                    double valueB = already;
                    double valueC = payment.getSum();
                    if ((valueA <= valueB) && (valueB < valueC)) {
                        isFindStart = true;
                    } else {
                        throw new BizzRuntimeException("获取账户记录异常！");
                    }
                }
                //统计涉及本次转账的账户记录(从发现开始节点开始统计，直至发现结束节点)
                if (isFindStart && (!isFindEnd)) {
                    payments.add(payment);
                }
                //统计所有可用金额被耗尽的账户记录的ID
                if (iterator.hasNext() && isUseUp) {
                    updateUseUpAccountRecordIds.add(payment.getAccountRecordId());
                }
                //统计被消耗的最后一条账户记录（没有下一条记录，且该对象只被赋值一次）
                if ((!iterator.hasNext()) && lastAccountRecord == null) {
                    AccountRecordInfo accountRecordTmp = AccountRecordInfo.builder()
                            .id(payment.getAccountRecordId())
                            .availableAmount(payment.getSum() - total)
                            .build();
                    lastAccountRecord = accountRecordTmp;
                }
                //获取值大于等于零的尾间距
                if (!isFindEnd) {
                    double valueD = payment.getSum() - payment.getAvailableAmount();
                    double valueE = already + tt.getTotalTransferAmount();
                    double valueF = payment.getSum();
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
            oneToOneTransfer(serialNumber, sourceAccountAddr, payments, tt, insertNewAccountRecordInfoList, insertNewTransferInfoList, insertNewAccountLogInfoList);
            //更新已转账金额
            already += tt.getTotalTransferAmount();
            //迭代器是否继续迭代(当前账户记录中的可用金额是否被消耗殆尽)
            if (already == payment.getSum()) {
                isUseUp = true;
            } else {
                isUseUp = false;
            }
        }

        EnumMap<AccountOperation, Object> operation = new EnumMap<>(AccountOperation.class);
        operation.put(AccountOperation.UPDATE_USE_UP_ACCOUNT_RECORD_IDS, updateUseUpAccountRecordIds);
        operation.put(AccountOperation.INSERT_NEW_ACCOUNT_LOG_INFO_LIST, insertNewAccountLogInfoList);
        operation.put(AccountOperation.INSERT_NEW_ACCOUNT_RECORD_INFO_LIST, insertNewAccountRecordInfoList);
        operation.put(AccountOperation.INSERT_NEW_TRANSFER_INFO_LIST, insertNewTransferInfoList);

        return operation;
    }

    /**
     * 转账记录的生成逻辑
     *
     * @param serialNumber      流水号
     * @param sourceAccountAddr 出账账户地址
     * @param payments          涉及本次转账的账户记录
     * @param target            入账的账户以及金额
     */
    private static void oneToOneTransfer(String serialNumber, String sourceAccountAddr, List<PaymentDTO> payments, TransferTarget target, List<AccountRecordInfo> insertNewAccountRecordInfoList, List<TransferInfo> insertNewTransferInfoList, List<AccountLogInfo> insertNewAccountLogInfoList) {
        log.info("流水号：{},进入顺序转账方法: 入账账户地址{} - 入账总金额{}", serialNumber, target.getAccountAddr(), target.getTotalTransferAmount());
        //入账账户地址
        String targetAccountAddr = target.getAccountAddr();
        //转账总金额
        final double total = target.getTotalTransferAmount();
        //累计已转账金额
        double already = AccountUtil.SYSTEM_DEFINE_AMOUNT_ZERO;
        //剩余待转金额
        double left = total - already;
        for (PaymentDTO payment : payments) {
            //来源账户记录id
            long fromAccountRecordId = payment.getAccountRecordId();
            //新产生账户记录的id
            long accountRecordId = autowiredSnowflakeIdWorker.genId();
            //来源账户记录变更次数
            int fromAccountRecordIndex = payment.getIndex();
            //冻结的金额
            double frozenAmount = payment.getFrozenAmount();
            //剩余的金额
            double leftAvailableAmount = payment.getAvailableAmount() - left;
            //支付消耗的金额
            double payAmount = payment.getAvailableAmount() - leftAvailableAmount;
            if (leftAvailableAmount < AccountUtil.SYSTEM_DEFINE_AMOUNT_ZERO) {
                leftAvailableAmount = AccountUtil.SYSTEM_DEFINE_AMOUNT_ZERO;
            }
            //生成新的账户记录
            AccountRecordInfo newAccountRecordInfo = AccountRecordInfo.builder()
                    .id(accountRecordId)
                    .serialNumber(serialNumber)
                    .fromAccountAddr(sourceAccountAddr)
                    .accountAddr(targetAccountAddr)
                    .index(AccountUtil.SYSTEM_INDEX)
                    .availableAmount(payment.getAvailableAmount() - leftAvailableAmount)
                    .frozenAmount(AccountUtil.SYSTEM_DEFINE_AMOUNT_ZERO)
                    .build();
            insertNewAccountRecordInfoList.add(newAccountRecordInfo);
            //生成账户变更记录(入账账户记录和出账账户记录各一条)
            AccountLogInfo newSourceAccountLogInfo = AccountLogInfo.builder()
                    .id(autowiredSnowflakeIdWorker.genId())
                    .serialNumber(serialNumber)
                    .fromAccountRecordId(fromAccountRecordId)
                    .fromAccountRecordIndex(fromAccountRecordIndex)
                    .accountRecordId(fromAccountRecordId)
                    .accountRecordIndex(AccountUtil.SYSTEM_INDEX)
                    .accountAddr(sourceAccountAddr)
                    .accountRecordIndex(fromAccountRecordIndex + 1)
                    .availableAmount(leftAvailableAmount)
                    .frozenAmount(frozenAmount)
                    .build();
            AccountLogInfo newTargetAccountLogInfo = AccountLogInfo.builder()
                    .id(autowiredSnowflakeIdWorker.genId())
                    .serialNumber(serialNumber)
                    .fromAccountRecordId(fromAccountRecordId)
                    .fromAccountRecordIndex(fromAccountRecordIndex)
                    .accountAddr(targetAccountAddr)
                    .accountRecordId(accountRecordId)
                    .accountRecordIndex(SYSTEM_INDEX)
                    .availableAmount(payAmount)
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
                        .amount(total)
                        .sourceAccountAddr(sourceAccountAddr)
                        .targetAccountAddr(targetAccountAddr)
                        .accountAddr(sourceAccountAddr)
                        .transferType(TransferType.TRANSFER_OUT.getKey())
                        .build();
                TransferInfo newTargetTransferInfo = TransferInfo.builder()
                        .id(autowiredSnowflakeIdWorker.genId())
                        .serialNumber(serialNumber)
                        .amount(total)
                        .sourceAccountAddr(sourceAccountAddr)
                        .targetAccountAddr(targetAccountAddr)
                        .accountAddr(targetAccountAddr)
                        .transferType(TransferType.TRANSFER_IN.getKey())
                        .build();
                insertNewTransferInfoList.add(newSourceTransferInfo);
                insertNewTransferInfoList.add(newTargetTransferInfo);
            }
        }
    }

    public static void main(String[] args) {

        List<PaymentDTO> paymentDTOList = Lists.newArrayList();
        paymentDTOList.add(PaymentDTO.builder().accountRecordId(1L).index(0).rowNo(0).accountAddr("a").availableAmount(10.00).frozenAmount(0.00).sum(10.00).build());
        paymentDTOList.add(PaymentDTO.builder().accountRecordId(2L).index(0).rowNo(1).accountAddr("b").availableAmount(10.00).frozenAmount(0.00).sum(20.00).build());
        paymentDTOList.add(PaymentDTO.builder().accountRecordId(3L).index(0).rowNo(2).accountAddr("c").availableAmount(10.00).frozenAmount(0.00).sum(30.00).build());

        List<TransferTarget> transferTargetList = Lists.newArrayList();
        transferTargetList.add(TransferTarget.builder().accountAddr("e").totalTransferAmount(3.00).build());

        AccountUtil.deposit("123456789", "12345", 13.00);
        AccountUtil.transfer("123456789", "12345", 13.00D, paymentDTOList, transferTargetList);
        AccountUtil.withdraw("123456789", "12345", 13.00, paymentDTOList);

    }

}
