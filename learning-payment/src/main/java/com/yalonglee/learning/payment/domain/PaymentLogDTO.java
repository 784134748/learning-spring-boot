package com.yalonglee.learning.payment.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentLogDTO {

    /**
     * 交易号
     */
    private String transNum;

    /**
     * 支付总金额
     */
    private Double totalAmount;

    /**
     * 交易流水号
     */
    private String serialNum;

    /**
     * 第三方返回的原始字段
     */
    private String originalString;


}
