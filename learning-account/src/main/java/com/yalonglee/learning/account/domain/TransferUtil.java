package com.yalonglee.learning.account.domain;

import com.google.common.collect.Lists;
import com.yalonglee.learning.account.exception.BizzRuntimeException;
import com.yalonglee.learning.account.utils.Json2;
import com.yalonglee.learning.account.utils.snowflake.SnowflakeIdWorker;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

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
@Getter
public class TransferUtil {

    /**
     * 创世货币来源账户和提现金额的入账账户默认为系统账户
     */
    private static final String SYSTEM_ACCOUNT_ADDR = "system_account_addr";
    /**
     * 创世货币来源账户记录ID和提现金额的入账账户记录ID默认为0L
     */
    private static final long SYSTEM_ACCOUNT_RECORD_ID = 0L;
    /**
     * 系统默认金额
     */
    private static final double SYSTEM_DEFINE_AMOUNT_ZERO = 0.00D;
    /**
     * 账户记录默认变更次数
     */
    private static final int SYSTEM_INDEX = 0;
    /**
     * 流水号
     */
    private String serialNumber;
    /**
     * 出账账户地址
     */
    private String sourceAccountAddr;
    /**
     * 转账总金额
     */
    private Double totalTransferAmount;
    /**
     * 本次转账涉及到的账户记录
     */
    private List<PaymentDTO> paymentDTOList;
    /**
     * 转入至哪些账户，以及具体的金额
     */
    private List<TransferTarget> transferTargetList;
    /**
     * 被消耗的最后一条账户记录
     */
    private AccountRecordInfo lastAccountRecord;
    /**
     * 所有可用金额被耗尽的账户记录的ID（最后一条被消耗的账户记录除外）
     */
    private List<Long> updateUseUpAccountRecordIds = Lists.newArrayList();
    /**
     * 新增的账户记录
     */
    private List<AccountRecordInfo> insertNewAccountRecordInfoList = Lists.newArrayList();
    /**
     * 新增的转账记录
     */
    private List<TransferInfo> insertNewTransferInfoList = Lists.newArrayList();
    /**
     * 新增的账户变更记录
     */
    private List<AccountLogInfo> insertNewAccountLogInfoList = Lists.newArrayList();
    /**
     * 雪花算法
     */
    private SnowflakeIdWorker snowflakeIdWorker = new SnowflakeIdWorker();
    /**
     * 生成md5
     */
    public String getMd5() {
        return DigestUtils.md5DigestAsHex(Json2.toJson(this).getBytes());
    }

    @Builder
    public TransferUtil(String serialNumber, String sourceAccountAddr, Double totalTransferAmount, List<PaymentDTO> paymentDTOList, List<TransferTarget> transferTargetList) {
        this.serialNumber = serialNumber;
        this.sourceAccountAddr = sourceAccountAddr;
        this.totalTransferAmount = totalTransferAmount;
        this.paymentDTOList = paymentDTOList;
        this.transferTargetList = transferTargetList;
    }

    /**
     * 转账操作
     *
     * @return
     */
    public EnumMap<TransferOperation, Object> transfer() {
        this.checkParams();
        this.oneToManyTransfer();

        EnumMap<TransferOperation, Object> operation = new EnumMap<>(TransferOperation.class);
        operation.put(TransferOperation.UPDATE_USE_UP_ACCOUNT_RECORD_IDS, updateUseUpAccountRecordIds);
        operation.put(TransferOperation.INSERT_NEW_ACCOUNT_LOG_INFO_LIST, insertNewAccountLogInfoList);
        operation.put(TransferOperation.INSERT_NEW_ACCOUNT_RECORD_INFO_LIST, insertNewAccountRecordInfoList);
        operation.put(TransferOperation.INSERT_NEW_TRANSFER_INFO_LIST, insertNewTransferInfoList);

        return operation;
    }

    /**
     * 参数校验
     */
    private void checkParams() {
        final double zero = 0.00D;

        if (StringUtils.isBlank(this.serialNumber)) {
            throw new BizzRuntimeException("流水号不能为空");
        }
        if (StringUtils.isBlank(this.sourceAccountAddr)) {
            throw new BizzRuntimeException("转账账户异常");
        }
        if (this.totalTransferAmount <= zero) {
            throw new BizzRuntimeException("转账金额必须大于0.00");
        }
        if (this.paymentDTOList == null || this.paymentDTOList.isEmpty()) {
            throw new BizzRuntimeException("未获取到任何可支付的账户记录");
        }
        if (this.transferTargetList == null || this.transferTargetList.isEmpty()) {
            throw new BizzRuntimeException("未获取到任何入账账户地址");
        }

        //本次转账涉及到的账户记录进行升序排序
        this.paymentDTOList = this.paymentDTOList.stream()
                .filter(paymentDTO -> paymentDTO.getSum() - paymentDTO.getAvailableAmount() < this.totalTransferAmount)
                .sorted(Comparator.comparing(PaymentDTO::getSum))
                .collect(Collectors.toList());
    }

    /**
     * 转账金额分割逻辑
     */
    private void oneToManyTransfer() {
        log.info("流水号：{},进入一对多转账的方法:\n" +
                "出账账户地址{} - 转账总金额{}\n" +
                "本次转账涉及的账户记录{}\n" +
                "转入至哪些账户，以及具体的金额{}", this.serialNumber, this.sourceAccountAddr, this.totalTransferAmount, Json2.toJson(this.paymentDTOList), Json2.toJson(transferTargetList));
        //转账总金额
        final double total = this.totalTransferAmount;
        //累计已转账金额
        double already = TransferUtil.SYSTEM_DEFINE_AMOUNT_ZERO;

        //获取支付记录的迭代器
        Iterator<PaymentDTO> iterator = paymentDTOList.listIterator();
        //迭代器当前位置的paymentDTO
        PaymentDTO payment = PaymentDTO.builder().build();
        //迭代器是否继续迭代(当前账户记录中的可用金额是否被消耗殆尽)
        boolean isUseUp = true;

        for (TransferTarget tt : this.transferTargetList) {
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
                    this.updateUseUpAccountRecordIds.add(payment.getAccountRecordId());
                }
                //统计被消耗的最后一条账户记录（没有下一条记录，且该对象只被赋值一次）
                if ((!iterator.hasNext()) && this.lastAccountRecord == null) {
                    AccountRecordInfo accountRecordTmp = AccountRecordInfo.builder()
                            .id(payment.getAccountRecordId())
                            .availableAmount(payment.getSum() - total)
                            .build();
                    this.lastAccountRecord = accountRecordTmp;
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
            oneToOneTransfer(this.serialNumber, this.sourceAccountAddr, payments, tt);
            //更新已转账金额
            already += tt.getTotalTransferAmount();
            //迭代器是否继续迭代(当前账户记录中的可用金额是否被消耗殆尽)
            if (already == payment.getSum()) {
                isUseUp = true;
            } else {
                isUseUp = false;
            }
        }
    }

    /**
     * 转账记录的生成逻辑
     *
     * @param serialNumber      流水号
     * @param sourceAccountAddr 出账账户地址
     * @param payments          涉及本次转账的账户记录
     * @param target            入账的账户以及金额
     */
    private void oneToOneTransfer(String serialNumber, String sourceAccountAddr, List<PaymentDTO> payments, TransferTarget target) {
        log.info("流水号：{},进入顺序转账方法:\n" +
                "涉及本次转账的账户记录{}\n" +
                "入账的账户以及金额{}", serialNumber, Json2.toJson(payments), Json2.toJson(target));
        //入账账户地址
        String targetAccountAddr = target.getAccountAddr();
        //转账总金额
        final double total = target.getTotalTransferAmount();
        //累计已转账金额
        double already = TransferUtil.SYSTEM_DEFINE_AMOUNT_ZERO;
        //剩余待转金额
        double left = total - already;
        for (PaymentDTO payment : payments) {
            //来源账户记录id
            long fromAccountRecordId = payment.getAccountRecordId();
            //新产生账户记录的id
            long accountRecordId = this.snowflakeIdWorker.genId();
            //来源账户记录变更次数
            int fromAccountRecordIndex = payment.getIndex();
            //冻结的金额
            double frozenAmount = payment.getFrozenAmount();
            //剩余的金额
            double leftAvailableAmount = payment.getAvailableAmount() - left;
            //支付消耗的金额
            double payAmount = payment.getAvailableAmount() - leftAvailableAmount;
            if (leftAvailableAmount < TransferUtil.SYSTEM_DEFINE_AMOUNT_ZERO) {
                leftAvailableAmount = TransferUtil.SYSTEM_DEFINE_AMOUNT_ZERO;
            }
            //生成新的账户记录
            AccountRecordInfo newAccountRecordInfo = AccountRecordInfo.builder()
                    .id(accountRecordId)
                    .serialNumber(serialNumber)
                    .fromAccountAddr(sourceAccountAddr)
                    .accountAddr(targetAccountAddr)
                    .index(TransferUtil.SYSTEM_INDEX)
                    .availableAmount(payment.getAvailableAmount() - leftAvailableAmount)
                    .frozenAmount(TransferUtil.SYSTEM_DEFINE_AMOUNT_ZERO)
                    .build();
            this.insertNewAccountRecordInfoList.add(newAccountRecordInfo);
            //生成账户变更记录(入账账户记录和出账账户记录各一条)
            AccountLogInfo newSourceAccountLogInfo = AccountLogInfo.builder()
                    .id(this.snowflakeIdWorker.genId())
                    .serialNumber(serialNumber)
                    .fromAccountRecordId(fromAccountRecordId)
                    .fromAccountRecordIndex(fromAccountRecordIndex)
                    .accountRecordId(fromAccountRecordId)
                    .accountRecordIndex(TransferUtil.SYSTEM_INDEX)
                    .accountAddr(sourceAccountAddr)
                    .accountRecordIndex(fromAccountRecordIndex + 1)
                    .availableAmount(leftAvailableAmount)
                    .frozenAmount(frozenAmount)
                    .build();
            AccountLogInfo newTargetAccountLogInfo = AccountLogInfo.builder()
                    .id(this.snowflakeIdWorker.genId())
                    .serialNumber(serialNumber)
                    .fromAccountRecordId(fromAccountRecordId)
                    .fromAccountRecordIndex(fromAccountRecordIndex)
                    .accountAddr(targetAccountAddr)
                    .accountRecordId(accountRecordId)
                    .accountRecordIndex(SYSTEM_INDEX)
                    .availableAmount(payAmount)
                    .frozenAmount(frozenAmount)
                    .build();
            this.insertNewAccountLogInfoList.add(newSourceAccountLogInfo);
            this.insertNewAccountLogInfoList.add(newTargetAccountLogInfo);
            //完成转账
            if (leftAvailableAmount >= TransferUtil.SYSTEM_DEFINE_AMOUNT_ZERO) {
                //生成转账记录(入账账户记录和出账账户记录各一条)
                TransferInfo newSourceTransferInfo = TransferInfo.builder()
                        .id(this.snowflakeIdWorker.genId())
                        .serialNumber(serialNumber)
                        .amount(total)
                        .sourceAccountAddr(sourceAccountAddr)
                        .targetAccountAddr(targetAccountAddr)
                        .accountAddr(sourceAccountAddr)
                        .transferType(TransferType.TRANSFER_OUT.getKey())
                        .build();
                TransferInfo newTargetTransferInfo = TransferInfo.builder()
                        .id(this.snowflakeIdWorker.genId())
                        .serialNumber(serialNumber)
                        .amount(total)
                        .sourceAccountAddr(sourceAccountAddr)
                        .targetAccountAddr(targetAccountAddr)
                        .accountAddr(targetAccountAddr)
                        .transferType(TransferType.TRANSFER_IN.getKey())
                        .build();
                this.insertNewTransferInfoList.add(newSourceTransferInfo);
                this.insertNewTransferInfoList.add(newTargetTransferInfo);
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

        TransferUtil transferUtil = TransferUtil.builder()
                .serialNumber("1234567890")
                .sourceAccountAddr("system_account_addr")
                .totalTransferAmount(13.00)
                .paymentDTOList(paymentDTOList)
                .transferTargetList(transferTargetList)
                .build();

        transferUtil.transfer();
    }

}
