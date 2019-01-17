package com.yalonglee.learning.payment.domain;

import com.alipay.api.internal.mapping.ApiField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AlipaySecondCheck {

    /**
     * 商户需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号
     */
    @ApiField("out_trade_no")
    private String outTradeNo;

    /**
     * 校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
     */
    @ApiField("seller_id")
    private String sellerId;

    /**
     * 判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额）
     */
    @ApiField("total_amount")
    private String totalAmount;

    /**
     * 验证app_id是否为该商户本身
     */
    @ApiField("app_id")
    private String appId;

}
